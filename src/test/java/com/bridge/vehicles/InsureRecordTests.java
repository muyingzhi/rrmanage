package com.bridge.vehicles;

import java.util.Calendar;
import java.util.Date;

import com.bridge.vehicles.dao.DicMapper;
import com.bridge.vehicles.dao.InsureRecordMapper;
import com.bridge.vehicles.entity.DicRecord;
import com.bridge.vehicles.entity.InsureRecord;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@MapperScan(basePackages="com.bridge.vehicles.dao.mapper")
@SpringBootTest
class InsureRecordTests {
	@Autowired
	private InsureRecordMapper mapper;

	@Test
	void testInsert() {
		InsureRecord record = new InsureRecord();
		record.setInsuranceCompany("insuranceCompany");
		Calendar calendar = Calendar.getInstance();
		record.setInsureDateYear(calendar.get(Calendar.YEAR));
		record.setInsureDateMonth(calendar.get(Calendar.MONTH));
		record.setInsureDateDay(calendar.get(Calendar.DAY_OF_MONTH));
		record.setInsureMoney(9012);
		record.setInsureYear("2022");
		record.setVehicleNo("京@27758");
		record.setCreateTime(new Date());
		record.setCreateUser(Long.valueOf(1));
		int i = mapper.insert(record);
		Assert.isTrue(record.getId()>=0,"station Insert失败");
	}
}
