package com.bridge.vehicles.entity;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BaseEntity {
    @ApiModelProperty("创建人")
    private Long createUser;
    @ApiModelProperty("创建时间")
    private Date createTime; 
    
}
