package com.wine.spider.service.impl;

import com.wine.spider.entity.ItemEntity;
import com.wine.spider.entity.ListEntity;
import com.wine.spider.repository.ItemDao;
import com.wine.spider.service.ItemService;
import com.wine.spider.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Date: 12-12-15
 * Time: 下午5:23
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemDao itemDao;
    @Override
    @Transactional
    public ItemEntity save(ItemEntity entity) {
        Assert.notNull(entity);
        if(entity.getId() == null){
            entity.setUuid(UUIDUtil.random());
            entity.setGmtCreate(new Date());
        }
        entity.setGmtModified(new Date());
        return itemDao.save(entity) ;
    }
}
