package com.fitness.server.publish;

import java.util.List;

import com.fitness.datastruts.FitRoom;
import com.fitness.datastruts.FitRoomList;
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
		
		List<String> addFitObjectServer(List<String> object);
		
		List<String> deleteFitObjectServer(String object);
		
		
		/*
		 *添加省份-区/县-健身房名称
		 * 删除健身房名称
		 * 修改健身房名称
		 * 查找省份-区/县的健身房列表
		 * */
		void addFitRoomListServer(FitRoomList fitroom);
		
		void deleteFitRoomListServer(String fitroomId);
		
		void updateFitRoomListServer(String fitroomId,String fitroomName);
		
		List<FitRoomList> searchFitRoomListServer(String province,String city,String county);
		
		List<Publish> searchPublishList(String openid);
}
