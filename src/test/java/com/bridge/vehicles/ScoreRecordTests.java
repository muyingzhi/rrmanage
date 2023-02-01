package com.bridge.vehicles;

import java.util.Date;

import com.bridge.vehicles.dao.ScoreRecordMapper;
import com.bridge.vehicles.entity.ScoreRecord;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@MapperScan(basePackages="com.bridge.vehicles.dao.mapper")
@SpringBootTest
class ScoreRecordTests {
	@Autowired
	private ScoreRecordMapper scoreMapper;

	@Test
	void testInsert() {
		ScoreRecord score = new ScoreRecord();
		score.setReason("100");
		score.setScore(10);
		score.setShareId(Long.valueOf(1));
		score.setTotalScore(110);
		score.setUserId(Long.valueOf(1));
		score.setCreateTime(new Date());
		score.setCreateUser(Long.valueOf(1));
		int i = scoreMapper.insert(score);
		Assert.isTrue(score.getId()>=0,"station Insert失败");
	}
}
