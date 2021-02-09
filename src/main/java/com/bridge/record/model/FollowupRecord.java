package com.bridge.record.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Data
@Table(name="rr_followup_record")
public class FollowupRecord {
    @Id
    private Long id;
    private String patientid;
    private String createDate;
    private String visitDate;
    private String visitor;
    private Float temperature  ;
    private String selfReported ;
    private String publicity;
    private String status ;
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
        "Plan[id=%n, 客户档案号='%s', ]",
        id, patientid);
    }
}
