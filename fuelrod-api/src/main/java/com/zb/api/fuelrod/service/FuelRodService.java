package com.zb.api.fuelrod.service;

/**
 * FuelRodService
 *
 * @author zj
 * @date 2016/8/18
 */
public interface FuelRodService {

    /**
     * 获取加油棒数量
     * @param liveId  直播ID
     * @return
     */
    int getFuelRod(long liveId);

    /**
     * 加油棒，点赞
     * @param userId    用户ID
     * @param roomId    房间ID
     * @param liveId    直播ID
     * @param count     点赞数
     * @return
     */
    boolean fuelRod(int userId, int roomId, long liveId, int count);
}
