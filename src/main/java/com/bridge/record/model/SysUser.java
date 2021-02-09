package com.bridge.record.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SysUser implements UserDetails {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String username;
    private String pwd;
    private List<SysRole> roles;

    public SysUser(String username,String pwd,List<SysRole> roles){
        this.username=username;
        this.pwd = pwd;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        return roles;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return this.pwd;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
		
}