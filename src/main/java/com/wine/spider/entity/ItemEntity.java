package com.wine.spider.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Date: 11/25/12
 * Time: 8:51 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "SPIDER_ITEM")
public class ItemEntity extends BaseEntity {
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE }, optional = true)
    @JoinColumn(name="LIST_ID")
    @JsonBackReference
    private ListEntity listEntity;
    @Column(name = "URL")
    private String url;
    @Column(name = "SUCCESS")
    private Boolean success;
    @Column(name = "HTML")
    private String html;
    @Column(name = "DATA_JSON")
    private String dataJson;
    @Column(name = "DATA_GROOVY")
    private String dataGroovy;

    public ListEntity getListEntity() {
        return listEntity;
    }

    public void setListEntity(ListEntity listEntity) {
        this.listEntity = listEntity;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getDataJson() {
        return dataJson;
    }

    public void setDataJson(String dataJson) {
        this.dataJson = dataJson;
    }

    public String getDataGroovy() {
        return dataGroovy;
    }

    public void setDataGroovy(String dataGroovy) {
        this.dataGroovy = dataGroovy;
    }
}
