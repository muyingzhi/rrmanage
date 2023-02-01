package com.bridge.vehicles.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Table(name = "examination.shared_record")
@EqualsAndHashCode(callSuper=false)
@ApiModel("分享记录")
public class SharedRecord  {

    @Id
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("用户ID")
    private Long userId;
    
    @ApiModelProperty("分享时间")
    private Date sharedDate;
}
