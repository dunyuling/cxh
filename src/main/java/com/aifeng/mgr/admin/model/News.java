package com.aifeng.mgr.admin.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by pro on 17-4-21.
 */
@Table
@Entity
public class News {

    @Id
    @GeneratedValue
    private long id;
    private String title;
    private String img;   //信息列表时展示
    private String desc;
    @Column(columnDefinition = "text")
    private String content;//富文本编辑器编辑
    private Date createTime;
    private Date updateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
