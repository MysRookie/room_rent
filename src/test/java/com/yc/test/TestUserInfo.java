package com.yc.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yc.dao.UserInfoMapper;
import com.yc.po.UserInfoPO;

public class TestUserInfo extends BaseJunit4Test{
	
	@Autowired
	UserInfoMapper userinfo;
	
	@Test
	public void testinsertUser(){
		
//		UserInfoPO user = new UserInfoPO();
//		user.setUname("刘万夫4");
//		user.setUpwd("a");
//		user.setTel("13245");
//		userinfo.insertUser(user);
	}
	
	@Test
	public void testselectUser(){
		
		UserInfoPO userInfoPO = new UserInfoPO();
		userInfoPO.setUname("刘万夫");
		userInfoPO.setUpwd("b");
		List<UserInfoPO> list = userinfo.selectUser(userInfoPO );
		System.out.println(list);
	}
	
	@Test
	public void testupdateUser(){
		UserInfoPO userInfoPO = new UserInfoPO();
		userInfoPO.setUid(1);
		userInfoPO.setUpwd("b");
		 boolean updateUser = userinfo.updateUser(userInfoPO);
		System.out.println(updateUser);
	}
	@Test
	public void testdelectUser(){
		UserInfoPO userInfoPO = new UserInfoPO();
		userInfoPO.setUid(10);
		boolean flag = userinfo.delectUser(userInfoPO );
		System.out.println(flag);
	}
	
}
