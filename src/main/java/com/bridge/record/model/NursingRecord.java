package com.bridge.record.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Entity
@Table(name="rr_nursing_record")
@Data
public class NursingRecord {
    @Id
    private Integer id;
    private String patientid;
    private String nursingType;
    private String nursingDate;
    private String items;
    private String nursingNote;
    private String nurseFullname;
    private String nurseName;
    private Boolean isFee;
    private String operator;
    private String checker;
    private String somethings;
    private String doTime;
}