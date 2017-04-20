package com.aifeng.mgr.admin.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 
 * @ClassName: Menu 
 * @Description: TODO
 * @author: cosco gt.fan@msn.cn
 * @date: 2016年10月13日 下午10:41:58
 */
@Entity
@Table( name = "tb_mgr_menu",uniqueConstraints={@UniqueConstraint(columnNames = {"name","code"})},schema="")
public class Menu implements Serializable{
	
    private Integer id;  // menu i
    private String name;  // 菜单名称
    private String code;
    private String url;    // url
    private String icon;  // 图标
    private Integer pid;   //上级菜单id
    private Boolean display;  // 是否展示
    private String attr1;
    private String attr2;
    private String attr3;
    private String attr4;
    private String attr5;
    private Date createDate;
    private Integer weight;   // 权重
    private String cls;   //
    

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name", nullable=false,columnDefinition="varchar(32)")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "url",  nullable=false, columnDefinition="varchar(128) default '#'")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "icon",columnDefinition="varchar(128) default ''")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Column(name = "pid", columnDefinition="INT default 0")
    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Column(name = "display", columnDefinition="bit(1)")
    public Boolean getDisplay() {
        return display;
    }

    public void setDisplay(Boolean display) {
        this.display = display;
    }

    @Column(name = "attr1")
    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    @Column(name = "attr2")
    public String getAttr2() {
        return attr2;
    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }

    @Column(name = "attr3")
    public String getAttr3() {
        return attr3;
    }

    public void setAttr3(String attr3) {
        this.attr3 = attr3;
    }

    @Column(name = "attr4")
    public String getAttr4() {
        return attr4;
    }

    public void setAttr4(String attr4) {
        this.attr4 = attr4;
    }

    @Column(name = "attr5")
    public String getAttr5() {
        return attr5;
    }

    public void setAttr5(String attr5) {
        this.attr5 = attr5;
    }
    
    @Column(name = "create_date")
    public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Column(name = "weight", columnDefinition="int default 0")
	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	
	@Column(name="cls", columnDefinition="varchar(24) default ''")
	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}
	
	
    @Column(name = "code", nullable=false,columnDefinition="varchar(32)")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Menu menu = (Menu) o;

        if (id != menu.id) return false;
        if (attr1 != null ? !attr1.equals(menu.attr1) : menu.attr1 != null) return false;
        if (attr2 != null ? !attr2.equals(menu.attr2) : menu.attr2 != null) return false;
        if (attr3 != null ? !attr3.equals(menu.attr3) : menu.attr3 != null) return false;
        if (attr4 != null ? !attr4.equals(menu.attr4) : menu.attr4 != null) return false;
        if (attr5 != null ? !attr5.equals(menu.attr5) : menu.attr5 != null) return false;
        if (display != null ? !display.equals(menu.display) : menu.display != null) return false;
        if (icon != null ? !icon.equals(menu.icon) : menu.icon != null) return false;
        if (name != null ? !name.equals(menu.name) : menu.name != null) return false;
        if (pid != null ? !pid.equals(menu.pid) : menu.pid != null) return false;
        if (url != null ? !url.equals(menu.url) : menu.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + (pid != null ? pid.hashCode() : 0);
        result = 31 * result + (display != null ? display.hashCode() : 0);
        result = 31 * result + (attr1 != null ? attr1.hashCode() : 0);
        result = 31 * result + (attr2 != null ? attr2.hashCode() : 0);
        result = 31 * result + (attr3 != null ? attr3.hashCode() : 0);
        result = 31 * result + (attr4 != null ? attr4.hashCode() : 0);
        result = 31 * result + (attr5 != null ? attr5.hashCode() : 0);
        return result;
    }
}
