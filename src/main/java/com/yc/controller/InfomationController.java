package com.yc.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yc.biz.InfomationBiz;
import com.yc.po.InfomationPO;

@Controller
public class InfomationController {
	
	@Autowired
	private InfomationBiz infomationBiz;

	@RequestMapping("/insertInfo")
	@ResponseBody
	public int insertInfo(InfomationPO informationPO) {
		if ( !"".equals(informationPO.getContent()) && null!=informationPO.getContent()) {
			// 插入系统 时间 
			SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
			String dateString = sdf.format( new Date());
			informationPO.setInfo_date(dateString);
			return infomationBiz.insertInfo(informationPO);
		}
		return 0;
	}

	@RequestMapping("/selectInformation")
	@ResponseBody
	public Map<String,Object> selectInfo(InfomationPO informationPO) {
		if (null != informationPO) {
			List<InfomationPO> infoList= infomationBiz.selectInfo(informationPO);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("code", 0); // 数据响应的状态
			map.put("msg", "响应成功，小心操作哦");
		    map.put("count",infoList.size());  // count  总记录 
		    map.put("data",infoList);	
			return map;
		}
		return null;
	}

	@RequestMapping("/deleteInfo")
	@ResponseBody
	public int deleteInfo(Integer fid) {
		if (null != fid) {
			InfomationPO informationPO = new InfomationPO();
			informationPO.setFid(fid);
			return infomationBiz.deleteInfo(informationPO);
		} 
		return 0;
	}
	
	@RequestMapping("/updateInfo")
	@ResponseBody
	public boolean deleteInfo( String content,Integer fid ) {
		if (null != content && content!="" ) {
			InfomationPO informationPO = new InfomationPO();
			informationPO.setContent(content);
			informationPO.setFid(fid);
			return infomationBiz.updateInfo(informationPO);
		} 
		return false;
	}
	
	// 批量删除 通知信息  @RequestBody(required=false)   
	@RequestMapping(value="/batchDeleteInfo",consumes="application/json",method=RequestMethod.POST)
	@ResponseBody
	public boolean batchDeleteInfo(@RequestBody List<InfomationPO> infoList) {
		System.out.println(infoList);		
		if( null != infoList){
			List<Integer>  fids = new ArrayList<Integer>(); 
			for(int i=0;i<infoList.size();i++ ){
				fids.add(infoList.get(i).getFid());
			}
			System.out.println(fids);
			return infomationBiz.batchDeleteInfo(fids);
		}
		return false;
	}
	
	
	
}
