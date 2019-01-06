package com.yc.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.biz.CommentBiz;
import com.yc.dao.CommentMapper;
import com.yc.po.CommentPO;
@Service("commentBiz")
public class CommentImpl implements CommentBiz {
	@Autowired
	CommentMapper commentMapper;
	
	@Override
	public boolean insertComment(CommentPO comPO) {
		return commentMapper.insertComment(comPO);
	}


	@Override
	public List<CommentPO> selectComment(CommentPO comPO) {
		return commentMapper.selectComment(comPO);
	}


	@Override
	public boolean deleteCommentById(List<String> cid) {
		// TODO Auto-generated method stub
		return commentMapper.deleteCommentById(cid);
	}

}
