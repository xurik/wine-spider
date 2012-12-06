package com.wine.spider.repository.impl;

import com.wine.spider.entity.SiteEntity;
import com.wine.spider.repository.SiteDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Date: 11/26/12
 * Time: 7:51 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class SiteDaoImpl implements SiteDao {
    @PersistenceContext
    private EntityManager entityManager;

    public SiteEntity save(SiteEntity entity) {
        entity.setGmtCreate(new Date());
        entity.setGmtModified(new Date());
        entity.setUui(UUID.randomUUID().toString().replace("-", ""));
        entityManager.merge(entity);
        return entity;
    }

    public List<SiteEntity> list() {
        String qlString = "select t from SiteEntity t order by t.id";
        return entityManager.createQuery(qlString).getResultList();
    }

}
