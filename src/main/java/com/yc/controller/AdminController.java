package com.yc.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.yc.biz.AdminBiz;
import com.yc.po.AdminPO;


@Controller
@RequestMapping("/admin")
@SessionAttributes(value="adminPO")
public class AdminController {
	

	@Autowired
	private AdminBiz adminBiz;
	
	
	/*
	 * 管理员登录 
	 * */
	@RequestMapping("/selectAll")
	@ResponseBody
	public String selectAll(boolean flag,AdminPO adminPO,
			                HttpServletResponse response,HttpServletRequest request ) {
		String account =adminPO.getAccount();
		String pwd = adminPO.getPwd();
		adminPO = adminBiz.selectAll(account,pwd);
		//账户 记住密码
		if (flag) {
			System.out.println(flag);
			Cookie[] cookies = request.getCookies();
			if (null != cookies) {
				//循环cookies数组  判断 cookie不存在 再添加  
				for (int i = 0; i < cookies.length; i++) {
					System.out.println(cookies[i].getValue());
					if (!account.equals(cookies[i].getValue()) && !pwd.equals(cookies[i].getValue())) {						
						addCookies(account, pwd, response);
					}
				}
			}else{
				addCookies(account, pwd, response);
			}
		}
		if (adminPO != null) {
			return "1";
		}
		return "0";
	}
	
	//添加 cookies
	public void addCookies(String account,String pwd, HttpServletResponse response){
		Cookie cookie1 = new Cookie("account", account);
		Cookie cookie2 = new Cookie("pwd", pwd);
		cookie1.setMaxAge(24 * 60 * 60);
		cookie2.setMaxAge(60 * 60);
		cookie1.setPath("/");
		cookie2.setPath("/"); // 本地应用共享
		// cookie1.setDomain("");  cookie跨域共享
		response.addCookie(cookie1);
		response.addCookie(cookie2);				
	}
	
	/*
	 * 查看所有的管理员信息 
	 * */
	@RequestMapping("/selectAllAdmin")
	@ResponseBody
	public List<AdminPO> selectAllAdmin(){
		return adminBiz.selectAllAdmin();		
	}
	
	/*
	 * 添加管理员信息 
	 * */
	@RequestMapping("/insertAdmin")
	@ResponseBody
	public int insertAdmin(AdminPO adminPO){
		if( null!=adminPO ){
			//插入之前 判断是否重名 
			 if(null == adminBiz.selectAll(adminPO.getAccount(), adminPO.getPwd())){
				 return 0;
			 }
		}
		return adminBiz.insertAdmin(adminPO.getAccount(), adminPO.getPwd());		
	}
	
	/*
	 * 删除管理员信息 
	 * */
	@RequestMapping("/deleteAdmin")
	@ResponseBody
	public int deleteAdmin( Integer aid ){
		if( null == aid ){
			return 0;
		}
		return adminBiz.deleteAdmin(aid);		
	}
	
	/*
	 * 退出系统 
	 * */
	@RequestMapping("/loginOut" )
	@ResponseBody
	public String loiginOut(HttpSession session){
		//session.invalidate();  // 使session全部信息失效 
		session.removeAttribute("pwd");  // 使密码/账户失效
		return 	"1";	
	}
	
	/*
	 * sendAdvanceToAdmin  用户意见处理  
	 * */
	@RequestMapping("/sendAdvanceToAdmin" )
	@ResponseBody
	public boolean sendAdvanceToAdmin( String advance ){
		if(null != advance){
			//暂时 模拟处理  
			return  true;
		}		
		return  false;	
	}
	

}
