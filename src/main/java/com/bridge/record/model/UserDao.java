package com.bridge.record.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDao implements UserDetails {
    private String username;
    private String pwd;
    private List<SysRole> roles;

    public UserDao(){
        this.username="caos";
        this.pwd = "1";
        roles = new ArrayList<>();
        roles.add(new SysRole("001","USER"));
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
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
		
}