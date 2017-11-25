package com.fitness.datastruts;

import java.util.Date;

import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("share")
public class Share {

	/*
	 * 此类是分享信息的数据结构,用户的评论表是CommentTab
	 * */
	
	/*
	 * 微信用户的openid
	 * 所发说说的ssid
	 * 说说时间time
	 * 文字消息Message
	 * 图片Picture:单独一个表存用户图片地址 PicVidAddress存储图片视频地址
	 * 视频Video：与图片同一表存视频存储地址
	 * 点赞数admireNum
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
