package com.yc.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/code")
public class CodeController {
	
	private static final long serialVersionUID = 1L;
	
	 Random random = new Random();
	 String[] fontNames  = {"宋体", "华文楷体", "黑体", "微软雅黑", "楷体_GB2312"};

	 //获取随机字体	
	 Font getRandomFont () {
		int index = random.nextInt(fontNames.length);
		String fontName = fontNames[index]; //生成随机的字体名称
		int style = random.nextInt(4);      //生成随机的样式, 0(无样式), 1(粗体), 2(斜体), 3(粗体+斜体)
		int size = random.nextInt(5) + 24;  //生成随机字号, 24 ~ 28
		return new Font(fontName, style, size); 
	 }

	 /**
	  * 获取登陆验证码
	  * @param request
	  * @param response
	  * @throws IOException
	  */
	@RequestMapping("/getCode")
	public void gteCode (HttpServletRequest request, HttpServletResponse response) throws IOException{  
	    int width = 100;  
	    int height = 30;  
	    
	    //构造一个类型为预定义图像类型之一的 BufferedImage
	    BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);  
	    
	    //获取图形上下文    
	    Graphics g = image.getGraphics();  	      	  	
	    g.setColor(new Color(200+random.nextInt(50),200+random.nextInt(50),200+random.nextInt(50)));  //设置颜色
	    g.fillRect(0, 0, width, height);  //填充矩形，即为图片设置背景色   
	    
		//随机产生4位验证码并绘制到图像中
	    String[] codes = {"2","3","4","5","6","7","8","9","A","B","C","D","E","F","G","H","J","K","L","M","N","P","Q","R","S","T","U","V","W","X","Y","Z"};  
	    String code = "";  
	    for(int i=0;i<4;i++){  
	        String str = codes[random.nextInt(codes.length)];  
	        code += str;  
	        // 将验证码显示到图象中  
	        Color fontColor = new Color(random.nextInt(100),random.nextInt(100),random.nextInt(100));
	        g.setColor( fontColor );  //设置绘制验证码的颜色 
	        g.setFont(getRandomFont());    //设置字体  
	        g.drawString(str, 18*i+8,random.nextInt(13)+18);   
	    } 
	    
	    request.getSession().setAttribute("code", code);  // 将验证码存入 session 会话对象中 
	     
	    //随机产生5条干扰线  
	    for(int i = 0; i < 5; i++) { 
	    	g.setColor(new Color(random.nextInt(50),random.nextInt(50),random.nextInt(50)));  //干扰线的颜色   
	    	int x = random.nextInt(width);     
	        int y = random.nextInt(height);     
	        int xl = random.nextInt(width);     
	        int yl = random.nextInt(height);     
	        g.drawLine(x, y, xl, yl);  //使用当前颜色在点 (x, y) 和 (x1, y1) 之间画一条线。   
	    }
	    
	    g.dispose();  // 释放此图形的上下文以及它使用的所有系统资源    
	    
	    // 输出图象到页面     使用支持给定格式的任意 ImageWriter 将一个图像写入 ImageOutputStream（此方法在写入操作完成后不会 关闭提供的 ImageOutputStream）
	    ImageIO.write(image, "JPG", response.getOutputStream());	    
	}	
}
