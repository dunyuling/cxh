package com.aifeng.mgr.admin.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by pro on 17-6-7.
 */
@Entity
@Table(name = "validate_code")
public class ValidateCode {

    @Id
    @GeneratedValue
    private long id;

    private String mobile;

    private String code;

    private Date createDate;

    public ValidateCode() {

    }

    public ValidateCode(String mobile,String code) {
        this.mobile = mobile;
        this.code = code;
        createDate = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
