package com.bridge.vehicles;

import java.util.Date;

import com.bridge.vehicles.dao.SharedRecordMapper;
import com.bridge.vehicles.entity.SharedRecord;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@MapperScan(basePackages="com.bridge.vehicles.dao.mapper")
@SpringBootTest
class SharedRecordTests {
	@Autowired
	private SharedRecordMapper mapper;

	@Test
	void testInsert() {
		SharedRecord sr = new SharedRecord();
		sr.setSharedDate(new Date());
		sr.setUserId(Long.valueOf(1));
		// sr.setCreateTime(new Date());
		// sr.setCreateUser(Long.valueOf(1));
		mapper.insert(sr);
		Assert.isTrue(sr.getId()>0,"station Insert失败");
	}
}
