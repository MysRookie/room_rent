package com.yc.po;

public class CommentPO {	//评论表
	
	private Integer cid;
	private String cname;		//用户名
	private String ctitle;		//房源
	private String info;	//主要内容信息
	private String grade;	//等级 1/2/3/4/5
	private String cdate;	//评论时间
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCtitle() {
		return ctitle;
	}
	public void setCtitle(String ctitle) {
		this.ctitle = ctitle;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getCdate() {
		return cdate;
	}
	public void setCdate(String cdate) {
		this.cdate = cdate;
	}
	@Override
	public String toString() {
		return "CommentPO [cid=" + cid + ", cname=" + cname + ", ctitle=" + ctitle + ", info=" + info + ", grade="
				+ grade + ", cdate=" + cdate + "]";
	}
	
	

}
