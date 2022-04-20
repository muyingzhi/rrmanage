package com.bridge.vehicles.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false) 
@ApiModel("机动车行驶证信息")
@Table( name = "examination.licenses_vehicles")
public class LicensesVehicle extends BaseEntity{
    
    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "licenses_id")
    private Long id;
    @ApiModelProperty("车辆绑定用户")
    private Long userId;
    @ApiModelProperty("本人/代理车辆：0 本人 1 代理")
    private String  isMine;
    @ApiModelProperty("行驶证编号")
    private String licensesNo;
    @ApiModelProperty("车牌号")
    private String vehicleNo;
    @ApiModelProperty("车辆类型：小客车、中型车")
    private String vehicleType;
    @ApiModelProperty("所有人")
    private String vehicleOwner;
    @ApiModelProperty("品牌型号")
    private String brand;
    @ApiModelProperty("注册登记日期")
    private Date registrationDate;
    @ApiModelProperty("发证日期")
    private Date issuanceDate;
    @ApiModelProperty("联系人")
    private String linkMan;
    @ApiModelProperty("联系电话")
    private String linkTel;
    @ApiModelProperty("分享ID，来自分享登记的记录时")
    private Long shareId;
}