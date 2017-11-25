package com.fitness.server.publish.dao;

import java.util.List;

import com.fitness.datastruts.FitRoom;
import com.fitness.datastruts.FitRoomList;
import com.fitness.datastruts.Publish;

public interface PublishDaoIf {

	/*
	 * ��ɾ�Ĳ�
	 * �����¿γ�ʱ����ķ��� 
	 * �����޸�isReceived�ֶεķ���
	 * �����޸�YN�ֶεķ���
	 * �޸������ֶ�evalute�ķ���
	 * ��Ӿ��ȡ�γ�ȡ���ͼ����
	 * ���û�evalute�ֶε���ֵ��������������
	 * ��������д������Ϣ
	 * Ϊ�γ������б��ṩ�γ�����
	 * д��γ����Ƶ����ݿ�
	 * */
	
	
	
	//�����γ��ٽ���
	void addObject(Publish pub);
	
	// �����޸�isReceived�ֶεķ���
	Boolean editIsReceived(String objectid,String Content);
	
	
	// �����޸�YN�ֶεķ���
	void editYN(String objectid,String YN);
	
	
	
	//�޸������ֶ�evalute�ķ���
	void editEvalute(String objectid,int score);
	
	
	//��Ӿ��ȡ�γ�ȡ���ͼ����
	void addMapInfo(String objectid,double pointX,double pointY,int mapClass);
	
	String getScore(String openid);
	
	void addFitRoomInfo(FitRoom fitRoom);
	
	List<String> fitObject();
	
	List<String> addFitObject(List<String> object);
	
	List<String> deleteFitObject(String[] object);
	
	/*
	 *���ʡ��-��/��-��������
	 * ɾ����������
	 * �޸Ľ�������
	 * ����ʡ��-��/�صĽ����б�
	 * */
	void addFitRoomList(FitRoomList fitroom);
	
	void deleteFitRoomList(String fitroomId);
	
	void updateFitRoomList(String fitroomId,String fitroomName);
	
	List<FitRoomList> searchFitRoomList(String province,String city,String county);
	
}
