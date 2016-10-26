package com.zb.api.fuelrod.service.storage;

import com.zb.api.commons.ApiLogger;
import com.zb.api.commons.codis.JedisFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * PluginStorage
 *
 * @author zj
 * @date 2016/8/18
 */
@Component
public class PluginStorage {

    @Resource
    private JedisFactory pluginsJedisFactory;

    /**
     * key计数
     * @param key
     * @param count
     * @return
     */
    public long incy(String key, int count){
        try {
            return pluginsJedisFactory.getInstance().incrBy(key, count);
        }catch (Exception e){
            ApiLogger.error("plugin jedis incy error! key:" + key, e);
            return 0;
        }
    }

    /**
     * 获取指定key的值
     * @param key
     * @return
     */
    public String get(String key){
        try {
            return pluginsJedisFactory.getInstance().get(key);
        }catch (Exception e){
            ApiLogger.error("plugin jedis get error! key:" + key, e);
            return null;
        }
    }

    /**
     * 删除指定key
     * @param key
     * @return
     */
    public boolean delete(String key){
        try {
            return pluginsJedisFactory.getInstance().del(key) > 0;
        }catch (Exception e){
            ApiLogger.error("plugin jedis del error! key:" + key, e);
            return false;
        }
    }
}
