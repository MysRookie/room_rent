package com.yc.po;

public class InfomationPO {	//通知表
	
	private Integer fid;
	private String content;		//内容	(租房信息)
	private String info_date;	//推送时间
	private String info_type;	//通知类型（新闻/公告)
	public Integer getFid() {
		return fid;
	}
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getInfo_date() {
		return info_date;
	}
	public void setInfo_date(String info_date) {
		this.info_date = info_date;
	}
	public String getInfo_type() {
		return info_type;
	}
	public void setInfo_type(String info_type) {
		this.info_type = info_type;
	}
	@Override
	public String toString() {
		return "Information [fid=" + fid + ", content=" + content + ", info_date=" + info_date + ", info_type="
				+ info_type + "]";
	}
	

}
