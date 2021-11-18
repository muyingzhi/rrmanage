package com.bridge.record.controller;

import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.bridge.common.AjaxResult;
import com.bridge.common.PageRequest;
import com.bridge.common.PageResponse;
import com.bridge.record.model.BaseinfoRecord;
import com.bridge.record.service.BaseinfoRecordServiceImpl;
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
@RequestMapping("/api/baseinfo")
public class BaseinfoController {
    @Autowired
    private BaseinfoRecordServiceImpl baseinfoService;
    @PostMapping("/list")
    public AjaxResult<PageResponse<BaseinfoRecord>> getPageList(
        @RequestBody Map<String,String> params
        ){
            
        // int pageNum=0;int pageSize=10;
            PageRequest page = new PageRequest();
            page.setPageNum(Integer.valueOf(params.get("pageNum").toString()));
            page.setPageSize(Integer.valueOf(params.get("pageSize").toString()));
            Iterable<BaseinfoRecord> its= baseinfoService.getQuery(params.get("fullname"), params.get("source"));
            PageInfo<BaseinfoRecord> pageInfo = new PageInfo<>(Lists.newArrayList(its));
            PageResponse<BaseinfoRecord> responseVo=new PageResponse<>();
            responseVo.setPageData(pageInfo.getList());
            responseVo.setTotal(pageInfo.getTotal());

            return AjaxResult.success(responseVo);
    }
    @PostMapping("/new")
    public AjaxResult<BaseinfoRecord> addnew(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        BaseinfoRecord record = baseinfoService.createOne(principal.getName());
        return AjaxResult.success(record);
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
