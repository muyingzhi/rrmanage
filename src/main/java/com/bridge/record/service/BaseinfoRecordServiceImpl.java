package com.bridge.record.service;

import com.bridge.record.dao.BaseinfoRecordMapper;
import com.bridge.record.model.BaseinfoRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseinfoRecordServiceImpl {
    @Autowired
    private BaseinfoRecordMapper mapper;
    public Iterable<BaseinfoRecord> getByFullname(String fullname){
        
        return mapper.findByFullname(fullname==null?"%":fullname+"%");
    }
    public int save(BaseinfoRecord record){
        if(record.getId()==null){
            mapper.insert(record);
        }else{
            mapper.updateByPrimaryKey(record);
        }
        return 1;
    }
    public BaseinfoRecord selectByPrimaryKey(long id){
        return mapper.selectByPrimaryKey(id);
    }
    public int deleteByPrimaryKey(long id){
        return mapper.deleteByPrimaryKey(Long.valueOf(id));
    }
}
