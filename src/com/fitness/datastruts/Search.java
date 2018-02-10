package com.fitness.datastruts;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;
@Table("search")
public class Search {

	
	/*
	 * 此类是搜索方的数据结构
	 * */
	
	/*
	 * 搜索方的openid
	 * 搜索方的昵称searchNickName
	 * 要教的课程object
	 * 要教的课程objectid
	 * 时间time
	 * 地点address
	 * 价格price
	 * 发布方电话pubphonenumber
	 * 发布方微信号pubwechatnumber 
	 * */
	
	private String openid;

	private String searchNickName;
	
	private String objectid;
	
	private String object;
	
	private Date time;
	
	private String address;
	
	private int price;
	
	private String pubphonenumber;
	
	private String pubwechatnumber;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getPubphonenumber() {
		return pubphonenumber;
	}

	public void setPubphonenumber(String pubphonenumber) {
		this.pubphonenumber = pubphonenumber;
	}

	public String getPubwechatnumber() {
		return pubwechatnumber;
	}

	public void setPubwechatnumber(String pubwechatnumber) {
		this.pubwechatnumber = pubwechatnumber;
	}

	public String getObjectid() {
		return objectid;
	}

	public void setObjectid(String objectid) {
		this.objectid = objectid;
	}

	public String getSearchNickName() {
		return searchNickName;
	}

	public void setSearchNickName(String searchNickName) {
		this.searchNickName = searchNickName;
	}

	
	

}
