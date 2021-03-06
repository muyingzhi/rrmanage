package com.bridge.record.controller;

import java.util.HashMap;
import java.util.Map;

import com.bridge.common.AjaxResult;
import com.bridge.record.service.BaseinfoRecordServiceImpl;
import com.bridge.record.service.FollowupRecordServiceImpl;
import com.bridge.record.service.PlanRecordServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stat")
public class StatController {
    @Autowired
    private BaseinfoRecordServiceImpl baseinfoService;
    @Autowired
	private PlanRecordServiceImpl planService;
    @Autowired
    private FollowupRecordServiceImpl followupService;
    @PostMapping("/list")
    public AjaxResult<Map<String,Integer>> getPageList(
        ){
            int baseinfoCount= baseinfoService.count();
            int followupCount= followupService.count();
            int planCount= planService.count();
            Map<String,Integer> result=new HashMap<>();
            result.put("baseinfo", baseinfoCount);
            result.put("followup", followupCount);
            result.put("plan", planCount);
            return AjaxResult.success(result);
    }
}
