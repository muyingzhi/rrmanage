package com.bridge.record.controller;

import java.util.Date;

import com.bridge.record.model.PlanRecord;
import com.bridge.record.service.PlanRecordService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.hutool.core.util.IdUtil;

@RestController
@RequestMapping("/api/plan")
public class PlanController {
    private static final Logger log = LoggerFactory.getLogger(PlanController.class);
	@Autowired
	private PlanRecordService service;
    @RequestMapping("list")
    public Object getPageList(){PlanRecord record = new PlanRecord();
		record.setId(IdUtil.simpleUUID());
		record.setPatientid("patientid");
		record.setExpertName("expertName");
		record.setExplantDate(new Date());
		service.save(record);

		Iterable<PlanRecord> it = service.getByFullname("");
		it.forEach(plan -> {
			System.out.print(plan.toString());
		});
        return it;
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
