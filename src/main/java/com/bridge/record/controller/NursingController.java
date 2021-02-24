package com.bridge.record.controller;

import com.bridge.common.AjaxResult;
import com.bridge.record.model.NursingRecord;
import com.bridge.record.service.NursingRecordServiceImpl;

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
@RequestMapping("/api/nursing")
public class NursingController {
    private static final Logger log = LoggerFactory.getLogger(PlanController.class);
	@Autowired
	private NursingRecordServiceImpl service;
    @PostMapping("list")
    public AjaxResult<Iterable<NursingRecord>> getPageList(
        @RequestParam String patientid,
        @RequestParam String nursingType
    ){
        Iterable<NursingRecord> its= service.findByType(patientid,nursingType);
        
        return AjaxResult.success(its);
}
    @PostMapping("new")
    public AjaxResult<NursingRecord> addnew(){
        return AjaxResult.success(new NursingRecord());
    }
    @PostMapping("save")
    public AjaxResult<Integer> save(@RequestBody NursingRecord record){
        return AjaxResult.success(service.save(record));
    }
    @GetMapping("/{id}")
    public AjaxResult<NursingRecord> selectByPrimaryKey(@PathVariable Long id){
        
        return AjaxResult.success(service.selectByPrimaryKey(id));
    }
    @DeleteMapping("/{id}")
    public AjaxResult<Integer> deleteByPrimaryKey(@PathVariable Long id){
        
        return AjaxResult.success(service.deleteByPrimaryKey(id));
    }
    
}
