package com.yc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yc.po.AdminPO;

public interface AdminMapper {
	
	// 根据账户密码查询admin
	@Select( "select * from admin where account=#{account} and pwd=#{pwd}" )
	public AdminPO selectAll( @Param("account") String account,@Param("pwd") String pwd);
	
	//查看所有管理员
	@Select("select * from admin")
	public List<AdminPO> selectAllAdmin();
	
	//添加 管理员
	@Insert("insert into admin values(null,#{account},#{pwd})")
	public int insertAdmin( @Param("account") String account,@Param("pwd") String pwd );
	
	// 根据 id 删除 管理员 
	@Delete("delete from admin where aid=#{aid}")
	public int deleteAdmin(@Param("aid") Integer aid);
	

}
