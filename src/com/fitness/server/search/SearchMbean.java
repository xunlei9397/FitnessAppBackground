package com.fitness.server.search;

import java.util.List;
import java.util.Map;

import com.fitness.datastruts.FitRoom;
import com.fitness.datastruts.Publish;

public interface SearchMbean {

	
	/*
	 * 搜索课程，呈现在地图上符合的点 坐标coordinate
	 * 点击标志，底部栏呈现本店、详情、电话
	 * 
	 * 搜索者接课按钮  已开发，还要开发秒杀程序
	 * 搜索者进行评价  已开发
	 * 搜索课程（详细条件）
	 * */
	List<String> coordinateServer(String object);
	
	List<FitRoom> queryFitRoomServer(String openid); 
	
	List<Publish> searchResultServer(Map map);
	
}
