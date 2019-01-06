package com.yc.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yc.biz.ScanrecordBiz;
import com.yc.po.ScanrecordPO;

public class TestScanrecord	extends BaseJunit4Test {

	@Autowired
	ScanrecordBiz scanrecordBiz;
	
	@Test
	public void testInsertScan(){
		ScanrecordPO scanrecordPO=new ScanrecordPO();
		scanrecordPO.setCname("许");		
		scanrecordPO.setCtitle("欣林宾馆两室一厅出租");
		System.out.println(scanrecordBiz.insertScanrecord(scanrecordPO));
	}
	@Test
	public void testselectcount(){
		
		System.out.println(scanrecordBiz.selectCount("欣林宾馆两室一厅出租"));
	}
	
	@Test
	public void testselectScanrecord(){
		ScanrecordPO scanrecordPO=new ScanrecordPO();
		scanrecordPO.setCname("许");				
		System.out.println(scanrecordBiz.selectScanrecord(scanrecordPO));
	}
	
	@Test
	public void testDeleteById(){						
		List<String> sidlist=new ArrayList<String>();
		sidlist.add("3");
		sidlist.add("4");
		System.out.println(scanrecordBiz.deleteScanById(sidlist));
	}
	
	
}
