package com.zb.api.fuelrod.constants;

/**
 * RedisConstans
 *
 * @author zj
 * @date 2016/8/18
 */
public class RedisConstans {

    /**
     * 加油棒计数器前缀
     */
    public static final String PLUGIN_FUELROD = "p_fr_";

    /**
     * 加油棒已下发计数器前缀
     */
    public static final String PLUGIN_FUELROD_SEND = "p_frs_";


    /**
     * 组装加油棒redis key
     * @param liveId
     * @return
     */
    public static String assembleFuelRod(long liveId) {
        return PLUGIN_FUELROD + liveId;
    }

    /**
     * 组装已下发加油棒redis key
     * @param liveId
     * @return
     */
    public static String assembleSendFuelRod(long liveId) {
        return PLUGIN_FUELROD_SEND + liveId;
    }
}
