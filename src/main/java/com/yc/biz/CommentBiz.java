package com.yc.biz;

import java.util.List;

import com.yc.po.CommentPO;

public interface CommentBiz {
	// 插入评论
	public boolean insertComment(CommentPO comPO);

	// 根据id删除评论
	public boolean deleteCommentById(List<String> cid);

	// 查询评论 根据用户 根据房屋
	public List<CommentPO> selectComment(CommentPO comPO);

}
