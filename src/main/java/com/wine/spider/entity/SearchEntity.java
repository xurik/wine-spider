package com.wine.spider.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Date: 11/25/12
 * Time: 8:51 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "SPIDER_SEARCH")
public class SearchEntity extends BaseEntity {
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH }, optional = true)
    @JoinColumn(name="SITE_ID")
    @JsonBackReference
    private SiteEntity siteEntity;
    @Column(name = "URI")
    private String uri;
    @Column(name = "SUCCESS")
    private Boolean success;
    @Column(name = "HTML")
    private String html;
    @Column(name = "LIST_BEAN")
    private String listBean;
    @Column(name = "ITEM_BEAN")
    private String itemBean;
    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "searchEntity")
    private List<ListEntity> listEntityList = new ArrayList<ListEntity>();

    public SiteEntity getSiteEntity() {
        return siteEntity;
    }

    public void setSiteEntity(SiteEntity siteEntity) {
        this.siteEntity = siteEntity;
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

    public String getListBean() {
        return listBean;
    }

    public void setListBean(String listBean) {
        this.listBean = listBean;
    }

    public String getItemBean() {
        return itemBean;
    }

    public void setItemBean(String itemBean) {
        this.itemBean = itemBean;
    }

    public List<ListEntity> getListEntityList() {
        return listEntityList;
    }

    public void setListEntityList(List<ListEntity> listEntityList) {
        this.listEntityList = listEntityList;
    }

    public void addListEntityList(ListEntity listEntity){
        listEntity.setSearchEntity(this);
        listEntityList.add(listEntity);
    }
}
