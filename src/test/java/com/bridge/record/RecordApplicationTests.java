package com.bridge.record;

import com.bridge.record.model.BaseinfoRecord;
import com.bridge.record.model.FollowupRecord;
import com.bridge.record.model.PlanRecord;
import com.bridge.record.service.BaseinfoRecordServiceImpl;
import com.bridge.record.service.FollowupRecordServiceImpl;
import com.bridge.record.service.PlanRecordServiceImpl;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@MapperScan(basePackages="com.bridge.record.dao.mapper")
@SpringBootTest
class RecordApplicationTests {
	@Autowired
	private PlanRecordServiceImpl service;
	@Autowired
	private FollowupRecordServiceImpl followupService;
	@Autowired
	private BaseinfoRecordServiceImpl baseinfoService;
	@Test
	void planTest() {
		// PlanRecord record = new PlanRecord();
		// record.setPatientid("patientid");
		// record.setExpertName("expertName");
		// record.setExplantDate("2020-01-01");
		// service.save(record);

		Iterable<PlanRecord> it = service.getByFullname("六");
		int size = Lists.newArrayList(it).size();
		
		Assert.isTrue(size>0,"查询无数据");
	}
	@Test
	void followupTest() {
		FollowupRecord record = new FollowupRecord();
		record.setPatientid("001001");
		// record.setCreateDate("2021-01-02");
		followupService.save(record);

		Iterable<FollowupRecord> it = followupService.getByFullname(""+"%");
		int size = Lists.newArrayList(it).size();
		
		Assert.isTrue(size>0,"查询无数据");
	}
	@Test
	void baseinfoTest() {
		BaseinfoRecord record = new BaseinfoRecord();
		record.setPatientid("patientid");
		// record.setCreateDate(new Date());
		baseinfoService.save(record);

		Iterable<BaseinfoRecord> it = baseinfoService.getByFullname("");
		int size = Lists.newArrayList(it).size();
		
		Assert.isTrue(size>0,"查询无数据");
	}

}
