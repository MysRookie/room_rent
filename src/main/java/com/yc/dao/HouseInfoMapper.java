package com.yc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yc.po.HouseInfoPO;


public interface HouseInfoMapper {

	//查询房屋信息和房东信息
	public List<HouseInfoPO> selectHouseInfo(HouseInfoPO houseInfoPO);
	
	//查询房屋的价格，根据一个区间
	public List<HouseInfoPO> selectHouseInfoPrice(String pricemin,String pricemax);
	
	public List<HouseInfoPO> selecttitle(HouseInfoPO houseInfoPO);

	
	
	//插入房源的信息
	public boolean insertHouseInfo(HouseInfoPO houseInfoPO);
	
	//修改房屋的信息
	public boolean updateHouseInfo(HouseInfoPO houseInfoPO);
	
	//查询待审核的记录数 
    @Select("select count(*) from houseinfo where pushstatus='审核中' ")
    public Integer selectUnpassedNum();
    
    //删除房屋的信息
    public boolean deleteHouseInfo(List<String> hid);
    
    //批量审核房源信息(通过)
    public boolean batchUpdateHouseInfoPushstatus(@Param("hidList")List<Integer> hid,@Param("pushstatus")String pushstatus,@Param("account")String account);
    
}
