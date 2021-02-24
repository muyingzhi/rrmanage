package com.bridge.record.model;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
public class SysRole implements GrantedAuthority {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer roleid;
    private String roleName;

    public SysRole(int id, String name){
        roleid=id;
        roleName=name;
    }
    @Override
    public String getAuthority() {
        return "ROLE_"+this.roleName;
    }
    
}
