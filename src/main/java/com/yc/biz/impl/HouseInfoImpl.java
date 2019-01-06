package com.yc.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yc.biz.HouseInfoBiz;
import com.yc.dao.HouseInfoMapper;
import com.yc.po.HouseInfoPO;

@Repository("houseInfoBiz")
public class HouseInfoImpl implements HouseInfoBiz {
	
	@Autowired
	HouseInfoMapper houseInfoMapper;
	
	public List<HouseInfoPO> selectHouseInfo(HouseInfoPO houseInfoPO) {
		return houseInfoMapper.selectHouseInfo(houseInfoPO);
	}

	public boolean insertHouseInfo(HouseInfoPO houseInfoPO) {
		return houseInfoMapper.insertHouseInfo(houseInfoPO);
	}

	public boolean updateHouseInfo(HouseInfoPO houseInfoPO) {
		return houseInfoMapper.updateHouseInfo(houseInfoPO);
	}

	@Override
	public List<HouseInfoPO> selectHouseInfoPrice(String pricemin, String pricemax) {
		return houseInfoMapper.selectHouseInfoPrice(pricemin, pricemax);
	}

	@Override
	public Integer selectUnpassedNum() {
		// TODO Auto-generated method stub
		return houseInfoMapper.selectUnpassedNum();
	}

	@Override
	public boolean deleteHouseInfo(List<String> hid) {
		// TODO Auto-generated method stub
		return houseInfoMapper.deleteHouseInfo(hid);
	}

	@Override
	public List<HouseInfoPO> selecttitle(HouseInfoPO houseInfoPO) {
		// TODO Auto-generated method stub
		return houseInfoMapper.selecttitle(houseInfoPO);
	}

	@Override
	public boolean batchUpdateHouseInfoPushstatus(List<Integer> hid, String pushstatus, String account) {
		// TODO Auto-generated method stub
		return houseInfoMapper.batchUpdateHouseInfoPushstatus(hid, pushstatus, account);
	}

	
}
