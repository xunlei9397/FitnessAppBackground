package com.fitness.server.search;

import java.util.List;

import com.fitness.datastruts.FitRoom;

public interface SearchMbean {

	
	/*
	 * 搜索课程，呈现在地图上符合的点 坐标coordinate
	 * 点击标志，底部栏呈现本店、详情、电话
	 * 
	 * 搜索者接课按钮  已开发，还要开发秒杀程序
	 * 搜索者进行评价  已开发
	 * */
	List<String> coordinateServer(String object);
	
	List<FitRoom> queryFitRoomServer(String openid); 
	
	
	
}
