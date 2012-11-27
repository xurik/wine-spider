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
@Table(name = "SPIDER_SEARCH")
public class SearchEntity extends BaseEntity {
    @Column(name = "SITE")
    private String site;
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

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
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
