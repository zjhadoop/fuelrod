package com.zb.api.fuelrod.message;

import com.alibaba.fastjson.JSONObject;
import com.zb.api.commons.ApiLogger;
import com.zb.api.commons.mq.annotation.ConsumerConfig;
import com.zb.api.commons.mq.api.Consumer;
import com.zb.api.commons.mq.api.Topic;
import com.zb.api.fuelrod.constants.RedisConstans;
import com.zb.api.fuelrod.dao.impl.FuelRodDao;
import com.zb.api.fuelrod.po.FuelRod;
import com.zb.api.fuelrod.service.FuelRodService;
import com.zb.api.fuelrod.service.storage.PluginStorage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * LiveFinishConsumer
 *
 * @author zj
 * @date 2016/8/18
 */
@Component("fuelRodLiveFinishConsumer")
@ConsumerConfig(consumerName = "zbFuelRodLiveFinishConsumer", topic = Topic.LIVE_FINISHED)
public class LiveFinishConsumer implements Consumer{

    @Resource
    private FuelRodDao fuelRodDao;
    @Resource
    private PluginStorage pluginStorage;

    @Override
    public boolean doit(String topic, String tags, String key, String msg) {
        ApiLogger.info("fuelrod live finish consumer! key: " + key + ",msg:" + msg);
        if (Topic.LIVE_FINISHED.getValue().equals(topic)){
            try {
                //1.解析msg
                JSONObject object = JSONObject.parseObject(msg);
                long liveId = object.getLong("liveId");
                int roomId = object.getInteger("roomId");
                int time = (int) (System.currentTimeMillis() / 1000);
                FuelRod fuelRod = new FuelRod();
                fuelRod.setLiveId(liveId);
                fuelRod.setRoomId(roomId);
                fuelRod.setCreateTime(time);
                fuelRod.setUpdateTime(time);
                int clickNum = 0;
                try {
                    String num = pluginStorage.get(RedisConstans.assembleFuelRod(liveId));
                    clickNum = num == null ? 0 : Integer.parseInt(num);
                }catch (Exception e){
                    ApiLogger.error("get fuelrod by liveId error！liveId:" + liveId, e);
                }
                fuelRod.setClickNum(clickNum);
                fuelRodDao.insert(fuelRod);
                try {
                    pluginStorage.delete(RedisConstans.assembleFuelRod(liveId));
                }catch (Exception e){
                    ApiLogger.error("delete redis key error! liveId:" + liveId, e);
                }
                return true;
            }catch (Exception e){
                ApiLogger.error("fuelrod live finished consumer error! key: " + key + ",msg: " + msg, e);
                return false;
            }
        }
        return true;
    }
}
