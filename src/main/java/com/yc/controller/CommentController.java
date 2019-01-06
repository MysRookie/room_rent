package com.yc.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yc.biz.CommentBiz;
import com.yc.po.CommentPO;
import com.yc.po.UserInfoPO;

@Controller
public class CommentController {
	@Autowired
	CommentBiz commentBiz;
	
	@RequestMapping("/selectComment")
	@ResponseBody
	public List<CommentPO> selectComment(CommentPO comPO){
		List<CommentPO> list=commentBiz.selectComment(comPO);
		return list;
	}
	
	@RequestMapping("/insertComment")
	@ResponseBody
	public boolean insertComment(CommentPO comPO, HttpSession session){
		UserInfoPO userInfoPO=(UserInfoPO) session.getAttribute("user");
		String uname = userInfoPO.getUname();   //取出session中的用户名存入compo对象中
		String info = comPO.getInfo();
		comPO.setCname(uname);
		if(info.equals("")){
			return false;			
		}else{
			return commentBiz.insertComment(comPO);			
		}
		
	}

	
	@RequestMapping("/deleteCommentById")
	@ResponseBody
	public boolean deleteCommentById(HttpServletRequest request,@RequestParam (value="cid",required=false)String cid){
		List<String> list=new ArrayList<String>();
		if(cid!=null){		
			list.add(cid);
			return commentBiz.deleteCommentById(list);
		}
		String comment_val=request.getParameter("comment_val");
		if(comment_val!=null && !"".equals(comment_val)){			
			String[] cids=comment_val.split(",");
			list=Arrays.asList(cids);
			return commentBiz.deleteCommentById(list);
		}
		return false;
	}

	
}
