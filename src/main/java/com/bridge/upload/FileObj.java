package com.bridge.upload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
* @ClassName : 
* @Description : 
* @Author : zhenjf  
* @Date: 2021-01-27  
*/
@ApiModel(value="上传附件表")
@Data
@Table(name = "biz_fj")
public class FileObj {
    /**
     * id
     */
    @Column(name = "id")
    @ApiModelProperty(value="id")
    private String id;

    /**
     * 业务id
     */
    @Column(name = "pid")
    @ApiModelProperty(value="业务id")
    private String pid;

    /**
     * 路径
     */
    @Column(name = "`path`")
    @ApiModelProperty(value="路径")
    private String path;

    /**
     * 类型文件后缀
     */
    @Column(name = "`type`")
    @ApiModelProperty(value="类型文件后缀")
    private String type;

    /**
     * 原始名称
     */
    @Column(name = "ysmc")
    @ApiModelProperty(value="原始名称")
    private String ysmc;

    /**
     * 创建时间
     */
    @Column(name = "createTime")
    @ApiModelProperty(value="创建时间")
    private Date createTime;
}