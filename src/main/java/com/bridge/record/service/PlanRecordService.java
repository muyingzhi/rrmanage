package com.bridge.record.service;

import java.util.List;

import com.bridge.record.dao.PlanRecordMapper;
import com.bridge.record.model.PlanRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface PlanRecordService {
    public Iterable<PlanRecord> getByFullname(String fullname);
    public int save(PlanRecord record);
}
