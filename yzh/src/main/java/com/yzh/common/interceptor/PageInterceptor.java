package com.yzh.common.interceptor;

import com.yzh.common.base.BaseEntity;
import com.yzh.common.base.Page;
import com.yzh.common.utils.StringUtil;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * mySql分页拦截器
 *
 * @author yzh
 * @date 2018/1/31 3:40
 */
@Intercepts({@Signature(type = Executor.class, method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class PageInterceptor implements Interceptor, Serializable {

//    private SQLDialect dialect;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) args[0];
        Object parameter = args[1];
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        Object parameterObject = boundSql.getParameterObject();

        if (StringUtil.empty(boundSql.getSql())) {
            return invocation.proceed();
        }
        // 获取分页参数对象
        Page page = getPage(parameterObject);
        int pageSize;
        if (page == null || (pageSize = page.getPageSize()) == 0) {
            return invocation.proceed();
        }
        // 需要分页的SQL
        String originalSql = boundSql.getSql().trim().replaceAll("\\n", " ").replaceAll("\\s{2,}", " ");
        // 计算总记录数
        int totalCount = getTotalCount(originalSql, mappedStatement, boundSql, parameterObject);
        //  设置page数据
        int pageCount = new Double(Math.ceil((double) totalCount / page.getPageSize())).intValue();
        if (pageCount <= 0) {
            pageCount = 1;
        }
        page.setTotalCount(totalCount);
        page.setPageCount(pageCount);
        //  分页SQL
        int curPage = page.getCurPage();
        if (curPage > pageCount) {
            curPage = pageCount;
        }
        int offset = pageSize * (curPage - 1);
        /*String limitSql = new StringBuffer(originalSql)
                .append(" LIMIT ")
                .append(offset)
                .append(",")
                .append(page.getPageSize()).toString();*/
        String limitSql = new StringBuffer("SELECT * FROM (")
                .append(originalSql)
                .append(") t_temporary")
                .append(" LIMIT ")
                .append(offset)
                .append(",")
                .append(page.getPageSize()).toString();
        System.out.println("LIMIT SQL:" + limitSql);
//        args[2] = new RowBounds(RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);
        final BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(), limitSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
        invocation.getArgs()[0] = copyMappedStatement(mappedStatement, new SqlSource() {
            @Override
            public BoundSql getBoundSql(Object o) {
                return newBoundSql;
            }
        });
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
//        dialect = new MySQLDialect();
    }

    //  获取参数中的page对象
    private static Page getPage(Object params) {
        if (params == null) {
            return null;
        }
        if (params instanceof BaseEntity) {
            return ((BaseEntity) params).getPage();
        }
        if (params instanceof Page) {
            return (Page) params;
        }
        return null;
    }

    // 计算总记录数
    private static int getTotalCount(String sql, MappedStatement mappedStatement, BoundSql boundSql, Object parameterObject) throws Exception {
        String countSql = "SELECT COUNT(1) FROM (" + removeOrders(sql) + ") temp_count";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            System.out.println("COUNT SQL: " + countSql);
            conn = mappedStatement.getConfiguration().getEnvironment().getDataSource().getConnection();
            ps = conn.prepareStatement(countSql);
            //  设置参数
            BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql,
                    boundSql.getParameterMappings(), parameterObject);
            setParameters(ps, mappedStatement, countBS, parameterObject);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    // 去除order by子句
    private static String removeOrders(String sqlString) {
        //  去除以ASC、DESC结尾的
        sqlString = StringUtil.replace(sqlString, "order\\s*by[\\w|\\W|\\s|\\S]*SC$", "");
        //  去除省略ASC的
        return StringUtil.replace(sqlString, "order\\s*by$", "");
    }

    private MappedStatement copyMappedStatement(MappedStatement ms, SqlSource sqlSource) {
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(),
                ms.getId(), sqlSource, ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null) {
            for (String keyProperty : ms.getKeyProperties()) {
                builder.keyProperty(keyProperty);
            }
        }
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.cache(ms.getCache());
        builder.useCache(ms.isUseCache());
        return builder.build();
    }

    //  设置参数
    private static void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql, Object parameterObject) throws SQLException {
        ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if (parameterMappings != null) {
            Configuration configuration = mappedStatement.getConfiguration();
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            MetaObject metaObject = parameterObject == null ? null :
                    configuration.newMetaObject(parameterObject);
            for (int i = 0; i < parameterMappings.size(); i++) {
                ParameterMapping parameterMapping = parameterMappings.get(i);
                if (parameterMapping.getMode() != ParameterMode.OUT) {
                    Object value;
                    String propertyName = parameterMapping.getProperty();
                    PropertyTokenizer prop = new PropertyTokenizer(propertyName);
                    if (parameterObject == null) {
                        value = null;
                    } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                        value = parameterObject;
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        value = boundSql.getAdditionalParameter(propertyName);
                    } else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX) && boundSql.hasAdditionalParameter(prop.getName())) {
                        value = boundSql.getAdditionalParameter(prop.getName());
                        if (value != null) {
                            value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
                        }
                    } else {
                        value = metaObject == null ? null : metaObject.getValue(propertyName);
                    }
                    TypeHandler typeHandler = parameterMapping.getTypeHandler();
                    if (typeHandler == null) {
                        throw new ExecutorException("There was no TypeHandler found for parameter " + propertyName + " of statement " + mappedStatement.getId());
                    }
                    typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
                }
            }
        }
    }

}
