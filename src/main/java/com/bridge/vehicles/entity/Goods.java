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
@ApiModel("商品")
@Table(name = "examination.goods")
public class Goods extends BaseEntity{

    @Id
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("商品名称")
    private String goodsName;

    @ApiModelProperty("商品类型")
    private String goodsType;

    @ApiModelProperty("描述")
    private String goodsDesc;

    @ApiModelProperty("规格")
    private String spec;

    @ApiModelProperty("单价")
    private Double price;

    @ApiModelProperty("图片")
    private String picture;

    @ApiModelProperty("库存数量")
    private Integer amount;

    @ApiModelProperty("供应商")
    private String provider;

    @ApiModelProperty("供应商地址")
    private String providerAddress;
}
