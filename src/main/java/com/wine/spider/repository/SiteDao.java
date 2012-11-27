package com.wine.spider.repository;

import com.wine.spider.entity.SiteEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Date: 11/26/12
 * Time: 7:44 PM
 * To change this template use File | Settings | File Templates.
 */
public interface SiteDao{
    SiteEntity save(SiteEntity entity);

}
