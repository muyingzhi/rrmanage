package com.bridge.record.vo;

import java.util.List;

import lombok.Data;
@Data
public class VoUser {
    /**
     *
     */
    private Integer id;
    private String username;
    private String password;
    private String fullname;
    private List<Integer> roles;
    public VoUser(){
        
    }

}