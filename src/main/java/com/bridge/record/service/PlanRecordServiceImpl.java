package com.bridge.record.service;

import java.util.List;

import com.bridge.record.dao.PlanRecordMapper;
import com.bridge.record.model.PlanRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanRecordServiceImpl implements PlanRecordService{
    @Autowired
    private PlanRecordMapper mapper;
    public Iterable<PlanRecord> getByFullname(String fullname){
        return mapper.selectAll();
    }
    public int save(PlanRecord record){
        return mapper.insert(record);
    }
}
