package com.wine.spider.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Date: 11/25/12
 * Time: 8:08 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "SPIDER_SITE")
public class SiteEntity extends BaseEntity {
    @Column(name = "NAME")
    private String name;
    @Column(name = "SITE")
    private String site;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "RATE")
    private Long rate;
    @Column(name = "RANDOM")
    private Long random;
    @Column(name = "NOTE")
    private String note;
    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "siteEntity")
    //这里配置关系，并且确定关系维护端和被维护端。mappBy表示关系被维护端，只有关系端有权去更新外键。这里还有注意OneToMany默认的加载方式是赖加载。当看到设置关系中最后一个单词是Many，那么该加载默认为懒加载
    private List<SearchEntity> searchEntityList = new ArrayList<SearchEntity>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getRate() {
        return rate;
    }

    public void setRate(Long rate) {
        this.rate = rate;
    }

    public Long getRandom() {
        return random;
    }

    public void setRandom(Long random) {
        this.random = random;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<SearchEntity> getSearchEntityList() {
        return searchEntityList;
    }

    public void setSearchEntityList(List<SearchEntity> searchEntityList) {
        this.searchEntityList = searchEntityList;
    }

    public void addSearchEntity(SearchEntity searchEntity) {
        searchEntity.setSiteEntity(this); //用关系维护端来维护关系
        this.searchEntityList.add(searchEntity);
    }
}
