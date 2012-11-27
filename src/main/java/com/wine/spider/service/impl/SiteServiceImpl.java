package com.wine.spider.service.impl;

import com.wine.spider.entity.SiteEntity;
import com.wine.spider.repository.SiteDao;
import com.wine.spider.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Date: 11/27/12
 * Time: 12:01 AM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class SiteServiceImpl implements SiteService{
    @Autowired
    private SiteDao siteDao;
    @Transactional
    @Override
    public SiteEntity save(SiteEntity entity) {
        return siteDao.save(entity);
    }
}
