package com.bridge.record.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
@Entity
@Table(name="sys_user")
@Data
public class SysUser implements UserDetails {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    private Integer id;
    private String username;
    private String password;
    private String fullname;
    private Integer roleId;
    @Transient
    private List<SysRole> roles;

    public SysUser(){
        
    }
    public SysUser(String username,String pwd,List<SysRole> roles){
        this.username=username;
        this.password = pwd;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        return roles;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return this.password;
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