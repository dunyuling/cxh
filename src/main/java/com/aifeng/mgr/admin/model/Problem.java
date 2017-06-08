package com.aifeng.mgr.admin.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by pro on 17-4-21.
 */
@Table(name = "problem")
@Entity
public class Problem {

    @Id
    @GeneratedValue
    private long id;

    private int customerService_id;

    private long agent_id;

    private String title;

    @Column(columnDefinition = "text")
    private String description;

    private boolean solve = false;

    private String solution;

    private Date createDate;

    private Date updateDate;

    public Problem() {
        this.createDate = new Date();
        this.updateDate = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCustomerService_id() {
        return customerService_id;
    }

    public void setCustomerService_id(int customerService_id) {
        this.customerService_id = customerService_id;
    }

    public long getAgent_id() {
        return agent_id;
    }

    public void setAgent_id(long agent_id) {
        this.agent_id = agent_id;
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

    public boolean isSolve() {
        return solve;
    }

    public void setSolve(boolean solve) {
        this.solve = solve;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
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