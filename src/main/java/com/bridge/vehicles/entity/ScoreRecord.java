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
@Table(name = "examination.score_record")
@EqualsAndHashCode(callSuper=false)
@ApiModel("积分记录")
public class ScoreRecord extends BaseEntity {

    @Id
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty("ID")
    @Column(name = "score_id")
    private Long id;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("积分")
    private Integer score;
    
    @ApiModelProperty("积分后合计")
    private Integer totalScore;

    @ApiModelProperty("积分业务")
    private String  reason;

    @ApiModelProperty("分享积分来源ID")
    private Long shareId;
}
