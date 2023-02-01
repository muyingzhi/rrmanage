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
@ApiModel("订单")
@Table(name = "examination.order_record")
public class Order extends BaseEntity{
    @Id
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("客户姓名")
    private String customerName;

    @ApiModelProperty("客户用户ID")
    private Long customerUserId;

    @ApiModelProperty("客户电话")
    private String customerTel;

    @ApiModelProperty("收货地址")
    private String customerAddress;

    @ApiModelProperty("订单金额")
    private Double orderMoney;

    @ApiModelProperty("下单时间")
    private Date orderTime;

    @ApiModelProperty("订单状态：00 新订单；01 已支付；02 已退费")
    private String orderStatus;
}
