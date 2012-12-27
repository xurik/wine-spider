package com.wine.spider.web.controller;

import com.wine.spider.service.RunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Date: 12-12-27
 * Time: 上午7:59
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/run")
public class RunController {
    @Autowired
    private RunService runService;
    @RequestMapping("/site/{id}")
    public String run(@PathVariable("id")Long id,Model model){
        runService.runBySite(id);
        return "index";
    }

    @RequestMapping("/all")
    public String run(Model model){
        runService.runAll();
        return "index";
    }
}
