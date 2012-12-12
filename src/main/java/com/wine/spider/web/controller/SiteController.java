package com.wine.spider.web.controller;

import com.wine.spider.entity.SiteEntity;
import com.wine.spider.select.Select;
import com.wine.spider.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Date: 12/8/12
 * Time: 4:38 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/site")
public class SiteController {
    @Autowired
    private SiteService siteService;
    @Resource(name="selectMap")
    private Map<String,List<Select>> selectMap;
    @RequestMapping(value = "/index")
    public String index(){
        return "site";
    }
    @RequestMapping(value = "/save")
    public void save(SiteEntity entity){
        siteService.save(entity);
    }
    @RequestMapping(value = "/{id}")
    public void get(@PathVariable("id")Long id,Model model){
        model.addAttribute("item",siteService.get(id));
    }
    @RequestMapping(value = "/list")
    public void list(Model model){
        model.addAttribute("rows",siteService.list());
    }
}
