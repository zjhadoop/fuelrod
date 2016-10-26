package com.zb.api.fuelrod.service;

import com.zb.api.commons.config.Config;
import com.zb.api.fuelrod.constants.ZkConstants;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * ConfigServiceImpl
 *
 * @author zj
 * @date 2016/8/23
 */
@Service("configService")
public class ConfigServiceImpl implements ConfigService{

    @Resource(name = "configFuelRod")
    private Config config;

    /**
     * 获取比率 -- 网管下发点赞数基数
     * @return
     */
    @Override
    public double getRatio() {
        try {
            return Double.parseDouble(config.get(ZkConstants.FUELROD_RATIO));
        }catch (Exception e){
            return ZkConstants.DEFAULT_RATIO;
        }
    }
}
