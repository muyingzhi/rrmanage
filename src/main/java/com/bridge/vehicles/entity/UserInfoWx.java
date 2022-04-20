package com.bridge.vehicles.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Table(name = "examination.user_info")
@EqualsAndHashCode(callSuper=false)
@ApiModel("用户信息")
public class UserInfoWx extends BaseEntity{

    @Id
    @GeneratedValue(generator = "JDBC")
    private Long userId;
    private String wxid;
    private String wxName;
    private String fullname;
    private String tel;
}
