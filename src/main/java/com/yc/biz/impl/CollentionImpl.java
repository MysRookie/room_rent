package com.yc.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yc.biz.CollectionBiz;
import com.yc.dao.CollectionMapper;
import com.yc.po.CollectionPO;

@Repository("collentionBiz")
public class CollentionImpl implements CollectionBiz {
	
	@Autowired
	CollectionMapper collentionMapper;
	
	public boolean insertCollection(CollectionPO collentionPO){
		return collentionMapper.insertCollection(collentionPO);
	}

	@Override
	public List<CollectionPO> selectCollection(CollectionPO collentionPO) {
		return collentionMapper.selectCollection(collentionPO);
	}

	@Override
	public boolean deleteCollection(List<String> tid) {
		// TODO Auto-generated method stub
		return collentionMapper.deleteCollection(tid);
	}

}
