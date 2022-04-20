package com.bridge.vehicles;

import java.util.Date;

import com.bridge.vehicles.dao.ExaminationRecordMapper;
import com.bridge.vehicles.dao.ExaminationStationMapper;
import com.bridge.vehicles.dao.GoodsMapper;
import com.bridge.vehicles.dao.GoodsTypeMapper;
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
}
