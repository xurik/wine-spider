package com.wine.spider.repository.impl;

import com.wine.spider.entity.ListEntity;
import com.wine.spider.entity.SearchEntity;
import com.wine.spider.repository.ListDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Date: 12/9/12
 * Time: 10:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class ListDaoImpl implements ListDao{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public ListEntity save(ListEntity entity) {
        return entityManager.merge(entity);
    }
    public ListEntity get(Long id){
        String qlString = "select t from ListEntity t where t.id = :id";
        Query query = entityManager.createQuery(qlString);
        query.setParameter("id",id);
        return (ListEntity)query.getSingleResult();
    }

    public List<ListEntity> list() {
        String qlString = "select t from ListEntity t order by t.id";
        return entityManager.createQuery(qlString).getResultList();
    }
}
