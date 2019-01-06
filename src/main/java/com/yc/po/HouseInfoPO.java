package com.yc.po;

public class HouseInfoPO {	//房源表
	
	private Integer hid;	//房屋主键
	private Integer uid;		// 房东信息
	private String province;	//省份
	private String city;	//城市
	private String district;	//区县
	private String strnum;	//M街道N号
	private String htype;	//房屋出租类型 （整间/单间）
	private String hroom;	//房间类型
	private String floor;	//房屋楼层
	private String area;	//房屋面积（目前只考虑整体，可扩展至每一间）
	private String status;	//房屋状态 （已出租/未出租） 
	private String paytype;		//交付形式（押一付三）
	private String pushdate;	//发布日期
	private String aspection;	//房子朝的位置	(面向朝南)
	private String discrible;	//房屋描述
	private String title;	//房屋标题
	private String infrastr;	//基础设施（有/无    可扩展）
	private String price;		//租金（元/月）
	private String deposit;	//保证金
	private String picture;		//（图片URL：../system_currenttime.png）
	private String pushstatus;   //审核状态
	private String reason;      //审核的原因
	private String account;     //管理员的身份
	private UserInfoPO user;   //房东的信息
	private CollectionPO collentionPO;  
	
	public HouseInfoPO() {
		super();
	}
	public HouseInfoPO(Integer hid, Integer uid, String province, String city, String district, String strnum,
			String htype, String hroom, String floor, String area, String status, String paytype, String pushdate,
			String aspection, String discrible, String title, String infrastr, String price, String deposit,
			String picture, String reason, String account) {
		super();
		this.hid = hid;
		this.uid = uid;
		this.province = province;
		this.city = city;
		this.district = district;
		this.strnum = strnum;
		this.htype = htype;
		this.hroom = hroom;
		this.floor = floor;
		this.area = area;
		this.status = status;
		this.paytype = paytype;
		this.pushdate = pushdate;
		this.aspection = aspection;
		this.discrible = discrible;
		this.title = title;
		this.infrastr = infrastr;
		this.price = price;
		this.deposit = deposit;
		this.picture = picture;

		this.reason = reason;
		this.account = account;
		
	}
	public Integer getHid() {
		return hid;
	}
	public void setHid(Integer hid) {
		this.hid = hid;
	}
	
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getPushstatus() {
		return pushstatus;
	}
	public void setPushstatus(String pushstatus) {
		this.pushstatus = pushstatus;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getStrnum() {
		return strnum;
	}
	public void setStrnum(String strnum) {
		this.strnum = strnum;
	}
	public String getHtype() {
		return htype;
	}
	public void setHtype(String htype) {
		this.htype = htype;
	}
	public String getHroom() {
		return hroom;
	}
	public void setHroom(String hroom) {
		this.hroom = hroom;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPaytype() {
		return paytype;
	}
	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	public String getPushdate() {
		return pushdate;
	}
	public void setPushdate(String pushdate) {
		this.pushdate = pushdate;
	}
	public String getAspection() {
		return aspection;
	}
	public void setAspection(String aspection) {
		this.aspection = aspection;
	}
	public String getDiscrible() {
		return discrible;
	}
	public void setDiscrible(String discrible) {
		this.discrible = discrible;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getInfrastr() {
		return infrastr;
	}
	public void setInfrastr(String infrastr) {
		this.infrastr = infrastr;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDeposit() {
		return deposit;
	}
	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public UserInfoPO getUser() {
		return user;
	}
	public void setUser(UserInfoPO user) {
		this.user = user;
	}
	
	public CollectionPO getCollentionPO() {
		return collentionPO;
	}
	public void setCollentionPO(CollectionPO collentionPO) {
		this.collentionPO = collentionPO;
	}
	@Override
	public String toString() {
		return "HouseInfoPO [hid=" + hid + ", uid=" + uid + ", province=" + province + ", city=" + city + ", district="
				+ district + ", strnum=" + strnum + ", htype=" + htype + ", hroom=" + hroom + ", floor=" + floor
				+ ", area=" + area + ", status=" + status + ", paytype=" + paytype + ", pushdate=" + pushdate
				+ ", aspection=" + aspection + ", discrible=" + discrible + ", title=" + title + ", infrastr="
				+ infrastr + ", price=" + price + ", deposit=" + deposit + ", picture=" + picture + ", pushstatus="
				+ pushstatus + ", reason=" + reason + ", account=" + account + ", user=" + user + ", collentionPO="
				+ collentionPO + "]";
	}
}
