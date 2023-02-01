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
@Table(name = "examination.examination_record")
@EqualsAndHashCode(callSuper=false)
@ApiModel("检测记录")
public class ExaminationRecord extends BaseEntity{

    @Id
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty("examinationId")
    private Long examinationId;

    @ApiModelProperty("检测类型：0001 年审")
    private String examinationType;
    @ApiModelProperty("来源：0个人；1代理")
    private String sourceType;
    @ApiModelProperty("代理用户")
    private Long agentUserId;
    @ApiModelProperty("车类型")
    private String vehicleType;
    @ApiModelProperty("车牌号")
    private String vehicleNo;
    @ApiModelProperty("预约日期")
    private Date appointmentDate;
    @ApiModelProperty("预约检测日期")
    private Date appointmentExamDate;
    @ApiModelProperty("预约检测站")
    private Integer stationId;
    @ApiModelProperty("检测费用")
    private Integer fee;
    @ApiModelProperty("是否支付：0未支付；1已支付")
    private Integer isPaid;
    @ApiModelProperty("支付方式")
    private String payWay;
    @ApiModelProperty("支付时间")
    private Date payTime;
    @ApiModelProperty("检测时间")
    private Date examinationDate;
    @ApiModelProperty("下次检测年月")
    private String nextDate;
    @ApiModelProperty("检测结果描述")
    private String examinationText;
}