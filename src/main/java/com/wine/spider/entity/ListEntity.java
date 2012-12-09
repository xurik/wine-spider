package com.wine.spider.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Date: 11/26/12
 * Time: 12:53 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "SPIDER_LIST")
public class ListEntity extends BaseEntity {
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH }, optional = true)
    @JoinColumn(name="SEARCH_ID")
    @JsonBackReference
    private SearchEntity searchEntity;
    @Column(name = "URI")
    private String uri;
    @Column(name = "SUCCESS")
    private Boolean success;
    @Column(name = "HTML")
    private String html;
    @Column(name = "ITEM_GROOVY")
    private String itemGroovy;
    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "listEntity")
    private List<ItemEntity> itemEntityList = new ArrayList<ItemEntity>();

    public SearchEntity getSearchEntity() {
        return searchEntity;
    }

    public void setSearchEntity(SearchEntity searchEntity) {
        this.searchEntity = searchEntity;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getItemGroovy() {
        return itemGroovy;
    }

    public void setItemGroovy(String itemGroovy) {
        this.itemGroovy = itemGroovy;
    }

    public List<ItemEntity> getItemEntityList() {
        return itemEntityList;
    }

    public void setItemEntityList(List<ItemEntity> itemEntityList) {
        this.itemEntityList = itemEntityList;
    }

    public void addItemEntityList(ItemEntity itemEntity) {
        itemEntity.setListEntity(this);
        itemEntityList.add(itemEntity);
    }
}
