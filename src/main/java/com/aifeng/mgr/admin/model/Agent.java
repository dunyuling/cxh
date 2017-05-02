package com.aifeng.mgr.admin.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by pro on 17-4-21.
 */

@Entity
@Table(name="agent")
public class Agent {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
    @Column(unique=true, columnDefinition="varchar(32)")
    private String name;
    @Column(unique=true, columnDefinition="varchar(32)")
    private String userid;
    @Column(columnDefinition="varchar(11)")
    private String mobile;
    @Column(columnDefinition="varchar(18)")
    private String IDCard;
    @Column(columnDefinition="varchar(32)")
    private String corpName;
    @Column(columnDefinition="varchar(255)")
//    private String BusinessLicenseImg;
//    private Date BusinessLicenseExpireDate;

    private String licenseImg;
    private Date expireDate;

    @Column(name="money")
    private int money;//账户余额

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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIDCard() {
        return IDCard;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    /*public String getBusinessLicenseImg() {
        return BusinessLicenseImg;
    }

    public void setBusinessLicenseImg(String businessLicenseImg) {
        BusinessLicenseImg = businessLicenseImg;
    }

    public Date getBusinessLicenseExpireDate() {
        return BusinessLicenseExpireDate;
    }

    public void setBusinessLicenseExpireDate(Date businessLicenseExpireDate) {
        BusinessLicenseExpireDate = businessLicenseExpireDate;
    }*/

    public String getLicenseImg() {
        return licenseImg;
    }

    public void setLicenseImg(String licenseImg) {
        this.licenseImg = licenseImg;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}