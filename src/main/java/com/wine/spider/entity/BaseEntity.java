package com.wine.spider.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Date: 11/25/12
 * Time: 7:42 PM
 * To change this template use File | Settings | File Templates.
 */
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    protected Long id;
    @Column(name = "UUID", updatable = false, nullable = false, unique = true)
    protected String uui;
    @Column(name = "GMT_CREATE", updatable = false, nullable = false)
    protected Date gmtCreate;
    @Column(name = "GMT_MODIFIED", nullable = false)
    protected Date gmtModified;
    @Column(name = "CREATOR")
    protected String creator;
    @Column(name = "MODIFIER")
    protected String modifier;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUui() {
        return uui;
    }

    public void setUui(String uui) {
        this.uui = uui;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
}
