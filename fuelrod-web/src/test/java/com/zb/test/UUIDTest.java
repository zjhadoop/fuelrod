package com.zb.test;

import com.zb.api.commons.uuid.service.UuidFactory;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Random;

/**
 * UUIDTest
 *
 * @author zj
 * @date 2016/9/12
 */
public class UUIDTest extends TestUtil{

    @Resource(name = "fuelrodFactory")
    private UuidFactory factory;

    /*@Test
    public void testUUIDByTime(){
        int count = 0;
        int count2 = 0;
        while (count++ < 10000000) {
            long l = factory.assignId();
            String string =  String.valueOf(l);
            if (string.length() < 19) {
                System.out.println(l);
            }
            if (string.length() == 19)count2++;
        }
        System.out.println(count2);
    }*/

    @Test
    public void testUuid(){
        int count = 10000;
        while (count-- >= 0) {
            long uid = getUuid();
            System.out.println(uid);
        }

    }

    private long getUuid(){
        long l = factory.assignId();
        if (l <= 0) {
            try {
                Thread.sleep(200);
                l = factory.assignId();
                if (l <= 0){
                    Thread.sleep(200);
                    l = factory.assignId();
                }
            } catch (Exception e) {
            }
        }
        return l;
    }
}
