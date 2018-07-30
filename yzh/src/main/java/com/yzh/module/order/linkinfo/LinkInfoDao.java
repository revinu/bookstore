package com.yzh.module.order.linkinfo;

import com.yzh.common.base.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * @author yzh
 * @date 2018/4/22 8:28
 */
@Repository
public interface LinkInfoDao extends BaseDao<LinkInfo, String> {

    int getCount(String userId);

}
