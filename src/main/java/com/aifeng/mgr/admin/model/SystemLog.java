package com.aifeng.mgr.admin.model;

import com.aifeng.constants.LogType;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by pro on 17-4-30.
 */
@Table(name = "system_log")
@Entity
public class SystemLog {

    @Id
    @GeneratedValue
    private long id;

    private long operate_id; //充值人或退费人的id

    @Enumerated(EnumType.STRING)
    private LogType logType;

    private String content;

    private Date createDate;

    public SystemLog() {
        this.createDate = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOperate_id() {
        return operate_id;
    }

    public void setOperate_id(long operate_id) {
        this.operate_id = operate_id;
    }

    public LogType getLogType() {
        return logType;
    }

    public void setLogType(LogType logType) {
        this.logType = logType;
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