package com.aifeng.mgr.admin.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by pro on 17-5-11.
 */
@Table(name = "visit_record")
@Entity
public class VisitRecord {

    @Id
    @GeneratedValue
    private long id;

    private String situation;

    private String remark;

    @Column(name = "visit_date")
    private Date visitDate;

    @Column(name = "next_visit_date")
    private Date nextVisitDate;

    private int times;

    @Column(name = "member_id")
    private long memberId;

    public VisitRecord() {
        this.visitDate = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public Date getNextVisitDate() {
        return nextVisitDate;
    }

    public void setNextVisitDate(Date nextVisitDate) {
        this.nextVisitDate = nextVisitDate;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }
}
