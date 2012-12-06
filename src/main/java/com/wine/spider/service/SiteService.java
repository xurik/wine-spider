package com.wine.spider.service;

import com.wine.spider.entity.SiteEntity;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Date: 11/27/12
 * Time: 12:00 AM
 * To change this template use File | Settings | File Templates.
 */
public interface SiteService {
    SiteEntity save(SiteEntity entity);
    List<SiteEntity> list();
}
