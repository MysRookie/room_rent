package com.yc.po;

public class CollectionPO {	//收藏表
	
	private Integer tid;
	private String  cname;		//收藏所有者 
	private String ctitle;		//收藏内容
	private String tdate;	//收藏时间
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
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
	@Override
	public String toString() {
		return "CollentionPO [tid=" + tid + ", cname=" + cname + ", ctitle=" + ctitle + ", tdate=" + tdate + "]";
	}
	public String getTdate() {
		return tdate;
	}
	public void setTdate(String tdate) {
		this.tdate = tdate;
	}

	
	

}
