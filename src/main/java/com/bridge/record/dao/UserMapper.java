package com.bridge.record.dao;

import java.util.List;

import com.bridge.common.CommonMapper;
import com.bridge.record.model.SysUser;

import org.apache.ibatis.annotations.Select;

public interface UserMapper extends CommonMapper<SysUser> {
    @Select("select id,username,fullname from sys_user")
    public List<SysUser> selectAllNoPassword();
}
