package com.bridge.record.dao;

import java.util.List;

import com.bridge.common.CommonMapper;
import com.bridge.record.model.BaseinfoRecord;

import org.apache.ibatis.annotations.Select;


public interface BaseinfoRecordMapper extends CommonMapper<BaseinfoRecord> {
    @Select("SELECT * FROM RR_BASEINFO_RECORD where fullname like #{fullname}")
    List<BaseinfoRecord> findByFullname(String fullname);

    List<BaseinfoRecord> findBy(String fullname,String source);
}
