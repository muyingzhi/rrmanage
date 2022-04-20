package com.bridge.vehicles.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Table(name = "examination.examination_station")
@EqualsAndHashCode(callSuper=false)
@ApiModel("检测站")
public class ExaminationStation extends BaseEntity {
    
    @Id
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty("ID")
    @Column(name="station_id")
    private Long id;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("检测站名称")
    private String stationName;

    @ApiModelProperty("简称")
    private String abbreviations;

    @ApiModelProperty("地址")
    private String  stationAddress;

    @ApiModelProperty("联系人")
    private String linkMan;

    @ApiModelProperty("联系电话")
    private String tel;

    @ApiModelProperty("小客车检测费")
    private Integer carFee;

    @ApiModelProperty("中型车检测费")
    private Integer middleFee;
}
