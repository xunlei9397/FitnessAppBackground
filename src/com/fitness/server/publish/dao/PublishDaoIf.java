package com.fitness.server.publish.dao;

import java.util.List;

import com.fitness.datastruts.FitRoom;
import com.fitness.datastruts.FitRoomList;
import com.fitness.datastruts.Publish;

public interface PublishDaoIf {

	/*
	 * 增删改查
	 * 发布新课程时插入的方法 
	 * 单独修改isReceived字段的方法
	 * 单独修改YN字段的方法
	 * 修改评价字段evalute的方法
	 * 添加经度、纬度、地图级别
	 * 将用户evalute字段的数值加起来，并返回
	 * 发布者填写健身房信息
	 * 为课程下拉列表提供课程名称
	 * 写入课程名称到数据库
	 * 查询发布者发布课程及接课人员
	 * 删除发布的课程
	 * */
	
	
	
	//发布课程召教练
	void addObject(Publish pub);
	
	// 单独修改isReceived字段的方法
	Boolean editIsReceived(String objectid,String Content);
	
	
	// 单独修改YN字段的方法
	void editYN(String objectid,String YN);
	
	
	
	//修改评价字段evalute的方法
	void editEvalute(String objectid,int score);
	
	
	//添加经度、纬度、地图级别
	void addMapInfo(String objectid,double pointX,double pointY,int mapClass);
	
	String getScore(String openid);
	
	void addFitRoomInfo(FitRoom fitRoom);
	
	List<String> fitObject();
	
	List<String> addFitObject(List<String> object);
	
	List<String> deleteFitObject(String object);
	
	/*
	 *添加省份-区/县-健身房名称
	 * 删除健身房名称
	 * 修改健身房名称
	 * 查找省份-区/县的健身房列表
	 * */
	void addFitRoomList(FitRoomList fitroom);
	
	void deleteFitRoomList(String fitroomId);
	
	void updateFitRoomList(String fitroomId,String fitroomName);
	
	List<FitRoomList> searchFitRoomList(String province,String city,String county);
	
	List<Publish> publishList(String openid);
	
	void deletePub(String objectid);
	
}
