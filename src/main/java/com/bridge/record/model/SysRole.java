package com.bridge.record.model;

import org.springframework.security.core.GrantedAuthority;

public class SysRole implements GrantedAuthority {
    private String roleid;
    private String roleName;

    public SysRole(String id, String name){
        roleid=id;
        roleName=name;
    }
    @Override
    public String getAuthority() {
        // TODO Auto-generated method stub
        return this.roleName;
    }
    
}
