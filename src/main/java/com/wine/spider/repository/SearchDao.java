package com.wine.spider.repository;

import com.wine.spider.entity.SearchEntity;

/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Date: 12/4/12
 * Time: 3:51 PM
 * To change this template use File | Settings | File Templates.
 */
public interface SearchDao {
    SearchEntity save(SearchEntity entity);
}
