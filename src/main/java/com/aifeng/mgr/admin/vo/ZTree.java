package com.aifeng.mgr.admin.vo;

/**
 * 
 * @ClassName: ZTree 
 * @Description: TODO
 * @author: cosco gt.fan@msn.cn
 * @date: 2017年3月25日 下午1:47:55
 */
public class ZTree {

	private int id;
	private String name;
	private String code;
	private String attr;
	private int pId;
	private boolean isParent;
	private boolean open = true;
	
	private boolean isAction;
	
	private boolean checked;
	
	private int val;
	
	public ZTree(){}
	
	
	public ZTree(int id, int pId, String name){
		this(id, pId,name, false, false);
	}
	
	public ZTree(int id, int pId, String name, String code){
		this(id, pId,name, false, false);
		this.code = code;
	}
	
	public ZTree(int id, int pId, String name, boolean  isParent){
		this(id, pId,name, isParent, false);
	}
	
	public ZTree(int id, int pId, String name, boolean  isParent, boolean open){
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.isParent = isParent;
		this.open = open;
	}
	
	
	


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getAttr() {
		return attr;
	}

	public void setAttr(String attr) {
		this.attr = attr;
	}

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public boolean isAction() {
		return isAction;
	}

	public void setAction(boolean isAction) {
		this.isAction = isAction;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public boolean isParent() {
		return isParent;
	}
	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	
	
	
	
}
