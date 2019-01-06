package com.yc.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yc.dao.InfomationMapper;
import com.yc.po.InfomationPO;

public class TestInfomation extends BaseJunit4Test{
	
	@Autowired
	InfomationMapper infomationMapper;
	
	@Test
	public void testselectInfo(){
		System.out.println(infomationMapper);
		InfomationPO informationPO = new InfomationPO();
		informationPO.setInfo_type("公告");
		informationPO.setInfo_date("2018-12-03");
		List<InfomationPO> list = infomationMapper.selectInfo(informationPO);
		System.out.println(list);
	}
	
	@Test
	public void testInsertInfo(){
		
		InfomationPO informationPO = new InfomationPO();
		informationPO.setContent("南华一位帅哥发布了房源，有兴趣的客户可以联系");
		informationPO.setInfo_date("2018-10-11");
		informationPO.setInfo_type("公告");
		infomationMapper.insertInfo(informationPO );
		
	}
	
}
