package com.bridge.vehicles.entity;

import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Table(name = "examination.dic_record")
@EqualsAndHashCode(callSuper=false)
@ApiModel("字典表")
public class DicRecord {
    @ApiModelProperty("字典类别")
    private String dicType;

    @ApiModelProperty("编码")
    private String itemCode;

    @ApiModelProperty("内容")
    private String itemName;
}
