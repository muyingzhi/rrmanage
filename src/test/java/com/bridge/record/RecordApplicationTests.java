package com.bridge.record;

import java.util.Date;

import com.bridge.record.model.PlanRecord;
import com.bridge.record.service.PlanRecordService;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.hutool.core.util.IdUtil;

@MapperScan(basePackages="com.bridge.record.dao.mapper")
@SpringBootTest
class RecordApplicationTests {
	@Autowired
	private PlanRecordService service;
	@Test
	void planTest() {
		PlanRecord record = new PlanRecord();
		record.setId(IdUtil.simpleUUID());
		record.setPatientid("patientid");
		record.setExpertName("expertName");
		record.setExplantDate(new Date());
		service.save(record);

		Iterable<PlanRecord> it = service.getByFullname("");
		it.forEach(plan -> {
			System.out.print(plan.toString());
		});
	}

}
