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
@EqualsAndHashCode(callSuper=false)
@Table(name = "examination.order_pay_record")
@ApiModel("订单支付记录")
public class OrderPayRecord extends BaseEntity{
    @Id
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("订单编号")
    private Long orderId;

    @ApiModelProperty("支付方式: 微信支付；银行支付")
    private String payWay;

    @ApiModelProperty("支付金额")
    private Double payMoney;

    @ApiModelProperty("支付时间")
    private Date payTime;

    @ApiModelProperty("支付账户")
    private String payAccount;

}
