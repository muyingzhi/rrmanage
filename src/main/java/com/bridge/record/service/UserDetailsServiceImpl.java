package com.bridge.record.service;

import java.util.ArrayList;
import java.util.List;

import com.bridge.record.dao.UserMapper;
import com.bridge.record.model.SysRole;
import com.bridge.record.model.SysUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("myUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<SysRole> roles = new ArrayList<>();
        //---从数据库获得用户信息；
        SysUser user=new SysUser();
        user.setUsername(username);
        user = userMapper.selectOne(user);
        //---构建符合接口的UserDetail
        roles.add(new SysRole("001","USER"));
        user.setRoles(roles); 
        //---返回用户，将进行密码比较       
        System.out.println("serviceimpl-----load user:"+username);
        return user;
    }
    
}
