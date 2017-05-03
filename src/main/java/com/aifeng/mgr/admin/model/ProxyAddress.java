package com.aifeng.mgr.admin.model;

import com.aifeng.mgr.admin.constants.ProxyStatus;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by pro on 17-4-21.
 */
@Table(name = "proxy_address")
@Entity
public class ProxyAddress {

    @Id
    @GeneratedValue
    private long id;

    private long agent_id;

    private long af_id;

    @Enumerated(EnumType.STRING)
    private ProxyStatus proxyStatus;

    private String denyReason;

    private Date createDate;

    private Date updateDate;

    public ProxyAddress() {
        this.createDate = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAgent_id() {
        return agent_id;
    }

    public void setAgent_id(long agent_id) {
        this.agent_id = agent_id;
    }

    public long getAf_id() {
        return af_id;
    }

    public void setAf_id(long af_id) {
        this.af_id = af_id;
    }

    public ProxyStatus getProxyStatus() {
        return proxyStatus;
    }

    public void setProxyStatus(ProxyStatus proxyStatus) {
        this.proxyStatus = proxyStatus;
    }

    public String getDenyReason() {
        return denyReason;
    }

    public void setDenyReason(String denyReason) {
        this.denyReason = denyReason;
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
}