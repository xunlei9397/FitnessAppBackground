package com.fitness.server.search.dao;

import java.util.List;

import com.fitness.datastruts.FitRoom;

public interface SearchDaoIf {

	/*
	 * �����γ̣������ڵ�ͼ�Ϸ��ϵĵ� ����coordinate
	 * �����־���ײ������ֱ��ꡢ���顢�绰
	 * �����߽ӿΰ�ť  �ѿ�������Ҫ������ɱ����
	 * �����߽�������  �ѿ���
	 * */
	List<String> coordinate(String object);
	
	List<FitRoom> queryFitRoom(String openid); 
	
	
	
	
}
