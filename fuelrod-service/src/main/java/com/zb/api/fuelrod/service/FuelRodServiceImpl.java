package com.zb.api.fuelrod.service;

import com.alibaba.fastjson.JSONObject;
import com.zb.api.commons.ApiLogger;
import com.zb.api.commons.mq.Producer;
import com.zb.api.commons.mq.api.Topic;
import com.zb.api.fuelrod.constants.RedisConstans;
import com.zb.api.fuelrod.dao.impl.FuelRodDao;
import com.zb.api.fuelrod.service.storage.PluginStorage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;

/**
 * FuelRodServiceImpl
 *
 * @author zj
 * @date 2016/8/18
 */
@Service("fuelRodService")
public class FuelRodServiceImpl implements FuelRodService{

    @Resource
    private PluginStorage pluginStorage;
    @Resource
    private FuelRodDao fuelRodDao;
    @Resource(name = "fuelRodMQProducer")
    private Producer producer;

    /**
     * 获取加油棒数量
     * @param liveId  直播ID
     * @return
     */
    @Override
    public int getFuelRod(long liveId) {
        int count = 0;
        try {
            String value = pluginStorage.get(RedisConstans.assembleFuelRod(liveId));
            count = value == null ? fuelRodDao.get(liveId) : Integer.parseInt(value);
        }catch (Exception e){
            ApiLogger.error("get fuelrod by liveId error！liveId:" + liveId, e);
        }
        return count;
    }

    /**
     * 加油棒，点赞
     * @param userId    用户ID
     * @param roomId    房间ID
     * @param liveId    直播ID
     * @param count     点赞数
     * @return
     */
    @Override
    public boolean fuelRod(int userId, int roomId, long liveId, int count) {
        JSONObject object = new JSONObject();
        object.put("userId", userId);
        object.put("roomId", roomId);
        object.put("liveId", liveId);
        object.put("count", count);
        String key = System.currentTimeMillis() + userId + liveId + new Random().nextInt(1000) + "";
        producer.send(Topic.FUELROD_SEND_SUCCESS, key, object.toJSONString());
        return true;
    }
}
