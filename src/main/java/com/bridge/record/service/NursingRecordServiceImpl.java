package com.bridge.record.service;

import java.util.List;

import com.bridge.record.dao.NursingRecordMapper;
import com.bridge.record.model.NursingRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NursingRecordServiceImpl {
    @Autowired
    private NursingRecordMapper mapper;
    public Iterable<NursingRecord> findByType(String patientid,String nursingType){
        NursingRecord record = new NursingRecord();
        record.setPatientid(patientid);
        record.setNursingType(nursingType);
        return mapper.select(record);
    }
    public int save(NursingRecord record){
        if(record.getId()==null){
            mapper.insert(record);
        }else{
            mapper.updateByPrimaryKey(record);
        }
        return 1;
    }
    public NursingRecord selectByPrimaryKey(long id){
        return mapper.selectByPrimaryKey(id);
    }
    public int deleteByPrimaryKey(long id){
        return mapper.deleteByPrimaryKey(Long.valueOf(id));
    }
    public int count(){
        NursingRecord record=new NursingRecord();

        return mapper.selectCount(record);
    }
}
