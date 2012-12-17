package com.wine.spider.service.impl;

import com.wine.spider.entity.ItemEntity;
import com.wine.spider.entity.ListEntity;
import com.wine.spider.entity.SearchEntity;
import com.wine.spider.entity.SiteEntity;
import com.wine.spider.select.Select;
import com.wine.spider.service.*;
import org.springframework.beans.factory.annotation.Autowired;

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
public class RunServiceImpl implements RunService{
    @Autowired
    private SiteService siteService;
    @Autowired
    private SearchService searchService;
    @Autowired
    private ListService listService;
    @Autowired
    private ItemService itemService;
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
        SiteEntity siteEntity = itemEntity.getListEntity().getSearchEntity().getSiteEntity();
        String html = itemEntity.getHtml();

    }
}
