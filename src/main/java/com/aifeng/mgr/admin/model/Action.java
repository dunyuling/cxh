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
@Table(name = "tb_mgr_action")
public class Action implements Serializable{
	
    private Integer id;  // action i
    
    private Integer val;   //   
    private String menuCode;
    private String name;
    private String attr1;
    private String attr2;
    private String attr3;
    private String attr4;
    private String attr5;
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
	
    
	@Column(name = "val",columnDefinition="INT default 0")
	public Integer getVal() {
		return val;
	}

	public void setVal(Integer val) {
		this.val = val;
	}

	public String getAttr1() {
		return attr1;
	}

	public void setAttr1(String attr1) {
		this.attr1 = attr1;
	}

	public String getAttr2() {
		return attr2;
	}

	public void setAttr2(String attr2) {
		this.attr2 = attr2;
	}

	public String getAttr3() {
		return attr3;
	}

	public void setAttr3(String attr3) {
		this.attr3 = attr3;
	}

	public String getAttr4() {
		return attr4;
	}

	public void setAttr4(String attr4) {
		this.attr4 = attr4;
	}

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

	@Column(name = "menu_code")
	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menu_code) {
		this.menuCode = menu_code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	

	
	
// SELECT tmur.role_code,tmr.name AS role_name, tmm.id AS menu_id, tmm.name AS menu_name, tma.id as user_id,action_val, menu_code, pid, url, weight,icon
// FROM tb_mgr_user_role tmur left join tb_mgr_admin tma on (tma.code = tmur.user_code)
// LEFT JOIN  tb_mgr_role tmr ON (tmur.`role_code` = tmr.code)
// RIGHT JOIN tb_mgr_role_menu tmrm ON (tmur.`role_code` = tmrm.`role_code`)
// LEFT JOIN tb_mgr_menu tmm ON(tmm.code = tmrm.`menu_code`)
// WHERE tmur.user_code = '" + userCode + "'
// ORDER BY tmm.code, weight
    
    

}
