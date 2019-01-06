package com.yc.biz;

import java.util.List;

import com.yc.po.ScanrecordPO;

public interface ScanrecordBiz {
	//查询浏览记录
	public List<ScanrecordPO> selectScanrecord(ScanrecordPO scanrecordPO);
	
	//插入浏览记录
	public boolean insertScanrecord(ScanrecordPO scanrecordPO);
	
	//根据id删除浏览记录
	public boolean deleteScanById(List<String> sid);
	
	//查询浏览量
	public int selectCount(String cname);
	
}
