package com.zb.test;

import com.zb.api.fuelrod.dao.impl.FuelRodDao;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * DbTest
 *
 * @author zj
 * @date 2016/9/13
 */
public class DbTest extends TestUtil{

    @Resource
    private FuelRodDao fuelRodDao;

    @Test
    public void testQuery(){
        long liveId = 147359403922226l;
        System.out.println(fuelRodDao.get(liveId));
    }

    public static void print(String a, String b){
        String key = a + b;
        synchronized (key){
            System.out.println(key);
        }
    }

    public static void main(String[] args) {
        String s1 = "jake";
        String s2 = "son";
        String s3 = s1 + s2;
        String s4 = s1 + s2;
        System.out.println(s3.intern() == s4.intern());
        System.out.println(s3.equals(s4));
    }
}
