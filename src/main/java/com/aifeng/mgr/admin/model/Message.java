package com.aifeng.mgr.admin.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by pro on 17-4-21.
 */
@Table(name = "message")
@Entity
public class Message {

    @Id
    @GeneratedValue
    private long id;
    private long proxyAddress_id;
    private String content;
    private Date createDate;

    public Message() {
        this.createDate = new Date();
    }

    public Message(long proxyAddress_id, String content) {
        this.proxyAddress_id = proxyAddress_id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProxyAddress_id() {
        return proxyAddress_id;
    }

    public void setProxyAddress_id(long proxyAddress_id) {
        this.proxyAddress_id = proxyAddress_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}