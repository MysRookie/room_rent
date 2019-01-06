package com.yc.po;

public class AdminPO {	//管理员表
	
	private Integer admin_id;
	private String account;	//账号
	private String pwd;	//密码
	public Integer getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(Integer admin_id) {
		this.admin_id = admin_id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Override
	public String toString() {
		return "admin [admin_id=" + admin_id + ", account=" + account + ", pwd=" + pwd + "]";
	}
	

}
