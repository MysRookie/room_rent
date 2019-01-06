package com.yc.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yc.biz.InfomationBiz;
import com.yc.dao.InfomationMapper;
import com.yc.po.InfomationPO;

@Repository("infomationBiz")
public class InfomationImpl implements InfomationBiz {

	@Autowired
	InfomationMapper infomationMapper;
	
	public int insertInfo(InfomationPO informationPO) {
		return infomationMapper.insertInfo(informationPO);
	}

	public List<InfomationPO> selectInfo(InfomationPO informationPO) {
		return infomationMapper.selectInfo(informationPO);
	}

	@Override
	public int deleteInfo(InfomationPO informationPO) {
		// TODO Auto-generated method stub
		return infomationMapper.deleteInfo(informationPO);
	}

	@Override
	public boolean updateInfo(InfomationPO informationPO) {
		// TODO Auto-generated method stub
		return infomationMapper.updateInfo(informationPO);
	}

	@Override
	public boolean batchDeleteInfo(List<Integer> fid) {
		// TODO Auto-generated method stub
		return infomationMapper.batchDeleteInfo(fid);
	}

}
