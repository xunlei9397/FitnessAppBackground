package com.fitness.datastruts;

import org.nutz.dao.entity.annotation.Table;

//��¼�û�˵˵���漰����ͼƬ��Ƶ��ַ��
@Table("picVidAddress")
public class PicVidAddress {

	/*
	 * ΢���û���openid
	 * ����˵˵��ssid
	 * �û�ͼƬ��ַ��ÿһ��ͼƬ��¼һ����Ϣ
	 * �û���Ƶ��ַ��ÿһ����Ƶ��¼һ����Ϣ
	 * */
	
	private String openid;
	
	private String ssid;
	
	private String picAddress;
	
	private String vidAddress;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getSsid() {
		return ssid;
	}

	public void setSsid(String ssid) {
		this.ssid = ssid;
	}

	public String getPicAddress() {
		return picAddress;
	}

	public void setPicAddress(String picAddress) {
		this.picAddress = picAddress;
	}

	public String getVidAddress() {
		return vidAddress;
	}

	public void setVidAddress(String vidAddress) {
		this.vidAddress = vidAddress;
	}

	@Override
	public String toString() {
		return "PicVidAddress [openid=" + openid + ", ssid=" + ssid + ", picAddress=" + picAddress + ", vidAddress="
				+ vidAddress + "]";
	}
	
	
	
}
