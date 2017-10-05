package com.fitness.server.share.dao;

import java.util.List;

import com.fitness.datastruts.CommentTab;
import com.fitness.datastruts.PicVidAddress;
import com.fitness.datastruts.Share;

public interface ShareInfoDaoIf {

	/*
	 * 用户发说说接口
	 * 用户评论接口
	 * 查找用户分享方法,一同查找分享下的评论
	 * 
	 * 接口处设计文件上传方法(升级picAddress字段的方法，升级vidAddress字段的方法)
	 * 需要传入页大小并且返回页数
	 * 查找图片/视频地址字段的方法
	 * 用户点赞接口
	 * */
	
	void share(Share share);
	
	void comment(CommentTab comment);
	
	List<Share> searchShare(int pageNumber,int pageSize);
	
	List<CommentTab> searchComment(String ssid);
	
	int pageNumber(int pageSize);
	
	int addAdmireNum(String ssid);
	
	void updateAddress(PicVidAddress pvAddress,Boolean isPic);
	
	List<String> searchAddress(String ssid);
	
	
}
