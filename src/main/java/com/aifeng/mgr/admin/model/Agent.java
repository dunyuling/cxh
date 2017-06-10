package com.aifeng.mgr.admin.model;

import com.aifeng.constants.Constants;
import com.aifeng.util.Md5;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by pro on 17-4-21.
 */

@Entity
@Table(name = "agent")
public class Agent {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
    @Column(/*unique=true,*/ columnDefinition = "varchar(32)")
    private String name;
    @Column(unique = true, columnDefinition = "varchar(32)")
    private String userid;
    @Column(columnDefinition = "varchar(11)")
    private String mobile;
    @Column(columnDefinition = "varchar(18)")
    private String IDCard;
    @Column(columnDefinition = "varchar(32)")
    private String corpName;
    @Column(columnDefinition = "varchar(255)")

    private String licenseImg;
    private Date expireDate;

    @Column(name = "money")
    private int money;//账户余额

    private String pwd;

    private Date createDate;

    private boolean active = true;

    public Agent() {
        this.pwd = Md5.getMd5(Constants.agent_pwd);
        this.active = true;
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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}