package com.wine.spider.repository.impl;

import com.wine.spider.entity.SearchEntity;
import com.wine.spider.repository.SearchDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Date: 12/4/12
 * Time: 3:51 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class SearchDaoImpl implements SearchDao{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public SearchEntity save(SearchEntity entity) {
        entity.setGmtCreate(new Date());
        entity.setGmtModified(new Date());
        entity.setUui(UUID.randomUUID().toString().replace("-", ""));
        return entityManager.merge(entity);
    }
}
