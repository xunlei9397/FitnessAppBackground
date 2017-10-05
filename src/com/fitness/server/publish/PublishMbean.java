package com.fitness.server.publish;

import com.fitness.datastruts.FitRoom;
import com.fitness.datastruts.Publish;

public interface PublishMbean {

	/*
	 * ��ɾ�Ĳ�
	 * �����¿γ�ʱ����ķ��� 
	 * �����޸�isReceived�ֶεķ���
	 * �����޸�YN�ֶεķ���
	 * �޸������ֶ�evalute�ķ���
	 * */
	//�����γ��ٽ���
		void addObjectServer(Publish pub);
		
		// �����޸�isReceived�ֶεķ���
		Boolean editIsReceivedServer(String objectid,String Content);
		
		
		// �����޸�YN�ֶεķ���
		void editYNServer(String objectid,String YN);
		
		
		
		//�޸������ֶ�evalute�ķ���
		void editEvaluteServer(String objectid,int score);
	
		//���Ӿ��ȡ�γ�ȡ���ͼ����
		void addMapInfoServer(String objectid,double pointX,double pointY,int mapClass);
		
		String getScoreServer(String openid);
		
		void addFitRoomServer(FitRoom fitRoom);
	
	
}