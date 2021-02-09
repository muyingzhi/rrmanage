package com.bridge.record.controller;

import com.bridge.common.AjaxResult;
import com.bridge.common.PageRequest;
import com.bridge.common.PageResponse;
import com.bridge.record.model.PlanRecord;
import com.bridge.record.service.PlanRecordServiceImpl;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/api/plan")
public class PlanController {
    private static final Logger log = LoggerFactory.getLogger(PlanController.class);
	@Autowired
	private PlanRecordServiceImpl service;
    @PostMapping("list")
    public AjaxResult<PageResponse<PlanRecord>> getPageList(
        @RequestParam String fullname,
        @RequestBody PageRequest page
    ){
        Iterable<PlanRecord> its= service.getByFullname(fullname);
        PageInfo<PlanRecord> pageInfo = new PageInfo<>(Lists.newArrayList(its));
        PageResponse<PlanRecord> responseVo=new PageResponse<>();
        responseVo.setPageData(pageInfo.getList());
        responseVo.setTotal(pageInfo.getTotal());

        return AjaxResult.success(responseVo);
}
    @PostMapping("new")
    public AjaxResult<PlanRecord> addnew(){
        return AjaxResult.success(new PlanRecord());
    }
    @PostMapping("save")
    public AjaxResult<Integer> save(@RequestBody PlanRecord record){
        return AjaxResult.success(service.save(record));
    }
    @GetMapping("/{id}")
    public AjaxResult<PlanRecord> selectByPrimaryKey(@PathVariable Long id){
        
        return AjaxResult.success(service.selectByPrimaryKey(id));
    }
    @DeleteMapping("/{id}")
    public AjaxResult<Integer> deleteByPrimaryKey(@PathVariable Long id){
        
        return AjaxResult.success(service.deleteByPrimaryKey(id));
    }
    
}
