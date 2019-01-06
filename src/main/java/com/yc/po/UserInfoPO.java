package com.yc.po;

public class UserInfoPO{		//用户表
	
	private Integer uid;	
	private String uname;	//用户名
	private String firstname;
	private String upwd;	//密码
	private String sex;		//性别
	private String idcard;	//身份证号码
	private String tel;		//电话号码
	private String email;	//邮箱
	private String utype;	//用户类型：房东/客户
	private String ugrade;   // 用户等级 
	private String code;			//存储验证码的属性
	
	private HouseInfoPO houseInfoPO;
	
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public HouseInfoPO getHouseInfoPO() {
		return houseInfoPO;
	}
	public void setHouseInfoPO(HouseInfoPO houseInfoPO) {
		this.houseInfoPO = houseInfoPO;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpwd() {
		return upwd;
	}
	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUtype() {
		return utype;
	}
	public void setUtype(String utype) {
		this.utype = utype;
	}
	
	public String getUgrade() {
		return ugrade;
	}
	public void setUgrade(String ugrade) {
		this.ugrade = ugrade;
	}
	@Override
	public String toString() {
		return "UserInfoPO [uid=" + uid + ", uname=" + uname + ", firstname=" + firstname + ", upwd=" + upwd + ", sex="
				+ sex + ", idcard=" + idcard + ", tel=" + tel + ", email=" + email + ", utype=" + utype + ", ugrade="
				+ ugrade + ", code=" + code + ", houseInfoPO=" + houseInfoPO + "]";
	}
	
	
	
	
	

}
