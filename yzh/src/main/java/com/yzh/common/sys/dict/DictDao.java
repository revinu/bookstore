package com.yzh.common.sys.dict;

import com.yzh.common.base.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * @author yzh
 * @date 2018/4/12 11:33
 */
@Repository
public interface DictDao extends BaseDao<Dict, String> {

    void deleteCategory(Dict dict);

}
