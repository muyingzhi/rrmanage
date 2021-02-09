package com.bridge.record.controller;

import java.util.List;

import com.bridge.common.AjaxResult;
import com.bridge.common.PageRequest;
import com.bridge.common.PageResponse;
import com.bridge.record.model.BaseinfoRecord;
import com.bridge.record.service.BaseinfoRecordServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/baseinfo")
public class BaseinfoController {
    @Autowired
    private BaseinfoRecordServiceImpl baseinfoService;
    @PostMapping("/list")
    public AjaxResult<PageResponse<BaseinfoRecord>> getPageList(
        @RequestParam String fullname,
        @RequestBody PageRequest page
        ){
            Iterable<BaseinfoRecord> its= baseinfoService.getByFullname(fullname);
            PageInfo<BaseinfoRecord> pageInfo = new PageInfo<>(Lists.newArrayList(its));
            PageResponse<BaseinfoRecord> responseVo=new PageResponse<>();
            responseVo.setPageData(pageInfo.getList());
            responseVo.setTotal(pageInfo.getTotal());

            return AjaxResult.success(responseVo);
    }
    @PostMapping("/new")
    public AjaxResult<BaseinfoRecord> addnew(){
        return AjaxResult.success(new BaseinfoRecord());
    }
    @PostMapping("/save")
    public AjaxResult<Integer> save(@RequestBody BaseinfoRecord record){
        
        return AjaxResult.success(baseinfoService.save(record));
    }
    @GetMapping("/{id}")
    public AjaxResult<BaseinfoRecord> selectByPrimaryKey(@PathVariable Long id){
        
        return AjaxResult.success(baseinfoService.selectByPrimaryKey(id));
    }
    @DeleteMapping("/{id}")
    public AjaxResult<Integer> deleteByPrimaryKey(@PathVariable Long id){
        
        return AjaxResult.success(baseinfoService.deleteByPrimaryKey(id));
    }
    
    
}
