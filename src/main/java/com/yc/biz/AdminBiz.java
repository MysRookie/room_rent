package com.yc.biz;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yc.po.AdminPO;

public interface AdminBiz {
	
	// 根据账户密码查询admin
		public AdminPO selectAll(String account, String pwd);

		// 查看所有管理员

		public List<AdminPO> selectAllAdmin();

		// 添加 管理员

		public int insertAdmin(@Param("account") String account, @Param("pwd") String pwd);

		// 根据 id 删除 管理员

		public int deleteAdmin(@Param("aid") Integer aid);
	
}
