package com.bridge.record.controller;

import com.bridge.common.AjaxResult;
import com.bridge.common.PageRequest;
import com.bridge.common.PageResponse;
import com.bridge.record.model.FollowupRecord;
import com.bridge.record.service.FollowupRecordServiceImpl;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/followup")
public class FollowupController {
    @Autowired
    private FollowupRecordServiceImpl service;
    @PostMapping("list")
    public AjaxResult<PageResponse<FollowupRecord>> getPageList(
        @RequestParam String fullname,
        @RequestBody PageRequest page
    ){
        Iterable<FollowupRecord> its= service.getByFullname(fullname);
        PageInfo<FollowupRecord> pageInfo = new PageInfo<>(Lists.newArrayList(its));
        PageResponse<FollowupRecord> responseVo=new PageResponse<>();
        responseVo.setPageData(pageInfo.getList());
        responseVo.setTotal(pageInfo.getTotal());

        return AjaxResult.success(responseVo);
}
    @PostMapping("new")
    public AjaxResult<FollowupRecord> addnew(){
        return AjaxResult.success(new FollowupRecord());
    }
    @PostMapping("save")
    public AjaxResult<Integer> save(@RequestBody FollowupRecord record){
        return AjaxResult.success(service.save(record));
    }
    @GetMapping("/{id}")
    public AjaxResult<FollowupRecord> selectByPrimaryKey(@PathVariable Long id){
        
        return AjaxResult.success(service.selectByPrimaryKey(id));
    }
    @DeleteMapping("/{id}")
    public AjaxResult<Integer> deleteByPrimaryKey(@PathVariable Long id){
        
        return AjaxResult.success(service.deleteByPrimaryKey(id));
    }
    
}
