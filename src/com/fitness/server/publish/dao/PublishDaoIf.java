package com.fitness.server.publish.dao;

import java.util.List;

import com.fitness.datastruts.FitRoom;
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
}
