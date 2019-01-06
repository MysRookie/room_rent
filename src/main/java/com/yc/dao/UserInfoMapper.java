package com.yc.dao;

import java.util.List;

import com.yc.po.UserInfoPO;

public interface UserInfoMapper {

	//注册用户
	public boolean insertUser(UserInfoPO userInfoPO);
	
	//查询用户的信息
	public List<UserInfoPO> selectUser(UserInfoPO userInfoPO);
	
	//修改用户的信息
	public boolean updateUser(UserInfoPO userInfoPO);
	
	//删除用户
	public boolean delectUser(UserInfoPO userInfoPO);
	
	//根据id查询用户
	public UserInfoPO findUserById(Integer uid);
	
	// 批量删除 用户信息 
	public boolean batchDeleteUserInfo(List<Integer> uid);
}
