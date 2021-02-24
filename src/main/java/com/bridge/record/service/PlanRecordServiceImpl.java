package com.bridge.record.service;

import com.bridge.record.dao.PlanRecordMapper;
import com.bridge.record.model.PlanRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanRecordServiceImpl {
    @Autowired
    private PlanRecordMapper mapper;
    public Iterable<PlanRecord> getByFullname(String fullname){
        return mapper.findByFullname(fullname==null?"%":fullname+"%");
    }
    public int save(PlanRecord record){
        if(record.getId()==null){
            mapper.insert(record);
        }else{
            mapper.updateByPrimaryKey(record);
        }
        return 1;
    }
    public PlanRecord selectByPrimaryKey(long id){
        return mapper.selectByPrimaryKey(id);
    }
    public int deleteByPrimaryKey(long id){
        return mapper.deleteByPrimaryKey(Long.valueOf(id));
    }
    public int count(){
        PlanRecord record=new PlanRecord();

        return mapper.selectCount(record);
    }
}
