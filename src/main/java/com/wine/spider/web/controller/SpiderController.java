package com.wine.spider.web.controller;

import com.wine.spider.entity.SearchEntity;
import com.wine.spider.entity.SiteEntity;
import com.wine.spider.repository.SearchDao;
import com.wine.spider.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

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
    public String test(Model model) {
        SiteEntity siteEntity = siteService.save(new SiteEntity());

        List<SiteEntity> list = siteService.list();
        model.addAttribute("list",list);
        return "test";
    }
}
