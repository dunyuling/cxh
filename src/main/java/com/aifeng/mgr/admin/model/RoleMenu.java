package com.aifeng.mgr.admin.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_mgr_role_menu")
public class RoleMenu implements Serializable{
	
	private Integer id; 
	private String roleCode;
	private String menuCode;
	private Integer actionVal;
    private Date createDate;
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "role_code")
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String role_code) {
		this.roleCode = role_code;
	}
	
	@Column(name = "menu_code")
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menu_code) {
		this.menuCode = menu_code;
	}
	
    @Column(name = "action_val", columnDefinition="INT default 0")
	public Integer getActionVal() {
		return actionVal;
	}
	public void setActionVal(Integer actionVal) {
		this.actionVal = actionVal;
	}
	
	
    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}
