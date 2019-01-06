package com.yc.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yc.dao.CollectionMapper;
import com.yc.po.CollectionPO;

public class TestCollection extends BaseJunit4Test{
	
	@Autowired
	CollectionMapper collentionMapper;
	
	@Test
	public void testinsertCollection(){
		CollectionPO collentionPO = new CollectionPO();
		collentionPO.setCname("许");
		collentionPO.setCtitle("欣林宾馆两室一厅出租");
		collentionMapper.insertCollection(collentionPO );
	}
	
	@Test
	public void testdeleteCollention(){
		List<String> list=new ArrayList<String>();
		list.add("1");
		list.add("2");
		collentionMapper.deleteCollection(list);
	}
	
	@Test
	public void testselectCollention(){
		CollectionPO collentionPO = new CollectionPO();
		List<CollectionPO> list = collentionMapper.selectCollection(collentionPO );
		System.out.println(list);
				
	}
	
	
}
