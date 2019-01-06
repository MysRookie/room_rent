package com.yc.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.yc.biz.HouseInfoBiz;
import com.yc.po.AdminPO;
import com.yc.po.HouseInfoPO;
import com.yc.po.UserInfoPO;

@Controller
//@Transactional(rollbackFor={Exception.class})
//@SessionAttributes(value="")
public class HouseInfoController {
	
	@Autowired
	private HouseInfoBiz hbiz;
	
	/*
	 * 显示房屋的信息
	 */
	@RequestMapping("showHouse")
	@ResponseBody
	public List<HouseInfoPO> showHouse(HouseInfoPO houseInfoPO){
		houseInfoPO.setPushstatus("已通过");
		List<HouseInfoPO> list = hbiz.selectHouseInfo(houseInfoPO);
		return list;
	}
	
	/*
	 * 发布房源
	 */
	@RequestMapping(value="/inserthouse",method=RequestMethod.POST)
	@ResponseBody
	public boolean inserthouse(HouseInfoPO houseInfoPO, 
			@RequestParam(value="image",required=false) CommonsMultipartFile[] files,HttpServletRequest request,HttpSession session){		
		//System.out.println("into ...");
		//System.out.println(houseInfoPO+"====="+files);
		boolean flag = false;
		// 文件上传预处理 
		String url =uploadImage(files, request);
		//System.out.println(url);
	    if( null != url && url!= "" && houseInfoPO.getTitle() != null ){	    	
	    	UserInfoPO user=(UserInfoPO) session.getAttribute("user");
	    	houseInfoPO.setUid(user.getUid());
	    	houseInfoPO.setPushdate( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) );
	    	houseInfoPO.setPicture(url);
	    	houseInfoPO.setDeposit("1000");
	    	houseInfoPO.setInfrastr("有");
	    	houseInfoPO.setStatus("未出租");	    	
	    	System.out.println(houseInfoPO);
	 		flag = hbiz.insertHouseInfo(houseInfoPO);	
	    }
		return flag;
	}

	
	@RequestMapping("showuser")
	@ResponseBody
	public int showuser(HouseInfoPO houseInfoPO){
		List<HouseInfoPO> list = hbiz.selecttitle(houseInfoPO);
		//System.out.println(list.get(0).getUid());
		return list.get(0).getUid();
	}
	
	
	// 后台房屋信息审核
		@RequestMapping("updateHouseInfo")
		@ResponseBody
		public boolean updateHouseInfo(@RequestParam(value="status",required=false) String status, HouseInfoPO houseInfoPO){
			if(  null == houseInfoPO ){
				return false;
			}else{
				return 	hbiz.updateHouseInfo(houseInfoPO);
			}		
		}
			
		
		
		/**
		 * 显示我的发布
		 * @param houseInfoPO
		 * @return
		 */
		@RequestMapping(value="/showMyHouseInfo/{id}")
		@ResponseBody
		public List<HouseInfoPO> showMyHouseInfo(@PathVariable Integer id,HttpServletRequest request){
			HouseInfoPO houseInfoPO=new HouseInfoPO();
			UserInfoPO userInfoPO =(UserInfoPO) request.getSession().getAttribute("user");
			houseInfoPO.setUid(userInfoPO.getUid());
			if(id==1){
				houseInfoPO.setPushstatus("已通过");
			}else{
				houseInfoPO.setPushstatus("未通过");
			}		
			List<HouseInfoPO> list = hbiz.selectHouseInfo(houseInfoPO);
			//System.out.println(list);
			return list;
		}
		
		
		// 后台 房源信息 检索 
		@RequestMapping("showHouseInfo")
		@ResponseBody
		public Map<String,Object> showHouseInfo(HouseInfoPO houseInfoPO , 
											String pushstatus){
			//System.out.println(houseInfoPO);	
			Map<String,Object> map ;
			if( null == pushstatus   ){
				return null;
			}else if(pushstatus.equals("passing")){
				//System.out.println(pushstatus);
				houseInfoPO = new HouseInfoPO();
				houseInfoPO.setPushstatus("审核中");
				//System.out.println(houseInfoPO);
				List<HouseInfoPO> passingList= hbiz.selectHouseInfo(houseInfoPO);
				map = new HashMap<String,Object>();
				map.put("code", 0); 
				map.put("msg", "响应成功，小心操作哦");
			    map.put("count",passingList.size()); 
			    map.put("data",passingList);
			    System.out.println(map);
				return map;
			}else if(pushstatus.equals("passed")){
				//System.out.println(pushstatus);
				houseInfoPO = new HouseInfoPO();
				houseInfoPO.setPushstatus("已通过");
				List<HouseInfoPO> passedList= hbiz.selectHouseInfo(houseInfoPO);
				map = new HashMap<String,Object>();
				map.put("code", 0); 
				map.put("msg", "响应成功，小心操作哦");
			    map.put("count",passedList.size()); 
			    map.put("data",passedList);
				return map;
			}else if(pushstatus.equals("unpassed")) {
				//System.out.println(pushstatus);
				houseInfoPO = new HouseInfoPO();
				houseInfoPO.setPushstatus("未通过");
				List<HouseInfoPO> unpassedList= hbiz.selectHouseInfo(houseInfoPO);
				map = new HashMap<String,Object>();
				map.put("code", 0); 
				map.put("msg", "响应成功，小心操作哦");
			    map.put("count",unpassedList.size()); 
			    map.put("data",unpassedList);
				return map;
			}
			//System.out.println();
			return null;
		}
		
		// 查看待审核的记录数 
		@RequestMapping("selectUnpassedNum")
		@ResponseBody
		public Integer selectUnpassedNum(){		
			return hbiz.selectUnpassedNum();
		}
		
		
		//删除多个
		@RequestMapping("/deleteHouse")
		@ResponseBody
		public boolean deleteHouse(HttpServletRequest request,@RequestParam (value="hid",required=false)String hid){
			//一次删除多个
			List<String> list=new ArrayList<String>();
			if(hid!=null && !"".equals(hid)){
				list.add(hid);
				return hbiz.deleteHouseInfo(list);
			}
			String hids=request.getParameter("hids");
			System.out.println(hids);
			if(hids!=null && !"".equals(hids)){
				String[] value=hids.split(",");
				list=Arrays.asList(value);
				return hbiz.deleteHouseInfo(list);
			}
			return false;
		}	
		
		//批量审核房源信息
		@RequestMapping(value="/batchUpdateHouseInfoPushstatus",method=RequestMethod.POST)
		@ResponseBody
		public boolean batchUpdateHouseInfoPushstatus(@RequestParam(value="hid") List<Integer> hidList,HttpSession session ){
			if( null!= hidList  ){
				System.out.println(hidList );
				AdminPO adminPO =(AdminPO)session.getAttribute("adminPO");			
				return hbiz.batchUpdateHouseInfoPushstatus(hidList, "已通过", adminPO.getAccount());
			}		
			return false;
		}
		
	
		//  图片上传
		public String uploadImage( CommonsMultipartFile []files,HttpServletRequest request){
			  StringBuffer sb = new StringBuffer();
			  if(null == files)
				  return null;
			  try {						
					//获取图片上传保存的路径  
					// request.getServletContext().getRealPath("")  获取当前项目的路径
					String pathString = request.getServletContext().getRealPath("")+"../images/";
					//System.out.println(pathString);
					for(int i=0;i<files.length;i++){
						// 图片重命名  统一格式：系统毫秒数+图片后缀名  
						//截取图片后缀 
						//  .jpg  .gif  .png  .bmp 只需截取后四位 
						// .jpeg 需要截取五位
						int index = files[i].getOriginalFilename().length()-4;
						// 判断图片是否以 .jpeg 结尾
						if( files[i].getOriginalFilename().endsWith(".jpeg" ) ){
							index = files[i].getOriginalFilename().length()-5;
						}					
						//System.out.println(files[i].getOriginalFilename().substring(index));
						String  savePath = pathString+System.currentTimeMillis()+files[i].getOriginalFilename().substring(index);								
						File newFile = new File(savePath);
						//File dest = new File(System.currentTimeMillis()+newFile.getName());
						//上传文件日志
						files[i].transferTo(newFile);
						//设置到对象中 按规定路径存入数据库
						String picPath= "../images/"+newFile.getName();
						//每张图片之间用逗号隔开
						sb.append(picPath).append(",");
					}
					//System.out.println(sb.toString());					
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}						
			return sb.toString();
		}
	

}