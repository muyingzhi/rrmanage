package com.bridge.record.service;

import com.bridge.record.dao.BizDictionaryMapper;
import com.bridge.record.model.BizDictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName : BizDictionaryServiceImpl
 * @Description :字典表增删改查
 * @Author : ly
 * @Date: 2021-01-21
 */
@Service
public class BizDictionaryServiceImpl {

    @Autowired
    private BizDictionaryMapper dao;

    public List<BizDictionary> getList(BizDictionary record) {
        return dao.getList(record);
    }
    public int deleteByPrimaryKey(String id) {
        return dao.deleteByPrimaryKey(id);
    }
    public int save(BizDictionary record) {
        if(record.getId()!=null){
            return dao.updateByPrimaryKeySelective(record);
        }else{
            return dao.insertSelective(record);
        }
    }

    public BizDictionary selectByPrimaryKey(String id) {
        return dao.selectByPrimaryKey(id);
    }

    public List<BizDictionary> getDictListByType(String type) {
        return dao.getDictListByType(type);
    }
}


