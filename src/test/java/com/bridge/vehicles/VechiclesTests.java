package com.bridge.vehicles;

import java.util.Date;
import com.bridge.vehicles.dao.LicensesVehicleMapper;
import com.bridge.vehicles.entity.LicensesVehicle;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@MapperScan(basePackages="com.bridge.vehicles.dao.mapper")
@SpringBootTest
class VechiclesTests {
	@Autowired
	private LicensesVehicleMapper mapper;

	@Test
	void testInsert() {
		LicensesVehicle record = new LicensesVehicle();
		record.setIsMine(SysConstant.MINE);
		record.setLicensesNo("0910902909901");
		record.setVehicleNo("京Q27758");
		record.setVehicleType(SysConstant.CAR);
		record.setBrand("别克");
		record.setVehicleOwner("张玲玲");
		record.setRegistrationDate(new Date());
		record.setIssuanceDate(new Date());
		record.setUserId(Long.valueOf(1));
		record.setLinkMan("muyz");
		record.setLinkTel("186779890910");
		record.setShareId(Long.valueOf(1));
		record.setCreateTime(new Date());
		record.setCreateUser(Long.valueOf(1));
		int i = mapper.insert(record);
		
		Assert.isTrue(i>0,"insert失败");
	}

}
