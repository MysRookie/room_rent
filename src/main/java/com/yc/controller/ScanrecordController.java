package com.yc.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yc.biz.ScanrecordBiz;
import com.yc.po.ScanrecordPO;
import com.yc.po.UserInfoPO;

@Controller
public class ScanrecordController {
	
	@Autowired
	ScanrecordBiz scanrecordBiz;
	
	@RequestMapping("selectScanCount")
	@ResponseBody
	public int selectCount(@RequestParam(value="ctitle",required=false)String ctitle){	
		if(ctitle!=null && !"".equals(ctitle)){
			return scanrecordBiz.selectCount(ctitle);
		}
		return 0;	
	}
	@RequestMapping("selectScanrecord")
	@ResponseBody
	public List<ScanrecordPO> selectScanrecord(ScanrecordPO scanrecordPO){
		if(scanrecordPO!=null && !"".equals(scanrecordPO)){
			return scanrecordBiz.selectScanrecord(scanrecordPO);			
		}
		return null;	
	}
	@RequestMapping("insertScan")
	@ResponseBody
	public boolean insertScanCount(ScanrecordPO scanrecordPO,HttpServletRequest request){		
		if(scanrecordPO!=null && !"".equals(scanrecordPO)){
			//判断是否已经浏览过 
			UserInfoPO upo=(UserInfoPO)request.getSession().getAttribute("user");
			scanrecordPO.setCname(upo.getUname());
			List<ScanrecordPO> cPoList= scanrecordBiz.selectScanrecord(scanrecordPO);
			if( cPoList != null && cPoList.size()>0){
				return false;
			}
			return scanrecordBiz.insertScanrecord(scanrecordPO);
		}
		return false;	
	}
	@RequestMapping("deleteScanById")
	@ResponseBody
	public boolean deleteScanById(@RequestParam(value="sid",required=false)String sid,HttpServletRequest request){		
		List<String> sidlist=new ArrayList<String>();
		if(sid!=null && !"".equals(sid)){			
			sidlist.add(sid);
			return scanrecordBiz.deleteScanById(sidlist);
		}
		String sids=request.getParameter("scan_val");
		if(sids!=null && !"".equals(sids)){
			String[] value=sids.split(",");
			sidlist=Arrays.asList(value);
			return scanrecordBiz.deleteScanById(sidlist);
		}
		return false;	
	}
	
	
	
}
