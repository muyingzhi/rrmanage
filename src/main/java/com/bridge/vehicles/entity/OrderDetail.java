package com.bridge.vehicles.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Table(name = "examination.order_detail")
@ApiModel("订单明细")
public class OrderDetail{
    @Id
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty("ID")
    private Long id;
    @ApiModelProperty("订单编号")
    private Long orderId;
    @ApiModelProperty("商品ID")
    private Long goodsId;
    @ApiModelProperty("序号")
    private Integer goodsNo;

    @ApiModelProperty("商品名称")
    private String goodsName;

    @ApiModelProperty("商品类型")
    private String goodsType;

    @ApiModelProperty("规格")
    private String spec;

    @ApiModelProperty("单价")
    private Double goodsPrice;

    @ApiModelProperty("数量")
    private Integer amount;

    @ApiModelProperty("金额")
    private Double charge;

}
