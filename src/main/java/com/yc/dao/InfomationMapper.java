package com.yc.dao;

import java.util.List;

import com.yc.po.InfomationPO;

public interface InfomationMapper {
	
	//插入一条公告
	public int insertInfo(InfomationPO informationPO);
	
	//查询所有的公告
	public List<InfomationPO> selectInfo(InfomationPO informationPO);
	
	// 删除公告信息
	public int deleteInfo(InfomationPO informationPO);
	
	// 修改内容 	
	public boolean updateInfo(InfomationPO informationPO);
	
	//批量删除 公告信息
	public boolean batchDeleteInfo(List<Integer> fid);
	
}
