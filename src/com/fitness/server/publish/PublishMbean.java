package com.fitness.server.publish;

import java.util.List;

import com.fitness.datastruts.FitRoom;
import com.fitness.datastruts.Publish;

public interface PublishMbean {

	/*
	 * 增删改查
	 * 发布新课程时插入的方法 
	 * 单独修改isReceived字段的方法
	 * 单独修改YN字段的方法
	 * 修改评价字段evalute的方法
	 * */
	//发布课程召教练
		void addObjectServer(Publish pub);
		
		// 单独修改isReceived字段的方法
		Boolean editIsReceivedServer(String objectid,String Content);
		
		
		// 单独修改YN字段的方法
		void editYNServer(String objectid,String YN);
		
		
		
		//修改评价字段evalute的方法
		void editEvaluteServer(String objectid,int score);
	
		//添加经度、纬度、地图级别
		void addMapInfoServer(String objectid,double pointX,double pointY,int mapClass);
		
		String getScoreServer(String openid);
		
		void addFitRoomServer(FitRoom fitRoom);
		
		List<String> fitObjectServer();
	
}
