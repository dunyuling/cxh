package com.aifeng.mgr.admin.model;

import com.aifeng.mgr.admin.constants.InsuranceType;
import com.aifeng.mgr.admin.constants.Status;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by pro on 17-4-21.
 */
@Table(name="member")
@Entity
public class Member {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String mobile;
    @Column
    @Enumerated(value = EnumType.STRING)
    private InsuranceType type;
    private long address_id;
    private Date createDate;
    private Date updateDate;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    private String denyReason;

    public Member() {
        this.createDate = new Date();
        this.status = Status.WAITING; //TODO 数据库字段显示方式
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public InsuranceType getType() {
        return type;
    }

    public void setType(InsuranceType type) {
        this.type = type;
    }

    public long getAddress_id() {
        return address_id;
    }

    public void setAddress_id(long address_id) {
        this.address_id = address_id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDenyReason() {
        return denyReason;
    }

    public void setDenyReason(String denyReason) {
        this.denyReason = denyReason;
    }
}
