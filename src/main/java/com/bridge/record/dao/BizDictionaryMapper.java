package com.bridge.record.dao;

import com.bridge.common.CommonMapper;
import com.bridge.record.model.BizDictionary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName : BizDictionaryMapper
 * @Description :
 * @Author : zhenjf
 * @Date: 2021-01-21
 */
public interface BizDictionaryMapper extends CommonMapper<BizDictionary> {

    List<BizDictionary> getList(BizDictionary vo);

    List<BizDictionary> getDictListByType(@Param("type") String type);

}