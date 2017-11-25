package com.fitness.server.publish;

import java.util.List;

import com.fitness.datastruts.FitRoom;
import com.fitness.datastruts.FitRoomList;
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
	
		//��Ӿ��ȡ�γ�ȡ���ͼ����
		void addMapInfoServer(String objectid,double pointX,double pointY,int mapClass);
		
		String getScoreServer(String openid);
		
		void addFitRoomServer(FitRoom fitRoom);
		
		List<String> fitObjectServer();
		
		List<String> addFitObjectServer(List<String> object);
		
		List<String> deleteFitObjectServer(String[] object);
		
		
		/*
		 *���ʡ��-��/��-��������
		 * ɾ����������
		 * �޸Ľ�������
		 * ����ʡ��-��/�صĽ����б�
		 * */
		void addFitRoomListServer(FitRoomList fitroom);
		
		void deleteFitRoomListServer(String fitroomId);
		
		void updateFitRoomListServer(String fitroomId,String fitroomName);
		
		List<FitRoomList> searchFitRoomListServer(String province,String city,String county);
	
}
