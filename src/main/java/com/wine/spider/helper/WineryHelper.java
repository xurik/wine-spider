package com.wine.spider.helper;

import org.apache.commons.lang.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Date: 12-12-24
 * Time: 下午9:58
 * To change this template use File | Settings | File Templates.
 */
public class WineryHelper {
    private static String[] matching ;
    static {
        matching =new String[]{"酒庄","庄园","城堡","山庄"};
    }
    public static String fromName(String name){
        String winery="";
        for (String m : matching) {
            if (StringUtils.contains(name,m)){
                int index = StringUtils.indexOf(name,m);
                winery = StringUtils.substring(name,0,index)+m;
            }
        }
        if (StringUtils.isBlank(winery)){
            winery = StringUtils.substring(name,0,StringUtils.indexOf(name,"葡萄酒"));
        }
        return winery;
    }

    public static void main(String[] args) {
        System.out.println(WineryHelper.fromName("xxx酒葡萄酒"));
    }
}
