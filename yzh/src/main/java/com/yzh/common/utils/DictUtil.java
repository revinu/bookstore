package com.yzh.common.utils;

import com.yzh.common.init.SpringContextHolder;
import com.yzh.common.sys.dict.Dict;
import com.yzh.common.sys.dict.DictDao;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 字典表工具类
 *
 * @author yzh
 * @date 2018/4/12 12:22
 */
public class DictUtil {

    private static DictDao dictDao = SpringContextHolder.getBean(DictDao.class);
    //  分类type
    public static String TYPE_CATEGORY = "category";

    /**
     * 获取对应type(缓存区名)的dict集合
     *
     * @param type
     * @return
     */
    public static List<Dict> listByType(String type) {
        CacheUtil.removeAllElements(type);
        List<Dict> dictList = new ArrayList<>();
        if (StringUtil.notEmpty(type)) {
            //  查缓存
            Map<Object, Element> elementsMap = CacheUtil.getAllElementValue(type);
            if (!elementsMap.isEmpty()) {
                dictList = new ArrayList<>();
                Dict dict = new Dict();
                for (Map.Entry<Object, Element> entry : elementsMap.entrySet()) {
                    Element element = entry.getValue();
                    dict.setKey((Integer) element.getObjectKey());
                    dict.setValue((String) element.getObjectValue());
                    dictList.add(dict);
                }
                return dictList;
            }
            //  查数据库
            Dict dict = new Dict();
            dict.setType(type);
            dict.forbiddenPagination();
            dictList = dictDao.listByEntity(dict);
            //  存缓存
            Cache cache = CacheUtil.getCache(type);
            for (Dict d : dictList) {
                cache.put(new Element(d.getKey(), d.getValue()));
            }
        }
        return dictList;
    }

    public static List<Dict> listByType() {
        return listByType(CacheUtil.CACHE_DEFAULT);
    }

    /**
     * 获取对应type和key的dict的值
     *
     * @param type
     * @param key
     * @return
     */
    public static String getValue(String type, Integer key, String defaultValue) {
        if (StringUtil.notEmpty(type) && key != null) {
            //  查缓存
            String elementValue = (String) CacheUtil.getElementValue(key.toString());
            if (StringUtil.notEmpty(elementValue)) {
                return elementValue;
            }
            //  查数据库
            Dict dict = new Dict();
            dict.setType(type);
            dict.setKey(key);
            dict = dictDao.getByEntity(dict);
            String value = dict.getValue();
            //  存缓存
            if (dict != null) {
                CacheUtil.putElement(key.toString(), value);
                return value;
            }
            if (defaultValue != null)
                return defaultValue;
        }
        return "";
    }

    public static String getValue(Integer key, String defaultValue) {
        return getValue(CacheUtil.CACHE_DEFAULT, key, defaultValue);
    }

}
