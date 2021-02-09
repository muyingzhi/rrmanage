package com.bridge.record.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Table(name="rr_plan_record")
@Data
public class PlanRecord {
    @Id
    private Long id;
    private String patientid;
    private String examDate  ;
    private String examItems;
    private String explanation ;
    private String suggestion ;
    private String plan1 ;
    private String plan2 ;
    private String plan3 ;
    private String plan1Date  ;
    private String plan2Date  ;
    private String plan3Date  ;
    private String nurseName ;
    private String expertName ;
    private String explantDate  ;
    private String createDate;
    @Transient
    private String fullname;
    @Transient
    private String sex;
    @Transient
    private String birthday;
    @Transient
    private String nation;

    public String toString(){
        return String.format(
        "Plan[id=%n, 客户档案号='%s', 专家='%s', 解读日期='%s']",
        id, patientid, expertName, explantDate);
    }
}
