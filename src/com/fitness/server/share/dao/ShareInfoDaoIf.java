package com.fitness.server.share.dao;

import java.util.List;

import com.fitness.datastruts.CommentTab;
import com.fitness.datastruts.PicVidAddress;
import com.fitness.datastruts.Share;

public interface ShareInfoDaoIf {

	/*
	 * �û���˵˵�ӿ�
	 * �û����۽ӿ�
	 * �����û�������,һͬ���ҷ����µ�����
	 * 
	 * �ӿڴ�����ļ��ϴ�����(����picAddress�ֶεķ���������vidAddress�ֶεķ���)
	 * ��Ҫ����ҳ��С���ҷ���ҳ��
	 * ����ͼƬ/��Ƶ��ַ�ֶεķ���
	 * �û����޽ӿ�
	 * */
	
	void share(Share share);
	
	void comment(CommentTab comment);
	
	List<Share> searchShare(int pageNumber,int pageSize);
	
	List<CommentTab> searchComment(String ssid);
	
	int pageNumber(int pageSize);
	
	int addAdmireNum(String ssid);
	
	void updateAddress(PicVidAddress pvAddress,Boolean isPic);
	
	List<String> searchAddress(String ssid);
	
	
}
