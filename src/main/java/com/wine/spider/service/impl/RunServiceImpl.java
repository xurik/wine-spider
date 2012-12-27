package com.wine.spider.service.impl;

import com.wine.spider.entity.*;
import com.wine.spider.select.Select;
import com.wine.spider.service.*;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Date: 12-12-17
 * Time: 上午10:00
 * To change this template use File | Settings | File Templates.
 */
@Service
public class RunServiceImpl implements RunService{
    private static final Logger logger = LoggerFactory.getLogger(RunServiceImpl.class);
    @Autowired
    private SiteService siteService;
    @Autowired
    private SearchService searchService;
    @Autowired
    private ListService listService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private WineDataService wineDataService;
    @Resource(name="selectMap")
    private Map<String,Map<String,Select>> selectMap;

    @Override
    public void runAll() {
        List<SiteEntity> list = siteService.list();
        if (list == null || list.isEmpty()){
            return;
        }
        runBySite(list);
    }

    @Override
    public void runBySite(Long id) {
        SiteEntity siteEntity = siteService.get(id);
        if (siteEntity == null)
            return;
        runBySearch(siteEntity.getSearchEntityList());
    }

    @Override
    public void runBySite(List<SiteEntity> list) {
        if (list == null || list.isEmpty()){
            return;
        }
        for (SiteEntity siteEntity : list){
            runBySearch(siteEntity.getSearchEntityList());
        }
    }

    @Override
    public void runBySearch(List<SearchEntity> list) {
        if (list == null || list.isEmpty()){
            return;
        }
        for (SearchEntity searchEntity : list){
            runByList(searchEntity.getListEntityList());
        }
    }

    @Override
    public void runByList(List<ListEntity> list) {
        if (list == null || list.isEmpty()){
            return;
        }
        for (ListEntity listEntity : list){
            runByItem(listEntity.getItemEntityList());
        }
    }

    @Override
    public void runByItem(List<ItemEntity> list) {
        if (list == null || list.isEmpty()){
            return;
        }
        for (ItemEntity itemEntity : list){
            run(itemEntity);
        }
    }

    private void run(ItemEntity itemEntity){
        SearchEntity searchEntity =itemEntity.getListEntity().getSearchEntity();
        SiteEntity siteEntity = searchEntity.getSiteEntity();
        String html = itemEntity.getHtml();
        if (StringUtils.isBlank(html)){
            logger.error("html is null! itemId:{}",itemEntity.getId());
        }
        String selectName = searchEntity.getSelectName();
        Select<WineDataEntity,ItemEntity> select = selectMap.get(selectName).get("data");
        List<WineDataEntity>list = select.execute(Jsoup.parse(html),itemEntity);
        for (WineDataEntity wineDataEntity:list){
            wineDataService.save(wineDataEntity);
        }
        Jsoup.parse(html);
    }
}
