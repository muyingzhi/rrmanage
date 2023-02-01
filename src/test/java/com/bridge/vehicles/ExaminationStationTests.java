package com.bridge.vehicles;

import java.util.Date;

import com.bridge.vehicles.dao.ExaminationRecordMapper;
import com.bridge.vehicles.dao.ExaminationStationMapper;
import com.bridge.vehicles.dao.GoodsMapper;
import com.bridge.vehicles.dao.GoodsTypeMapper;
import com.bridge.vehicles.entity.ExaminationRecord;
import com.bridge.vehicles.entity.ExaminationStation;
import com.bridge.vehicles.entity.Goods;
import com.bridge.vehicles.entity.GoodsType;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@MapperScan(basePackages="com.bridge.vehicles.dao.mapper")
@SpringBootTest
class ExaminationStationTests {
	@Autowired
	private ExaminationStationMapper stationMapper;
	@Autowired
	private ExaminationRecordMapper examRecordMapper;

	@Test
	void testInsert() {
		ExaminationStation station = new ExaminationStation();
		station.setUserId(Long.valueOf(1));
		station.setStationAddress("郑州市二七区商鼎路189号");
		station.setAbbreviations("鼎检01");
		station.setStationName("郑州市鼎新检测场");
		station.setCarFee(360);
		station.setMiddleFee(420);
		station.setLinkMan("刘坤");
		station.setTel("18900109807");
		station.setCreateTime(new Date());
		station.setCreateUser(Long.valueOf(1));
		int i = stationMapper.insert(station);
		
		Assert.isTrue(station.getId()>=0,"station Insert失败");
	}
	@Test
	void examInsert() {
		ExaminationStation station = new ExaminationStation();
		station.setId(Long.valueOf(1));
		stationMapper.selectOne(station);
		ExaminationRecord record= new ExaminationRecord();
		record.setAgentUserId(Long.valueOf(1));
		record.setAppointmentExamDate(new Date());
		record.setAppointmentDate(new Date());
		record.setSourceType("1");
		record.setExaminationDate(new Date());
		record.setExaminationText("鼎新检001");
		record.setExaminationType("代理");
		record.setFee(360);
		record.setIsPaid(1);
		record.setPayWay("微信支付");
		record.setNextDate("202310");
		record.setPayTime(new Date());
		record.setStationId(Integer.valueOf(station.getId().toString()));
		record.setVehicleNo("京Q27758");
		record.setVehicleType("小客车");
		examRecordMapper.insert(record);
		Assert.isTrue(record.getExaminationId()>=0,"station Insert失败");
	}
}
