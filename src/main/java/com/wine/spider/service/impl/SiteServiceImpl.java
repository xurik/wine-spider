package com.wine.spider.service.impl;

import com.wine.spider.entity.SearchEntity;
import com.wine.spider.entity.SiteEntity;
import com.wine.spider.repository.SearchDao;
import com.wine.spider.repository.SiteDao;
import com.wine.spider.service.SiteService;
import com.wine.spider.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Date: 11/27/12
 * Time: 12:01 AM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class SiteServiceImpl implements SiteService {
    @Autowired
    private SiteDao siteDao;
    @Autowired
    private SearchDao searchDao;
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
            searchEntity =  searchDao.get(searchEntity.getId());
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
}
