package com.zb.api.fuelrod.dao.impl;

import com.zb.api.commons.ApiLogger;
import com.zb.api.fuelrod.dao.core.CommonDao;
import com.zb.api.fuelrod.po.FuelRod;
import org.springframework.stereotype.Component;

/**
 * by zj
 */
@Component(value = "fuelRodDao")
public class FuelRodDao extends CommonDao<FuelRod>{

    private static String PACKAGE_NAME = "com.zb.api.fuelrod.po.FuelRod";

    /**
     * 保存点赞记录
     * @param fuelRod
     * @return
     */
    public int insert(FuelRod fuelRod){
        return super.insert(PACKAGE_NAME + ".insert", fuelRod);
    }

    /**
     * 查询点赞记录
     * @param liveId
     * @return
     */
    public int get(long liveId){
        int num = 0;
        try {
            num = super.findOneByField(PACKAGE_NAME + ".query", liveId);
        }catch (Exception e){
            ApiLogger.info("db fuel_rod no data! liveId:" + liveId);
        }
        return num > 0 ? num : 0;
    }
}
