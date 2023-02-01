package com.bridge.vehicles;

import java.util.Date;

import com.bridge.vehicles.dao.DicMapper;
import com.bridge.vehicles.dao.UserInfoWxMapper;
import com.bridge.vehicles.entity.DicRecord;
import com.bridge.vehicles.entity.UserInfoWx;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@MapperScan(basePackages="com.bridge.vehicles.dao.mapper")
@SpringBootTest
class UserTests {
	@Autowired
	private UserInfoWxMapper userMapper;

	@Test
	void testInsert() {
		UserInfoWx wxUser = new UserInfoWx();
		wxUser.setFullname("刘飞飞");;
		wxUser.setTel("189001090190");
		wxUser.setWxName("昵称");
		wxUser.setWxid("wxi1222d090023ff");
		wxUser.setCreateTime(new Date());
		wxUser.setUpdateTime(new Date());
		int i = userMapper.insert(wxUser);
		Assert.isTrue(wxUser.getUserId()>=0,"User_INFO_WX Insert失败");
	}
}
