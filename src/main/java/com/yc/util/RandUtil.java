package com.yc.util;

import java.util.Random;

import javax.servlet.http.HttpServletResponse;

public class RandUtil {

	public  static String  getRandomNum(){
        Random random=new Random();
        String randomNum = random.nextInt(1000000) + "";
        if(randomNum.length()!=6){
          //  System.out.println("6位伪随机数："+randomNum);
            return  getRandomNum();
        }
       // System.out.println("6位随机数："+randomNum);
        return randomNum;
    }

	public static void main(String[] args) {
		//System.out.println(RandUtil.getRandomNum());
	}

	public static void write(HttpServletResponse response, String string) {
		
	}
}
