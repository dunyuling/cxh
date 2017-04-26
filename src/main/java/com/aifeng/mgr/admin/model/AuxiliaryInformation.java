package com.aifeng.mgr.admin.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by pro on 17-4-21.
 */
@Table(name="auxiliary_information")
@Entity
public class AuxiliaryInformation {

    @Id
    @GeneratedValue
    private long id;

    private String access_token = ""; //过期后需要重新获取

    private String corpID = "";

    private String secret = "";

    private Date updateDate;

    public AuxiliaryInformation() {}

    public void config(String access_token,String CorpID,String secret) {
        this.access_token = access_token;
        this.corpID = CorpID;
        this.secret = secret;
        this.updateDate = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getCorpID() {
        return corpID;
    }

    public void setCorpID(String corpID) {
        this.corpID = corpID;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
