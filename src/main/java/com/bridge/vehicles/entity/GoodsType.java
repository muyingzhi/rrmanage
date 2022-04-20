package com.bridge.vehicles.entity;

import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Table(name = "examination.goods_type")
@ApiModel("商品类型")
public class GoodsType {

    @ApiModelProperty("编码")
    private String typeCode;

    @ApiModelProperty("内容")
    private String typeName;
}
