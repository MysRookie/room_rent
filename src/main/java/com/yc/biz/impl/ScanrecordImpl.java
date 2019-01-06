package com.yc.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.biz.ScanrecordBiz;
import com.yc.dao.ScanrecordMapper;
import com.yc.po.ScanrecordPO;
@Service("scanrecordBiz")
public class ScanrecordImpl implements ScanrecordBiz{

	@Autowired
	ScanrecordMapper scanrecordMapper;
	@Override
	public List<ScanrecordPO> selectScanrecord(ScanrecordPO scanrecordPO) {
		return scanrecordMapper.selectScanrecord(scanrecordPO);
	}

	@Override
	public boolean insertScanrecord(ScanrecordPO scanrecordPO) {
		return scanrecordMapper.insertScanrecord(scanrecordPO);
	}

	

	@Override
	public int selectCount(String cname) {
		return scanrecordMapper.selectCount(cname);
	}

	@Override
	public boolean deleteScanById(List<String> sid) {
		// TODO Auto-generated method stub
		return scanrecordMapper.deleteScanById(sid);
	}

}
