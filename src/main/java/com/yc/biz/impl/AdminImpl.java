package com.yc.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.biz.AdminBiz;
import com.yc.dao.AdminMapper;
import com.yc.po.AdminPO;

@Service("adminBiz")
public class AdminImpl implements AdminBiz{
	
	@Autowired
	public AdminMapper  adminMapper;
	

	@Override
	public AdminPO selectAll(String account, String pwd) {
		// TODO Auto-generated method stub
		return adminMapper.selectAll(account, pwd);
	}


	@Override
	public List<AdminPO> selectAllAdmin() {
		// TODO Auto-generated method stub
		return adminMapper.selectAllAdmin();
	}


	@Override
	public int insertAdmin(String account, String pwd) {
		// TODO Auto-generated method stub
		return adminMapper.insertAdmin(account, pwd);
	}


	@Override
	public int deleteAdmin(Integer aid) {
		// TODO Auto-generated method stub
		return adminMapper.deleteAdmin(aid);
	}
	
	
	

}
