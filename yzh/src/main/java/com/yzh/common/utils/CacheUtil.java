package com.yzh.common.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.apache.commons.lang3.Validate;
import org.springframework.cache.annotation.EnableCaching;

import java.io.Serializable;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * 缓存工具类
 *
 * @author yzh
 * @date 2018/4/12 12:22
 */
@EnableCaching
public class CacheUtil {

    //  缓存管理器
    private static CacheManager cacheManager;
    //  默认缓存区名
    public static String CACHE_DEFAULT = "defaultCache";
    //  缓存区名-键   集合

    /**
     * 获取缓存管理器
     *
     * @return
     */
    public static CacheManager getManger() {
        if (cacheManager == null) {
            URL url = CacheUtil.class.getResource("/ehcache.xml");
            cacheManager = CacheManager.newInstance(url);
            Validate.validState(cacheManager != null, "cacheManager为空...");
        }
        return cacheManager;
    }

    /**
     * 获取缓存区
     *
     * @param cacheName 缓存区名
     * @return
     */
    public static Cache getCache(String cacheName) {
        Cache cache = getManger().getCache(cacheName);
        Validate.validState(cache != null, "缓存" + cacheName + "为空...");
        return cache;
    }

    /**
     * 获取默认缓存区
     *
     * @return
     */
    public static Cache getDefaultCache() {
        return getCache(CACHE_DEFAULT);
    }

    /**
     * 添加缓存
     *
     * @param cacheName 缓存区名
     * @param key       键
     * @param value     值
     */
    public static void putElement(String cacheName, Serializable key, Object value) {
        Cache cache = getCache(cacheName);
        cache.put(new Element(key, value));
    }

    /**
     * 添加缓存 — 使用默认缓存区
     *
     * @param key   键
     * @param value 值
     */
    public static void putElement(Serializable key, Object value) {
        putElement(CACHE_DEFAULT, key, value);
    }

    /**
     * 添加缓存并刷新
     *
     * @param cacheName 缓存区名
     */
    public static void flush(String cacheName) {
        getCache(cacheName).flush();
    }

    /**
     * 获取值
     *
     * @param cacheName 缓存区名
     * @param key       键
     * @return
     */
    public static Object getElementValue(String cacheName, String key) {
        Cache cache = getCache(cacheName);
        Element element = cache.get(key);
        return element == null ? null : element.getObjectValue();
    }

    /**
     * 获取默认缓存区中的值
     *
     * @param key 键
     * @return
     */
    public static Object getElementValue(String key) {
        return getElementValue(CACHE_DEFAULT, key);
    }

    /**
     * 获取缓存区中所有值的集合
     *
     * @param cacheName 缓存区名
     * @return
     */
    public static Map<Object, Element> getAllElementValue(String cacheName) {
        Cache cache = getCache(cacheName);
        List keys = cache.getKeys();
        return cache.getAll(keys);
    }

    /**
     * 获取默认缓存区中所有值的集合
     *
     * @return
     */
    public static Map<Object, Element> getAllElementValue() {
        return getAllElementValue(CACHE_DEFAULT);
    }

    /**
     * 删除缓存区
     *
     * @param cacheName 缓存区名
     */
    public static void removeCache(String cacheName) {
        getManger().removeCache(cacheName);
    }

    /**
     * 删除元素
     *
     * @param cacheName 缓存区名
     * @param key       键
     */
    public static void removeElement(String cacheName, Serializable key) {
        getCache(cacheName).remove(key);
    }

    /**
     * 删除默认缓存区元素
     *
     * @param key 键
     */
    public static void removeElement(Serializable key) {
        removeElement(CACHE_DEFAULT, key);
    }

    /**
     * 清空缓存区元素
     *
     * @param cacheName 缓存区
     */
    public static void removeAllElements(String cacheName) {
        getCache(cacheName).removeAll();
    }

    /**
     * 清空默认缓存区
     */
    public static void removeAllElements() {
        removeAllElements(CACHE_DEFAULT);
    }

    /**
     * 关闭缓存管理器
     */
    public static void shutdown() {
        shutdown(cacheManager);
    }

    /**
     * 关闭缓存管理对象
     *
     * @param cacheManager
     */
    public static void shutdown(CacheManager cacheManager) {
        try {
            Validate.validState(cacheManager != null, "cacheManager为空...");
            cacheManager.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
