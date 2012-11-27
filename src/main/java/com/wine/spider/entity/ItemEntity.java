package com.wine.spider.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
    @Column(name = "LIST")
    private String list;
    @Column(name = "ITEM_ID")
    private String itemId;
    @Column(name = "SITE")
    private String site;
    @Column(name = "SEARCH")
    private String search;
    @Column(name = "URL")
    private String url;
    @Column(name = "CT")
    private Integer count;
    @Column(name = "SUCCESS")
    private Integer success;
    @Column(name = "FAIL")
    private Integer fail;
    @Column(name = "HTML")
    private String html;
    @Column(name = "DATA_JSON")
    private String dataJson;
    @Column(name = "DATA_GROOVY")
    private String dataGroovy;

    @Column(name = "XTYPE")
    private String type;

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
