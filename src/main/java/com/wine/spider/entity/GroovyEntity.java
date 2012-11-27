package com.wine.spider.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Date: 11/26/12
 * Time: 1:14 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "SPIDER_SCRIPT_GROOVY")
public class GroovyEntity extends BaseEntity {
    @Column(name = "CODE")
    private String code;
    @Column(name = "SCRIPT")
    private String script;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }
}
