package com.yc.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yc.biz.UserInfoBiz;
import com.yc.dao.UserInfoMapper;
import com.yc.po.UserInfoPO;

@Repository("userInfoBiz")
public class UserInfoImpl implements UserInfoBiz {

	@Autowired
	UserInfoMapper userInfoMapper;
	
	public boolean insertUser(UserInfoPO userInfoPO) {
		return userInfoMapper.insertUser(userInfoPO);
	}

	public List<UserInfoPO> selectUser(UserInfoPO userInfoPO) {
		return userInfoMapper.selectUser(userInfoPO);
	}

	public boolean updateUser(UserInfoPO userInfoPO) {
		return userInfoMapper.updateUser(userInfoPO);
	}

	public boolean delectUser(UserInfoPO userInfoPO) {
		return userInfoMapper.delectUser(userInfoPO);
	}

	@Override
	public UserInfoPO findUserById(Integer uid) {
		return userInfoMapper.findUserById(uid);
	}

	@Override
	public boolean batchDeleteUserInfo(List<Integer> uid) {
		// TODO Auto-generated method stub
		return userInfoMapper.batchDeleteUserInfo(uid);
	}
	
}
