package com.yc.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;
import com.yc.dao.HouseInfoMapper;
import com.yc.po.HouseInfoPO;

public class TestHouseInfo extends BaseJunit4Test{
	
	@Autowired
	HouseInfoMapper houseInfoMapper;
	
	@Test
	public void testselectHouseInfo(){
		HouseInfoPO HouseInfoPO = new HouseInfoPO();
//		HouseInfoPO.setPrice("700");
//		HouseInfoPO.setDeposit("1200");
//		HouseInfoPO.setDistrict("蒸湘区");
//		HouseInfoPO.setStrnum("欣林宾馆");
//		HouseInfoPO.setHtype("单间");
//		HouseInfoPO.setArea("80");
		HouseInfoPO.setPushstatus("审核中");
		List<HouseInfoPO> list = houseInfoMapper.selectHouseInfo(HouseInfoPO  );
		System.out.println(list);
	}
	
	@Test
	public void testinsertHouseInfo(){
		HouseInfoPO houseInfoPO = new HouseInfoPO(null, 1, "湖南省", "衡阳市", "珠晖区", "谜底酒店" , "单间", "单间卧室", "二楼201", "50", "未出租", "押一付三", "2018-10-10", "面向西", "房东很帅", "谜底主题酒店的单间出租", "无", "700", "700", "../89.png", null, null);		
		boolean flag = houseInfoMapper.insertHouseInfo(houseInfoPO);
		System.out.println(flag);
		
	}
	@Test
	public void testupdateHouseInfo(){
		HouseInfoPO houseInfoPO = new HouseInfoPO();
		houseInfoPO.setHid(1);
		
		houseInfoPO.setPrice("1200");
		houseInfoPO.setDeposit("1200");
		houseInfoMapper.updateHouseInfo(houseInfoPO );
	}
	
	@Test
	public void testselectHouseInfoPrice(){
		List<HouseInfoPO> list = houseInfoMapper.selectHouseInfoPrice("500", "1000");
		System.out.println(list);
	
	
	
	}
	
	
}
