package com.bridge.record.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rr_plan_record")
public class PlanRecord {
    @Id
    private String id;
    private String patientid;
    private Date examDate  ;
    private String examItems;
    private String explanation ;
    private String suggestion ;
    private String plan1 ;
    private String plan2 ;
    private String plan3 ;
    private Date plan1Date  ;
    private Date plan2Date  ;
    private Date plan3Date  ;
    private String nurseName ;
    private String expertName ;
    private Date explantDate  ;
    private Date createDate;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientid() {
        return this.patientid;
    }

    public void setPatientid(String patientid) {
        this.patientid = patientid;
    }

    public Date getExamDate() {
        return this.examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public String getExamItems() {
        return this.examItems;
    }

    public void setExamItems(String examItems) {
        this.examItems = examItems;
    }

    public String getExplanation() {
        return this.explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getSuggestion() {
        return this.suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getPlan1() {
        return this.plan1;
    }

    public void setPlan1(String plan1) {
        this.plan1 = plan1;
    }

    public String getPlan2() {
        return this.plan2;
    }

    public void setPlan2(String plan2) {
        this.plan2 = plan2;
    }

    public String getPlan3() {
        return this.plan3;
    }

    public void setPlan3(String plan3) {
        this.plan3 = plan3;
    }

    public Date getPlan1Date() {
        return this.plan1Date;
    }

    public void setPlan1Date(Date plan1Date) {
        this.plan1Date = plan1Date;
    }

    public Date getPlan2Date() {
        return this.plan2Date;
    }

    public void setPlan2Date(Date plan2Date) {
        this.plan2Date = plan2Date;
    }

    public Date getPlan3Date() {
        return this.plan3Date;
    }

    public void setPlan3Date(Date plan3Date) {
        this.plan3Date = plan3Date;
    }

    public String getNurseName() {
        return this.nurseName;
    }

    public void setNurseName(String nurseName) {
        this.nurseName = nurseName;
    }

    public String getExpertName() {
        return this.expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName;
    }

    public Date getExplantDate() {
        return this.explantDate;
    }

    public void setExplantDate(Date explantDate) {
        this.explantDate = explantDate;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public String toString(){
        return String.format(
        "Plan[id=%s, 客户档案号='%s', 专家='%s', 解读日期='%s']",
        id, patientid, expertName, explantDate);
    }
}
