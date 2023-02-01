package com.bridge.vehicles;

import com.bridge.vehicles.dao.DicMapper;
import com.bridge.vehicles.entity.DicRecord;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@MapperScan(basePackages="com.bridge.vehicles.dao.mapper")
@SpringBootTest
class DicRecordTests {
	@Autowired
	private DicMapper dicMapper;

	@Test
	void testInsert() {
		DicRecord dic = new DicRecord();
		dic.setDicType("01");;
		dic.setItemCode("0101");
		dic.setItemName("本人");
		int i = dicMapper.insert(dic);

		dic.setDicType("01");;
		dic.setItemCode("0102");
		dic.setItemName("代理");
		i = dicMapper.insert(dic);
		Assert.isTrue(i>0,"station Insert失败");
	}
}
