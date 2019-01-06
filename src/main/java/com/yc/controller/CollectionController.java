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

import com.yc.biz.CollectionBiz;
import com.yc.po.CollectionPO;
import com.yc.po.UserInfoPO;

@Controller
public class CollectionController {

	@Autowired
	private CollectionBiz collectionBiz;

	@RequestMapping("/insertCollection")
	@ResponseBody
	public String insertCollection( CollectionPO cPo,HttpServletRequest request){
		boolean flag;
			//判断是否已经收藏过 
			UserInfoPO userinfopo=(UserInfoPO) request.getSession().getAttribute("user");
			String cname=userinfopo.getUname();
			
			cPo.setCname(cname);
			List<CollectionPO> cPoList= collectionBiz.selectCollection(cPo);
			if(cPoList!= null && cPoList.size()>0){
				//如果已经收藏就提示已经收藏
				return "have";
			}else{
				flag=collectionBiz.insertCollection(cPo);
				if(flag==true){
					return "yes";
				}else{
					return "error";
				}
			}		
	}
	
	
	@RequestMapping("/deleteCollection")
	@ResponseBody
	public boolean deleteCollectionAll(HttpServletRequest request,@RequestParam (value="tid",required=false)String tid){
		//一次删除多个
		List<String> list=new ArrayList<String>();
		if(tid!=null && !"".equals(tid)){
			list.add(tid);
			return collectionBiz.deleteCollection(list);
		}
		String collection_val=request.getParameter("collection_val");
		//System.out.println("收藏的id:"+collection_val);
		if(collection_val!=null && !"".equals(collection_val)){
			String[] value=collection_val.split(",");
			list=Arrays.asList(value);
			for(String i:value){			
				return collectionBiz.deleteCollection(list);
			}
		}
		return false;
	}
	
	@RequestMapping("/selectCollection")
	@ResponseBody
	public List<CollectionPO> selectCollection( CollectionPO cPo){
		List<CollectionPO> cPoList =null;
		if( null != cPo ){
			// 查询是否有收藏  若无直接返回 null
			cPoList= collectionBiz.selectCollection(cPo);
			if(null == cPoList){
				return null;
			}		
		}	
		return cPoList;
	}
	
	
	
}