package com.bridge.record.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/plan")
public class PlanController {
    @RequestMapping("list")
    public Object getPageList(){
        return null;
    }
    @RequestMapping("new")
    public Object addnew(){
        return null;
    }
    @RequestMapping("save")
    public Object save(){
        return null;
    }
    
}