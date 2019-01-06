package com.yc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.yc.biz.UserInfoBiz;
import com.yc.po.UserInfoPO;
import com.yc.sendEmail.MailSenderInfo;
import com.yc.sendEmail.SimpleMailSender;
import com.yc.util.GetSMS;

/*
 * 用户的控制层
 */
@Controller
@SessionAttributes(value={"user"},types={String.class})
@RequestMapping("/user")
public class UserInfoController {
	//spring注入实现类
	@Autowired
	private UserInfoBiz userInfoBiz;
	
	//用户注册的方法
	@RequestMapping("/insertUser")
	@ResponseBody
	public boolean insertUser(@RequestParam(value="tel") String tel,
							 @RequestParam(value="uname") String uname,
							 @RequestParam(value="upwd") String upwd
							 ){
		UserInfoPO userInfoPO = new UserInfoPO();
		userInfoPO.setTel(tel);
		userInfoPO.setUname(uname);
		userInfoPO.setUpwd(upwd);
		boolean flag = userInfoBiz.insertUser(userInfoPO);
		return flag;
	}
	
	//登录  将登陆的用户信息存入session
		@RequestMapping("/login2")
		@ResponseBody
		public List<UserInfoPO> findUser(UserInfoPO userInfoPO,ModelMap map, HttpSession session){	
			List<UserInfoPO> list=userInfoBiz.selectUser(userInfoPO);
			System.out.println(list);
			if(list!=null && list.size()>0){
				map.addAttribute("user", list.get(0));
			}		
			return list;
		}
	//登录  将登陆的用户信息存入session
		@RequestMapping("/login")
		@ResponseBody
		public int findUser(UserInfoPO userInfoPO,ModelMap map, HttpSession session,HttpServletRequest request){	
			//获取验证码的值
			String sessionVerifyCode = (String) request.getSession().getAttribute("code");
			//获取来自页面的验证码的值
			//userInfoPO.getCode();
			List<UserInfoPO> list=userInfoBiz.selectUser(userInfoPO);
			if(userInfoPO.getCode()!=null){				
				if (!userInfoPO.getCode().equalsIgnoreCase(sessionVerifyCode)) {
					return -1;		     	      
				}
			}
			if(list!=null && list.size()>0){
				map.addAttribute("user", list.get(0));
				return 1;
			}else{
				return 0;
			}		
		}
		//判断用户是否登录   从session中取值
		@RequestMapping("/yes")
		@ResponseBody
		public UserInfoPO yes(HttpSession session){	
			UserInfoPO userInfoPO=(UserInfoPO) session.getAttribute("user");
			return userInfoPO;
		}
		
		//根据编号查询  在执行更新操作之前查询
		@ModelAttribute
		public void findUserById(Map<String,Object> map,@RequestParam(value="uid",required=false)Integer uid){
			UserInfoPO userInfoPO=null;
			if(uid!=null){
				//System.out.println("用户编号："+uid);
				userInfoPO=userInfoBiz.findUserById(uid);
				//System.out.println(userInfoPO);
				map.put("userInfoPO", userInfoPO);
			}
		}
		//修改用户信息
		@RequestMapping("/updateUser")
		@ResponseBody
		public boolean update(UserInfoPO userInfoPO){
			System.out.println("修改时传入的对象"+userInfoPO);
			boolean updateUser = userInfoBiz.updateUser(userInfoPO);
			return updateUser;
		}
		
		//获取手机的验证码
		@RequestMapping("/getmessage")
		@ResponseBody
		public String getmessage(UserInfoPO userInfoPO,ModelMap map,@RequestParam("tel") String tel){
			List<UserInfoPO> list = userInfoBiz.selectUser(userInfoPO);//userinfo中有用户的手机号
			if(list.size()>0 && list!=null){  //如果数据里没有改手机号，就无法使用手机登录
				map.addAttribute("user", list.get(0));
				//数据库里有这个号码，则发送验证码
				GetSMS.getmMssage(tel);
				String randNum = GetSMS.randNum;
				return randNum;							
			}else{
				return "no";
			}
		}
		
		//手机验证码的判断
		@RequestMapping("/codetest")
		@ResponseBody
		public int codetest(UserInfoPO userInfoPO,@RequestParam("code") String code){
			//System.out.println(code);  //这是用户输入的验证码
			String randNum = GetSMS.randNum;  //这里是验证码的值
			if(code.equals(randNum)){
				return 1;
			}else{
				return 0;
			}
		}
		//手机验证码的判断
		@RequestMapping("/codetest2")
		@ResponseBody
		public int codetest2(UserInfoPO userInfoPO,@RequestParam("code") String code){
			String randNum = GetSMS.randNum;  //这里是验证码的值
				if(code.equals(randNum)){
					return 1;
				}else{
					return 0;
				}				
			
		}
		//发送邮件
		@RequestMapping("/sendemail")
		@ResponseBody
		public String sendEmail(@RequestParam (value="email",required=false)String email,
				@RequestParam (value="clientcode",required=false)String clientcode,Map<String,Object> map,HttpSession session){		
			System.out.println(email+"==客户端填写的验证码："+clientcode);
			String code=null;
			if((email!=null && !email.equals(""))&& (clientcode==null || clientcode.equals(""))){
				code=randomCode();
				session.setAttribute("code", code);
				System.out.println("服务器端生成的验证码："+code);	 
				MailSenderInfo mailInfo = new MailSenderInfo();    
			      mailInfo.setMailServerHost("smtp.qq.com"); //邮件服务器主机  
			      mailInfo.setMailServerPort("25"); //端口   
			      mailInfo.setValidate(true);    
			      mailInfo.setUserName("2351889342@qq.com"); //邮箱账号   
			      mailInfo.setPassword("dmkbefcgvkwtecej");//您的邮箱开启服务的授权码
			      mailInfo.setFromAddress("2351889342@qq.com"); //发送方   
			      mailInfo.setToAddress(email); //接收方
			      mailInfo.setSubject("验证码");  //标题       
			      mailInfo.setContent(code);  //内容  
			         //这个类主要来发送邮件   
			      SimpleMailSender sms = new SimpleMailSender();   
			          boolean haha=sms.sendTextMail(mailInfo);//发送文体格式 	          
			          if(haha){
			        	  System.out.println("温馨提示:邮件发送成功");	  
			        	  return code;
			          }else{
			        	  System.out.println("温馨提示:邮件发送失败");
			        	  code=null;
			          }
			}
			if(email!=null && !email.equals("")&& clientcode!=null && !clientcode.equals("")){
				code=(String) session.getAttribute("code");
				System.out.println("进入了"+code);
				if(clientcode.equals(code)){
					System.out.println("验证码输入正确");
					return "right";
				}else{
					System.out.println("验证码输入错误");
					return "error";
				}
			}
			return null;
		}
		//获取6位随机验证码
		public String randomCode(){
			Random random=new Random();
		    StringBuffer code=new StringBuffer();
		      for(int i=0;i<6;i++){
		    	 int num= random.nextInt(9);
		      	  code.append(num);
		      }
		      return code.toString();
		}
		
		
		
	    // 后台管理 
		
		//后台  查询 用户信息 
		@RequestMapping("/selectUser")
		@ResponseBody
		public Map<String,Object>selectUser( UserInfoPO userInfoPO ){
			if( null != userInfoPO){
				List<UserInfoPO> userList= userInfoBiz.selectUser(userInfoPO);			 
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("code", 0); // 数据响应的状态
				map.put("msg", "响应成功，小心操作哦");
			    map.put("count",userList.size());  // count  总记录 
			    map.put("data",userList);			
				return map;
			}
			return null;
		}
		
		//批量 删除 用户信息     consumes:验证请求数据类型 
		@RequestMapping(value="/batchDeleteUserInfo",consumes="application/json",method=RequestMethod.POST)
		@ResponseBody
		public boolean  batchDeleteUserInfo(@RequestBody List<UserInfoPO> userInfoList ){
			if(null != userInfoList){
				List<Integer> uids = new ArrayList<Integer>();
				for(UserInfoPO userinfo : userInfoList ){
					uids.add(userinfo.getUid());
				}
				return userInfoBiz.batchDeleteUserInfo(uids);
			}						
			return false;
		}
		
		// 根据id删除用户信息 
		@RequestMapping("/delectUserInfo")
		@ResponseBody
		public boolean delectUserInfo(Integer uid){
			if(null != uid){
				UserInfoPO userInfo = new UserInfoPO();
				userInfo.setUid(uid);
				return userInfoBiz.delectUser(userInfo);
			}
			return false;
		}
		
		
		// 注销
		@RequestMapping("/loginout")
		@ResponseBody
		public boolean loginout(HttpSession session){
			session.removeAttribute("user");
			return true;
		}
		
		
	}