package com.yc.myconverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

//Converter接口很简单,就是把S类型转化成T类型.
public class DateConverter implements Converter<String, Date> {
	// TODO 自定义日期转换器
	public Date convert(String source) {
		// TODO 指定字符串装换为日期类型的格式(格式是yyyy-MM-dd)
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			System.out.println(source);
			// TODO 进行格式转换后返回
			return dateFormat.parse(source);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO 如果参数绑定失败则返回null
		return null;
	}

}
