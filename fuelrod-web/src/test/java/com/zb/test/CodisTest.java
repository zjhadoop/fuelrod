package com.zb.test;

import com.zb.api.fuelrod.constants.RedisConstans;
import com.zb.api.fuelrod.service.storage.PluginStorage;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * CodisTest
 *
 * @author zj
 * @date 2016/9/12
 */
public class CodisTest extends TestUtil{

    @Resource
    private PluginStorage pluginStorage;

    @Test
    public void testRedis(){
        String key = RedisConstans.assembleFuelRod(1473770030866l);//1473666334374l
        //long incy = pluginStorage.incy(key, 1);
        //System.out.println(incy);
        String s = pluginStorage.get(key);
        System.out.println(s);
    }

}
