package com.yzh.common.sys.dict;

import com.yzh.common.base.BaseService;
import com.yzh.common.utils.CacheUtil;
import com.yzh.common.utils.DictUtil;
import com.yzh.common.utils.ResultBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yzh
 * @date 2018/4/12 11:34
 */
@Service
@Transactional(readOnly = true)
public class DictService extends BaseService<DictDao, Dict, String> {

    @Transactional(readOnly = false)
    public ResultBean addCategory(Integer key, String value) {
        Dict dict = new Dict();
        dict.setType(DictUtil.TYPE_CATEGORY);
        dict.setKey(key);
        if (dao.getByEntity(dict) != null)
            return new ResultBean(ResultBean.CODE_PARAM_ERROR, "键为" + key + "的分类已存在");
        dict.setValue(value);
        dao.insert(dict);
        CacheUtil.putElement(DictUtil.TYPE_CATEGORY, key, value);
        return new ResultBean("添加成功");
    }

    @Transactional(readOnly = false)
    public ResultBean editCategory(String id, String value) {
        Dict dict = dao.get(id);
        if (dict == null)
            return new ResultBean(ResultBean.CODE_PARAM_ERROR);
        dict.setValue(value);
        dao.update(dict);
        CacheUtil.putElement(DictUtil.TYPE_CATEGORY, dict.getKey(), value);
        return new ResultBean("修改成功");
    }

    @Transactional(readOnly = false)
    public ResultBean deleteCategory(String id) {
        Dict dict = new Dict();
        dict.setId(id);
        dict.setType(DictUtil.TYPE_CATEGORY);
        dict = dao.getByEntity(dict);
        if (dict != null) {
            CacheUtil.removeElement(DictUtil.TYPE_CATEGORY, dict.getKey());
            dao.deleteCategory(dict);
        }
        return new ResultBean("删除成功");
    }

}
