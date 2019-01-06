package com.yc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.po.AdminPO;


public class AdminloginFilter  implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("AdminFilter   init");	
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		// 获得管理员请求的URI
		//System.out.println(request.getRequestURI());
		AdminPO admin = (AdminPO) request.getSession().getAttribute("adminPO");		
		if (admin == null || "".equals(admin)) {
			response.sendRedirect("login.jsp");
		}else{
			chain.doFilter(request, response);
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("AdminFilter   destroy");
	}


}
