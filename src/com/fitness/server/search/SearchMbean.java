package com.fitness.server.search;

import java.util.List;

import com.fitness.datastruts.FitRoom;

public interface SearchMbean {

	
	/*
	 * �����γ̣������ڵ�ͼ�Ϸ��ϵĵ� ����coordinate
	 * �����־���ײ������ֱ��ꡢ���顢�绰
	 * 
	 * �����߽ӿΰ�ť  �ѿ�������Ҫ������ɱ����
	 * �����߽�������  �ѿ���
	 * */
	List<String> coordinateServer(String object);
	
	List<FitRoom> queryFitRoomServer(String openid); 
	
	
	
}
