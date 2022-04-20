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
@Table(name = "examination.insure_record")
@EqualsAndHashCode(callSuper=false)
@ApiModel("保险记录")
public class InsureRecord extends BaseEntity {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @ApiModelProperty("车牌号")
    private String vehicleNo;

    @ApiModelProperty("投保年份")
    private String insureYear;
    
    @ApiModelProperty("投保时间")
    private Date insureDate;

    @ApiModelProperty("保险公司")
    private String  insuranceCompany;

    @ApiModelProperty("投保金额")
    private Integer insureMoney;
}
