package com.bridge.record.dao;

import java.util.List;

import com.bridge.common.CommonMapper;
import com.bridge.record.model.BaseinfoRecord;


public interface BaseinfoRecordMapper extends CommonMapper<BaseinfoRecord> {

    List<BaseinfoRecord> findByFullname(String fullname);
}
