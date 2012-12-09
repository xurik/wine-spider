package com.wine.spider.repository.impl;

import com.wine.spider.entity.ItemEntity;
import com.wine.spider.entity.ListEntity;
import com.wine.spider.repository.ItemDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Date: 12/9/12
 * Time: 10:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class ItemDaoImpl implements ItemDao{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public ItemEntity save(ItemEntity entity) {
        return entityManager.merge(entity);
    }
    public ItemEntity get(Long id){
        String qlString = "select t from ItemEntity t where t.id = :id";
        Query query = entityManager.createQuery(qlString);
        query.setParameter("id",id);
        return (ItemEntity)query.getSingleResult();
    }

    public List<ItemEntity> list() {
        String qlString = "select t from ItemEntity t order by t.id";
        return entityManager.createQuery(qlString).getResultList();
    }
}
