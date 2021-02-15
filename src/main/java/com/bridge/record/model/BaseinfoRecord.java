package com.bridge.record.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "rr_baseinfo_record")
@Data
public class BaseinfoRecord {
    @Id
    private Long id;
    private String patientid;
    private String fullname;
    private String sex;
    private String birthday;
    private String nation;
    private String cardno;
    private String address;
    private String marriage;
    private Float height;
    private Float weight;
    private Float bmi;
    private String phone;
    private Float temperature;
    private String bloodpressure;
    private Integer heartrate;
    private Integer breathe;
    // -------
    private String symptom;
    // ------
    private String symptomOther;
    // ------
    private String familyHistory;
    private String ai01;
    private String ai02;
    private String ai03;
    private String ai04;
    private String ai11;
    private String ai12;
    private String ai13;
    private String ai14;
    private String p1;
    private String p1Date;
    private String p1One;
    private String p1OneDate;
    private String p2;
    private String p201Diagnose;
    private String p201Date;
    private String p201Status;
    private String p202Diagnose;
    private String p202Date;
    private String p202Status;
    private String p3;
    private String p301Diagnose;
    private String p301Date;
    private String p301Name;
    private String p302Diagnose;
    private String p302Date;
    private String p302Name;
    private String p4;
    private String p401Name;
    private String p401Date;
    private String p401Usage;
    private String p402Name;
    private String p402Date;
    private String p402Usage;
    private String p5;
    private String p5Name;

    private String h1;
    private Integer h1Years;
    private Integer h1Few;
    private String h2;
    private String h2Text;
    private String h3;
    private String h3Time;
    private String h4;
    private String h4Many;
    private String h5;
    private String h6;
    private String h6Date;
    private Integer h6Days;
    private String others;
    private String treat;
    private String nurseName;
    private String nurseFullname;    private String createDate;
    private String status;
    public String toString() {
        return String.format("Plan[id=%n, 客户档案号='%s', 专家='%s', 解读日期='%s']", id, patientid);
    }

}
