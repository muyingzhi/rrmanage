package com.bridge.record.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.bridge.record.model.SysRole;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class MyAuthentication implements Authentication {
    private Authentication auth;

    public MyAuthentication(Authentication a){
        auth = a;
    }
    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return auth.getName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        List<SysRole> roles = new ArrayList<>();
        SysRole one = new SysRole("001","USER");
        // roles.add(one);
        return roles;
    }

    @Override
    public Object getCredentials() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object getDetails() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object getPrincipal() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        // TODO Auto-generated method stub

    }
    
}
