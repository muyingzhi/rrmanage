package com.bridge.record.dao;

import java.util.List;

import com.bridge.common.CommonMapper;
import com.bridge.record.model.PlanRecord;


public interface PlanRecordMapper extends CommonMapper<PlanRecord> {

    List<PlanRecord> findByFullname(String fullname);

    PlanRecord deleteById(String id);
}
