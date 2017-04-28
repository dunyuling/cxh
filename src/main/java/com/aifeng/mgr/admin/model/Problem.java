package com.aifeng.mgr.admin.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by pro on 17-4-21.
 */
@Table(name="problem")
@Entity
public class Problem {

    @Id
    @GeneratedValue
    private long id;

    private long customerService_id;

    private String title;

    @Column(columnDefinition = "text")
    private String description;

    private Date createTime;

    private Date updateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomerService_id() {
        return customerService_id;
    }

    public void setCustomerService_id(long customerService_id) {
        this.customerService_id = customerService_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}