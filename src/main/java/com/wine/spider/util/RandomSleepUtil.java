package com.wine.spider.util;

import com.wine.spider.entity.SiteEntity;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Date: 12-12-27
 * Time: 下午5:36
 * To change this template use File | Settings | File Templates.
 */
public class RandomSleepUtil {
    private static final Random random = new Random();
    public static void sleep(int rate,int ran){
            long  s = rate*random.nextInt(ran);
            try {
                Thread.sleep(Long.valueOf(s));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
}
