package com.bridge.vehicles;

import java.util.Date;

import com.bridge.vehicles.dao.GoodsMapper;
import com.bridge.vehicles.dao.GoodsTypeMapper;
import com.bridge.vehicles.entity.Goods;
import com.bridge.vehicles.entity.GoodsType;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@MapperScan(basePackages="com.bridge.vehicles.dao.mapper")
@SpringBootTest
class GoodsTests {
	@Autowired
	private GoodsMapper mapper;
	@Autowired
	private GoodsTypeMapper goodsTypeMapper;

	@Test
	void testInsert() {
		Goods record = new Goods();
		record.setGoodsName("季马斯特轮胎");
		record.setGoodsType("轮胎");
		record.setGoodsDesc("薄胎，细纹");
		record.setSpec("19寸");
		record.setProvider("万亿配件");
		record.setProviderAddress("郑州市黄河大街花园商厦底商300号");
		record.setPicture("0019002012.png");
		record.setPrice(Double.valueOf(896));
		record.setAmount(100);
		record.setCreateTime(new Date());
		record.setCreateUser(Long.valueOf(1));
		int i = mapper.insert(record);
		
		Assert.isTrue(i>0,"insert失败");
	}
	@Test
	void testInsertType() {
		GoodsType gtype= new GoodsType();
		gtype.setTypeCode("001");
		gtype.setTypeName("轮胎");
		goodsTypeMapper.insert(gtype);
		gtype= new GoodsType();
		gtype.setTypeCode("002");
		gtype.setTypeName("洗车");
		goodsTypeMapper.insert(gtype);
		gtype= new GoodsType();
		gtype.setTypeCode("003");
		gtype.setTypeName("保险");
		goodsTypeMapper.insert(gtype);
	}
}
