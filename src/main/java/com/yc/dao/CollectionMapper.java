package com.yc.dao;

import java.util.List;

import com.yc.po.CollectionPO;

public interface CollectionMapper {

	//添加收藏
	public boolean insertCollection(CollectionPO collentionPO);

	//移除收藏
	public boolean deleteCollection(List<String> tid);
	
	//查看收藏
	public List<CollectionPO> selectCollection(CollectionPO collentionPO);
	
	
}
