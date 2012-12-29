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
import com.wine.spider.util.BeanCopyUtil;
import com.wine.spider.util.RandomSleepUtil;
import com.wine.spider.util.UUIDUtil;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
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
public class SiteServiceImpl implements SiteService, ApplicationContextAware {
    private final static Logger logger = LoggerFactory.getLogger(SiteServiceImpl.class);
    @Autowired
    private SiteDao siteDao;
    @Autowired
    private SearchService searchService;
    @Autowired
    private ListService listService;
    @Autowired
    private ItemService itemService;
    private ApplicationContext applicationContext;

    @Transactional
    @Override
    public SiteEntity save(SiteEntity entity) {
        Assert.notNull(entity);
        if (entity.getId() == null) {
            entity.setUuid(UUIDUtil.random());
            entity.setGmtCreate(new Date());
        } else {
            entity = BeanCopyUtil.copyWithoutNull(siteDao.get(entity.getId()), entity);
        }
        entity.setGmtModified(new Date());
        siteDao.save(entity);
        return entity;
    }

    @Transactional
    public SiteEntity addSearch(Long id, SearchEntity searchEntity) {
        if (searchEntity.getId() == null) {
            searchEntity.setUuid(UUIDUtil.random());
            searchEntity.setGmtCreate(new Date());
        } else {
            searchEntity = BeanCopyUtil.copyWithoutNull(searchService.get(searchEntity.getId()), searchEntity);
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
        if (searchEntityList == null || searchEntityList.isEmpty()) {
            return;
        }
        for (SearchEntity searchEntity : searchEntityList) {
            try {
                build(searchEntity);
            } catch (Exception e){
                logger.error("error!searchId:{}",searchEntity.getId(),e);
            }

        }
    }

    private void build(SearchEntity searchEntity){
        SiteEntity siteEntity = searchEntity.getSiteEntity();
        String selectName = searchEntity.getSelectName();
        if (StringUtils.isBlank(selectName)) {
            return;
        }
        Map<String, Select> sm = null;
        try {
            sm = applicationContext.getBean(searchEntity.getSelectName(), Map.class);
        } catch (Exception e) {
            logger.error("找不到selectName。searchId:", searchEntity.getId(), e);
            return;
        }
        Document doc = null;
        try {
            doc = Jsoup.parse(new URL(searchEntity.getUrl()), 5000);
        } catch (Exception e) {
            logger.error("访问搜索页面失败！searchId:" + searchEntity.getId());
        }
        searchEntity.setHtml(doc.html());
        searchService.save(searchEntity);
        List<ListEntity> lists = sm.get("list").execute(doc, searchEntity);
        for (ListEntity listEntity : lists) {
            try {
                listEntity.setSearchEntity(searchEntity);
                build(listEntity);
            } catch (Exception e){
                logger.error("error!listId:{}",listEntity.getId(),e);
            }
        }
    }

    private void build(ListEntity listEntity){
        SearchEntity searchEntity = listEntity.getSearchEntity();
        SiteEntity siteEntity = searchEntity.getSiteEntity();
        ListEntity listOld = listService.findByUrl(listEntity.getUrl());
        if (listOld != null) {
            listEntity.setId(listOld.getId());
        }
        Document doc = null;
        try {
            doc = Jsoup.parse(new URL(listEntity.getUrl()), 5000);
            randomSleep(siteEntity);
        } catch (Exception e) {
            logger.error("访问列表页面失败！searchId:" + searchEntity.getId());
            return;
        }
        listEntity.setHtml(doc.html());
        listService.save(listEntity);
        Map<String, Select> sm = null;
        try {
            sm = applicationContext.getBean(searchEntity.getSelectName(), Map.class);
        } catch (Exception e) {
            logger.error("找不到selectName。searchId:", searchEntity.getId(), e);
            return;
        }
        List<ItemEntity> items = sm.get("item").execute(doc, listEntity);
        for (ItemEntity itemEntity : items) {
            try {
                itemEntity.setListEntity(listEntity);
                build(itemEntity);
            } catch (Exception e){
                logger.error("error!itemId:{}",itemEntity.getId(),e);
            }
        }
    }

    private void build(ItemEntity itemEntity){
        SearchEntity searchEntity = itemEntity.getListEntity().getSearchEntity();
        SiteEntity siteEntity = searchEntity.getSiteEntity();
        Document doc = null;
        try {
            doc = Jsoup.parse(new URL(itemEntity.getUrl()), 5000);
            randomSleep(siteEntity);
        } catch (Exception e) {
            logger.error("访问Item页面失败！searchId:" + searchEntity.getId());
            return;
        }
        itemEntity.setHtml(doc.html());
        ItemEntity tmp = itemService.findByUrl(itemEntity.getUrl());
        if (tmp != null) {
            itemEntity.setId(tmp.getId());
        }
        itemService.save(itemEntity);
    }

    private void randomSleep(SiteEntity siteEntity) {
        Integer rate = siteEntity.getRate();
        if (rate == null || rate == 0) {
            return;
        }
        Integer random = siteEntity.getRandom();
        if (random == null) {
            random = 10;
        }
        RandomSleepUtil.sleep(rate, random);

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
