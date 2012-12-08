package com.wine.spider.service.impl;

import com.wine.spider.entity.SearchEntity;
import com.wine.spider.entity.SiteEntity;
import com.wine.spider.repository.SearchDao;
import com.wine.spider.repository.SiteDao;
import com.wine.spider.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        SearchEntity searchEntity = new SearchEntity();
        searchEntity.setGmtCreate(new Date());
        searchEntity.setGmtModified(new Date());
        searchEntity.setUui(UUID.randomUUID().toString().replace("-", ""));
        entity.addSearchEntity(searchEntity);
        entity.setName("中文");
        siteDao.save(entity);
        return entity;
    }

    @Override
    public List<SiteEntity> list() {
        return siteDao.list();
    }
}
