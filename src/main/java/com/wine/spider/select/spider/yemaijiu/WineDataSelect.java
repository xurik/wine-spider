package com.wine.spider.select.spider.yemaijiu;

import com.wine.spider.entity.ItemEntity;
import com.wine.spider.entity.WineDataEntity;
import com.wine.spider.select.Select;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Date: 12-12-17
 * Time: 上午10:23
 * To change this template use File | Settings | File Templates.
 */
public class WineDataSelect implements Select<WineDataEntity,ItemEntity> {
    @Override
    public List<WineDataEntity> execute(Document document, ItemEntity itemEntity) {
        List<WineDataEntity> list = new ArrayList<WineDataEntity>();
        WineDataEntity wineDataEntity = new WineDataEntity();

        String fullNameZh =document.select("div.promotionMiddleTop h1").text();

        wineDataEntity.setFullNameZh(fullNameZh);
        list.add(wineDataEntity) ;
        return list;
    }
}
