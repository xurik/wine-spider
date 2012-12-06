package com.wine.spider.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

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
    private SiteEntity siteEntity;
    @Column(name = "URI")
    private String uri;
    @Column(name = "CT")
    private Integer count;
    @Column(name = "SUCCESS")
    private Integer success;
    @Column(name = "FAIL")
    private Integer fail;
    @Column(name = "HTML")
    private String html;
    @Column(name = "LIST_GROOVY")
    private String listGroovy;
    @Column(name = "ITEM_GROOVY")
    private String itemGroovy;
    @Column(name = "DATA_GROOVY")
    private String dataGroovy;

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public Integer getFail() {
        return fail;
    }

    public void setFail(Integer fail) {
        this.fail = fail;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getListGroovy() {
        return listGroovy;
    }

    public void setListGroovy(String listGroovy) {
        this.listGroovy = listGroovy;
    }

    public String getItemGroovy() {
        return itemGroovy;
    }

    public void setItemGroovy(String itemGroovy) {
        this.itemGroovy = itemGroovy;
    }

    public String getDataGroovy() {
        return dataGroovy;
    }

    public void setDataGroovy(String dataGroovy) {
        this.dataGroovy = dataGroovy;
    }
}
