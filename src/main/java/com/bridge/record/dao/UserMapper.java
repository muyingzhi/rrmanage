package com.bridge.record.dao;

import java.util.List;

import com.bridge.common.CommonMapper;
import com.bridge.record.model.SysUser;

import org.apache.ibatis.annotations.Select;

public interface UserMapper extends CommonMapper<SysUser> {
    @Select("select id,username,fullname from sys_user")
    public List<SysUser> selectAllNoPassword();


    @Select("select * from sys_user where fullname like #{fullname}")
    public List<SysUser> getByFullname(String fullname);

    @Select("select id,username,fullname from sys_user where role_id=#{roleId}")
    public List<SysUser> selectByRole(int roleId);
}
