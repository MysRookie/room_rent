package com.yc.po;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ScanrecordPO implements Serializable{
	private Integer sid;
	private String cname;
	private String ctitle;
	private String sdate;
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
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
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	@Override
	public String toString() {
		return "ScanrecordPO [sid=" + sid + ", cname=" + cname + ", ctitle=" + ctitle + ", sdate=" + sdate + "]";
	}	
}
