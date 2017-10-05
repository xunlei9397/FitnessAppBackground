package com.fitness.datastruts;

import java.util.Date;

import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("share")
public class Share {

	/*
	 * �����Ƿ�����Ϣ�����ݽṹ,�û������۱���CommentTab
	 * */
	
	/*
	 * ΢���û���openid
	 * ����˵˵��ssid
	 * ˵˵ʱ��time
	 * ������ϢMessage
	 * ͼƬPicture:����һ������û�ͼƬ��ַ PicVidAddress�洢ͼƬ��Ƶ��ַ
	 * ��ƵVideo����ͼƬͬһ�����Ƶ�洢��ַ
	 * ������admireNum
	 * */
	private String openid;
	
	@Name
	private String ssid;
	
	private Date time;
	
	private String message;
	
	private String picAddress;
	
	private String videoAddress;

	private String admireNum;
	
	
	
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPicAddress() {
		return picAddress;
	}

	public void setPicAddress(String picAddress) {
		this.picAddress = picAddress;
	}

	public String getVideoAddress() {
		return videoAddress;
	}

	public void setVideoAddress(String videoAddress) {
		this.videoAddress = videoAddress;
	}
 
	
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	
	
	public String getAdmireNum() {
		return admireNum;
	}

	public void setAdmireNum(String admireNum) {
		this.admireNum = admireNum;
	}

	@Override
	public String toString() {
		return "Share [openid=" + openid + ", ssid=" + ssid + ", time=" + time + ", message=" + message
				+ ", picAddress=" + picAddress + ", videoAddress=" + videoAddress + ", admireNum=" + admireNum + "]";
	}
	
	
}
