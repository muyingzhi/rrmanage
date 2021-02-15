package com.bridge.record.service;

import com.bridge.record.dao.BaseinfoRecordMapper;
import com.bridge.record.dao.UserMapper;
import com.bridge.record.model.BaseinfoRecord;
import com.bridge.record.model.SysUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseinfoRecordServiceImpl {
    @Autowired
    private BaseinfoRecordMapper mapper;
    @Autowired
    private UserMapper userMapper;
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
    public BaseinfoRecord createOne(String nurseName){
        SysUser nurse = new SysUser();
        nurse.setUsername(nurseName);
        nurse = userMapper.selectOne(nurse);
        BaseinfoRecord record=new BaseinfoRecord();
        record.setNurseName(nurseName);
        record.setNurseFullname(nurse.getFullname());
        return record;
    }
    public int count(){
        BaseinfoRecord record=new BaseinfoRecord();

        return mapper.selectCount(record);
    }
}
