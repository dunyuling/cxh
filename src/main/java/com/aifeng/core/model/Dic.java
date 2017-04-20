package com.aifeng.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table( name = "tb_sys_dic",uniqueConstraints={@UniqueConstraint(columnNames = {"type","code"})})
public class Dic{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4114825291532997479L;
	
    private Integer id;
	
    private String type;   // 字典类型   重复表示是一个list
    
    private String code;  // 字典代码  // 唯一值
	
    private String name;  // 字典名称    可以为空
    
    private String value;   // 字典值
    
    private String note;    // 描述
    
    private int isfixed;
    
    private String attr1;// 预留
    private String attr2;
    private String attr3;
    private String attr4;
    private String attr5;
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	@Column(name="type", columnDefinition="varchar(16) not null")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="value", columnDefinition="varchar(2048)")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name="is_fixed", columnDefinition="int default 0")
	public int getIsfixed() {
		return isfixed;
	}

	public void setIsfixed(int isfixed) {
		this.isfixed = isfixed;
	}

	@Column(name="code", columnDefinition="varchar(16) not null")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	
	
    
	
	
    
    
    
    
    

}
