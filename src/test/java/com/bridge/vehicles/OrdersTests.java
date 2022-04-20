package com.bridge.vehicles;

import java.util.Date;
import com.bridge.vehicles.dao.LicensesVehicleMapper;
import com.bridge.vehicles.dao.OrderDetailMapper;
import com.bridge.vehicles.dao.OrderPayRecordMapper;
import com.bridge.vehicles.dao.OrdersMapper;
import com.bridge.vehicles.entity.LicensesVehicle;
import com.bridge.vehicles.entity.OrderDetail;
import com.bridge.vehicles.entity.OrderPayRecord;
import com.bridge.vehicles.entity.Order;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@MapperScan(basePackages="com.bridge.vehicles.dao.mapper")
@SpringBootTest
class OrdersTests {
	@Autowired
	private OrdersMapper mapper;
	@Autowired
	private OrderDetailMapper detailMapper;
	@Autowired
	private OrderPayRecordMapper payRecordMapper;
	@Test
	void testInsert() {
		Double totalMoney = new Double("0");

		OrderDetail detail = new OrderDetail();
		detail.setOrderId(Long.valueOf(1));
		detail.setGoodsId(Long.valueOf(1));
		detail.setGoodsName("季马斯洛轮胎");
		detail.setGoodsNo(1);
		detail.setGoodsType("001");
		detail.setGoodsPrice(Double.valueOf(896));
		detail.setAmount(Integer.valueOf(1));
		detail.setCharge(detail.getGoodsPrice()*detail.getAmount());
		detail.setSpec("19寸");
		totalMoney += detail.getCharge();
		
		//----先保存订单主表
		Order order = new Order();
		order.setCreateTime(new Date());
		order.setCreateUser(Long.valueOf(1));
		order.setCustomerAddress("中州大道石化路1号1001室");
		order.setCustomerName("穆英智");
		order.setCustomerTel("18611790301");
		order.setCustomerUserId(Long.valueOf(1));
		order.setOrderTime(new Date());
		order.setOrderMoney(totalMoney);
		order.setOrderStatus("00");

		mapper.insert(order);
		//----再保存明细表
		detail.setOrderId(order.getId());
		int i = detailMapper.insert(detail);
		//----断言
		Assert.isTrue(i>0,"insert失败");
	}
	@Test
	void testOrderPay(){
		Order order = new Order();
		order.setId(Long.valueOf(1));
		order = mapper.selectOne(order);
		OrderPayRecord payRecord = new OrderPayRecord();
		payRecord.setOrderId(order.getId());
		payRecord.setPayMoney(order.getOrderMoney());
		payRecord.setPayWay("微信支付");
		payRecord.setPayAccount("90192009309004");
		payRecord.setPayTime(new Date());
		payRecord.setCreateTime(order.getCreateTime());
		payRecord.setCreateUser(order.getCreateUser());
		int i = payRecordMapper.insert(payRecord);
		Assert.isTrue(payRecord.getId()>0, "支付失败");
	}

}
