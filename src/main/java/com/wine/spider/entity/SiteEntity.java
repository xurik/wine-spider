package com.wine.spider.entity;

import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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

    @Column(name = "SITE")
    private String site;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "RATE")
    private Long rate;
    @Column(name = "RANDOM")
    private Long random;

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
}
