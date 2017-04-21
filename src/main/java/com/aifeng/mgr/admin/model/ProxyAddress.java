package com.aifeng.mgr.admin.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
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

    private Date createTime;

    private boolean active; // 通过此表可以查询代理历史的变迁

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
