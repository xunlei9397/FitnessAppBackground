package com.fitness.datastruts;

import java.util.Date;

import org.nutz.dao.entity.annotation.Table;
@Table("Search")
public class Search {

	
	/*
	 * 此类是搜索方的数据结构
	 * */
	
	/*
	 * 搜索方的openid
	 * 要教的课程object
	 * 时间time
	 * 地点address
	 * 价格price
	 * 发布方电话pubphonenumber
	 * 发布方微信号pubwechatnumber 
	 * */
	
	private String openid;
	
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

	@Override
	public String toString() {
		return "Search [openid=" + openid + ", object=" + object + ", time=" + time + ", address=" + address
				+ ", price=" + price + ", pubphonenumber=" + pubphonenumber + ", pubwechatnumber=" + pubwechatnumber
				+ "]";
	}
	
	

}
