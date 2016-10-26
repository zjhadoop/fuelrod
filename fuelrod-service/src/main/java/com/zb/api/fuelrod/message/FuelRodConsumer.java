package com.zb.api.fuelrod.message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zb.api.commons.ApiLogger;
import com.zb.api.commons.message.MessageUtil;
import com.zb.api.commons.message.enums.MessageType;
import com.zb.api.commons.message.po.MessageUser;
import com.zb.api.commons.mq.annotation.ConsumerConfig;
import com.zb.api.commons.mq.api.Consumer;
import com.zb.api.commons.mq.api.Topic;
import com.zb.api.fuelrod.constants.RedisConstans;
import com.zb.api.fuelrod.constants.ZkConstants;
import com.zb.api.fuelrod.service.ConfigService;
import com.zb.api.fuelrod.service.storage.PluginStorage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * FuelRodConsumer
 *
 * @author zj
 * @date 2016/8/22
 */
@Component("zbFuelRodSendConsumer")
@ConsumerConfig(consumerName = "zbFuelRodSendConsumer", topic = Topic.FUELROD_SEND_SUCCESS)
public class FuelRodConsumer implements Consumer{

    @Resource
    private PluginStorage pluginStorage;
    @Resource
    private ConfigService configService;

    /**
     * 接收客户端点赞消息消费
     * @param topic
     * @param tags
     * @param key
     * @param msg
     * @return
     */
    @Override
    public boolean doit(String topic, String tags, String key, String msg) {
        ApiLogger.info("fuelrod send success! key:" + key + ",msg:" + msg);
        if (Topic.FUELROD_SEND_SUCCESS.getValue().equals(topic)){
            try {
                JSONObject object = JSON.parseObject(msg);
                if (object == null){
                    return true;
                }
                int userId = object.getInteger("userId");//用户ID
                int roomId = object.getInteger("roomId");//房间ID
                long liveId = object.getLong("liveId");//直播ID
                int count = object.getInteger("count");//点赞数
                //根据房间ID 查询当前房间直播人数,TODO 冬冬  根据房间ID查在线人数
                int onLineUsers = 1;

                //当场直播点赞总数
                long currentCount = pluginStorage.incy(RedisConstans.assembleFuelRod(liveId), count);
                //已下发网关的点赞数
                long sendedCount = 0l;
                try {
                    String value = pluginStorage.get(RedisConstans.assembleSendFuelRod(liveId));
                    sendedCount = value == null ? 0 : Long.parseLong(value);
                }catch (NumberFormatException e){
                    sendedCount = 0;
                }
                if (needSend(currentCount, sendedCount, onLineUsers)){
                    //调用网关
                    MessageUser fromUser = new MessageUser();
                    fromUser.setId(userId + "");
                    MessageUser toUser = new MessageUser();
                    toUser.setId(roomId + "");
                    JSONObject ext = new JSONObject();
                    ext.put("lever", 1);//加油棒效果
                    boolean isSucess = MessageUtil.sendMsg(MessageType.fuelRod, fromUser, toUser, ext);
                    if (isSucess){
                        //已下发点赞数 incy
                        pluginStorage.incy(RedisConstans.assembleSendFuelRod(liveId), count);
                    }
                }
                return true;
            }catch (Exception e){
                ApiLogger.error("fuelrod send success! key:" + key + ",msg:" + msg, e);
                return false;
            }
        }
        return true;
    }

    /**
     * 是否需要发送
     * @param currentCount  当前点赞数
     * @param sendedCount 已发点赞数
     * @param onLineUsers 在线人数
     * @return
     */
    private boolean needSend(long currentCount, long sendedCount, int onLineUsers) {
        //点击频率
        double ratio = (double)(currentCount - sendedCount) / onLineUsers;
        double zratio = ZkConstants.DEFAULT_RATIO;
        try {
            zratio = configService.getRatio();
        }catch (Exception e){
            ApiLogger.error("get zk config error! key:", e);
        }
        if (ratio >= zratio){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        //调用网关
        MessageUser fromUser = new MessageUser();
        fromUser.setId("1000000010");
        MessageUser toUser = new MessageUser();
        toUser.setId("100001");
        JSONObject ext = new JSONObject();
        ext.put("lever", 1);//加油棒效果
        boolean isSucess = MessageUtil.sendMsg(MessageType.fuelRod, fromUser, toUser, ext);
        System.out.println(isSucess);
    }
}
