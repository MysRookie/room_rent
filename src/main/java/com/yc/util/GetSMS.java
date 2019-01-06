package com.yc.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import net.sf.json.JSONObject;

public class GetSMS {

	 /**
     * 用户ID
     */
    public static final String ACCOUNT_SID = "2d3f3481874f4f93a720c65e73216860";//这里填写你在平台里的ACOUNT_SID

    /**
     * 密钥
     */
    public static final String AUTH_TOKEN = "2ce2968499c44548b04d143a5bb21abc";

    /**
     * 请求地址前半部分
     */
    public static final String BASE_URL = "https://api.miaodiyun.com/20150822/industrySMS/sendSMS";//请求地址是固定的不用改
    /**
     * 随机码		
     */
    public static String randNum = RandUtil.getRandomNum();
    public static String smsContent = "【蓝天科技】登录验证码："+randNum+"，如非本人操作，请忽略此短信。";

    /**
     * 获取验证码
     *
     * @param to
     * @return
     */
    public static String getmMssage(String to) {
        String args = SendNumUtil.queryArgs(ACCOUNT_SID, AUTH_TOKEN, smsContent, to);
        OutputStreamWriter out = null;
        InputStream in = null;
        BufferedReader br = null;
        StringBuffer sb = new StringBuffer();
        try {
            URL url = new URL(BASE_URL);
            URLConnection connection = url.openConnection();//打开连接
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setConnectTimeout(5000);  //设置链接超时
            connection.setReadTimeout(10000);    //设置读取超时
            out = new OutputStreamWriter(connection.getOutputStream(),"utf-8");
            out.write(args);
            out.flush();
            //读取返回数据
            br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            while((line = br.readLine())!=null){
                sb.append(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = JSONObject.fromObject(sb.toString());
      //  System.out.println(jsonObject);
        Object object = jsonObject.get("respCode");
        System.out.println("状态码："+object+"验证码："+randNum);
     //   System.out.println(!object.equals("00000"));
        return jsonObject.toString();

    }
}
