package com.yc.sendEmail;


public class SendEmail {
	public static void main(String[] args) {
		MailSenderInfo mailInfo = new MailSenderInfo();    
	      mailInfo.setMailServerHost("smtp.qq.com"); //邮件服务器主机  
	      mailInfo.setMailServerPort("25"); //端口   
	      mailInfo.setValidate(true);    
	      mailInfo.setUserName("2351889342@qq.com"); //邮箱账号   
	      mailInfo.setPassword("dmkbefcgvkwtecej");//您的邮箱开启服务的授权码
	      mailInfo.setFromAddress("2351889342@qq.com"); //发送方   
	      mailInfo.setToAddress("893381969@qq.com"); //接收方
	      mailInfo.setSubject("验证码");  //标题  
	      mailInfo.setContent("123456");  //内容  
	         //这个类主要来发送邮件   
	      SimpleMailSender sms = new SimpleMailSender();   
	          boolean haha=sms.sendTextMail(mailInfo);//发送文体格式 
	          if(haha){
	        	  System.out.println("温馨提示:邮件发送成功");
	          }else{
	        	  System.out.println("温馨提示:邮件发送失败");
	          }
	}
}

