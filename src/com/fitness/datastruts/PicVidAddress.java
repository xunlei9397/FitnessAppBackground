package com.fitness.datastruts;

import org.nutz.dao.entity.annotation.Table;

//记录用户说说中涉及到的图片视频地址表
@Table("picVidAddress")
public class PicVidAddress {

	/*
	 * 微信用户的openid
	 * 所发说说的ssid
	 * 用户图片地址，每一张图片记录一条信息
	 * 用户视频地址，每一个视频记录一条信息
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
