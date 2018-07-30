package com.yzh.common.sys.user;

import com.yzh.common.base.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author yzh
 * @date 2018/4/2 10:42
 */
@Repository
public interface UserDao extends BaseDao<User, String> {

    User getByUsername(String username);

    void resetPassword(@Param("id") String id,@Param("password") String password);

}
