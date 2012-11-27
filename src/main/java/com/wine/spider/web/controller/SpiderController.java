package com.wine.spider.web.controller;

import com.wine.spider.entity.SiteEntity;
import com.wine.spider.repository.SiteDao;
import com.wine.spider.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Date: 11/25/12
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class SpiderController {
    @Autowired
    private SiteService siteService;
    @RequestMapping("/test")
    public String test(){
        siteService.save(new SiteEntity());
        return "test";
    }
}
