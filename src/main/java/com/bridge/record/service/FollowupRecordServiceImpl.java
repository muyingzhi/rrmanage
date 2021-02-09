package com.bridge.record.service;

import com.bridge.record.dao.FollowupRecordMapper;
import com.bridge.record.model.FollowupRecord;
import com.bridge.record.model.FollowupRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowupRecordServiceImpl {
    @Autowired
    private FollowupRecordMapper mapper;
    public Iterable<FollowupRecord> getByFullname(String fullname){
        return mapper.findByFullname(fullname==null?"%":fullname+"%");
    }
    public int save(FollowupRecord record){
        if(record.getId()==null){
            mapper.insert(record);
        }else{
            mapper.updateByPrimaryKey(record);
        }
        return 1;
    }
    public FollowupRecord selectByPrimaryKey(long id){
        return mapper.selectByPrimaryKey(id);
    }
    public int deleteByPrimaryKey(long id){
        return mapper.deleteByPrimaryKey(Long.valueOf(id));
    }
}
