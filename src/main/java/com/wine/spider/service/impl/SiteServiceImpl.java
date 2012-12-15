package com.wine.spider.service.impl;

import com.wine.spider.entity.ItemEntity;
import com.wine.spider.entity.ListEntity;
import com.wine.spider.entity.SearchEntity;
import com.wine.spider.entity.SiteEntity;
import com.wine.spider.repository.SearchDao;
import com.wine.spider.repository.SiteDao;
import com.wine.spider.select.Select;
import com.wine.spider.service.ItemService;
import com.wine.spider.service.ListService;
import com.wine.spider.service.SearchService;
import com.wine.spider.service.SiteService;
import com.wine.spider.util.UUIDUtil;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Date: 11/27/12
 * Time: 12:01 AM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class SiteServiceImpl implements SiteService {
    private final static Logger logger = LoggerFactory.getLogger(SiteServiceImpl.class);
    @Autowired
    private SiteDao siteDao;
    @Autowired
    private SearchService searchService;
    @Autowired
    private ListService listService;
    @Autowired
    private ItemService itemService;
    @Resource(name="selectMap")
    private Map<String,Map<String,Select>> selectMap;
    private static final Random random = new Random();
    @Transactional
    @Override
    public SiteEntity save(SiteEntity entity) {
        Assert.notNull(entity);
        if(entity.getId() == null){
            entity.setUuid(UUIDUtil.random());
            entity.setGmtCreate(new Date());
        }
        entity.setGmtModified(new Date());
        siteDao.save(entity);
        return entity;
    }
    @Transactional
    public SiteEntity addSearch(Long id,SearchEntity searchEntity){
        if(searchEntity.getId() == null){
            searchEntity.setUuid(UUIDUtil.random());
            searchEntity.setGmtCreate(new Date());
        }else {
            searchEntity =  searchService.get(searchEntity.getId());
        }
        searchEntity.setGmtModified(new Date());
        SiteEntity siteEntity = siteDao.get(id);
        siteEntity.addSearchEntity(searchEntity);
        return siteDao.save(siteEntity);
    }

    @Override
    public List<SiteEntity> list() {
        return siteDao.list();
    }

    @Override
    public SiteEntity get(Long id) {
        Assert.notNull(id);
        return siteDao.get(id);
    }

    @Override
    public void build(Long id) {
        SiteEntity siteEntity = siteDao.get(id);
        List<SearchEntity> searchEntityList = siteEntity.getSearchEntityList();
        if (searchEntityList ==null || searchEntityList.isEmpty()){
            return;
        }

        for (SearchEntity searchEntity : searchEntityList){
            String selectName = searchEntity.getSelectName();
            if(StringUtils.isBlank(selectName)){
                continue;
            }
            if(!selectMap.containsKey(selectName)){
                logger.error("找不到selectName。searchId:",searchEntity.getId());
                continue;
            }
            Map<String,Select> sm = selectMap.get(selectName);
            Select slist = sm.get("list");
            Select sitem = sm.get("item");
            Document doc = null;
            try {
                doc = Jsoup.parse(new URL(searchEntity.getUrl()), 5000);
            } catch (Exception e) {
                logger.error("访问搜索页面失败！searchId:"+searchEntity.getId());
            }
            searchEntity.setHtml(doc.html());
            searchService.save(searchEntity);

            List<ListEntity> lists = slist.execute(doc,searchEntity);
            for (ListEntity listEntity : lists){
                listService.save(listEntity);
            }
            for (ListEntity listEntity : lists){
                try {
                    doc = Jsoup.parse(new URL(listEntity.getUrl()), 5000);
                    randomSleep(siteEntity);
                } catch (Exception e) {
                    logger.error("访问列表页面失败！searchId:"+searchEntity.getId());
                }
                listEntity.setHtml(doc.html());
                listService.save(listEntity);
                List<ItemEntity> items = sitem.execute(doc,listEntity);
                for (ItemEntity itemEntity:items){
                    try {
                        doc = Jsoup.parse(new URL(itemEntity.getUrl()), 5000);
                        randomSleep(siteEntity);
                    } catch (Exception e) {
                        logger.error("访问Item页面失败！searchId:"+searchEntity.getId());
                    }
                    itemEntity.setHtml(doc.html());
                    itemService.save(itemEntity);

                }
            }
        }
    }

   private void randomSleep(SiteEntity siteEntity){
    if (siteEntity.getRate() != null && siteEntity.getRate() != 0){
        long s = siteEntity.getRate();
        if(siteEntity.getRandom() != null && siteEntity.getRandom() != 0){
            s = s*random.nextInt(siteEntity.getRandom());
        }
        try {
            Thread.sleep(Long.valueOf(s));
        } catch (InterruptedException e) {
            logger.error("sleep error!",e);
        }
    }
   }
}
