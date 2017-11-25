package com.fitness.datastruts;

import java.util.List;

import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("fitroomlist")
public class FitRoomList {

	//需要前端开发者返回该健身房的地理坐标
	//需要输入者在输入前查看有没有这家健身房的名字，然后再行输入
	/*
	 * 省份 province
	 * 市city
	 * 区/县 county
	 * 健身房列表fitRoomName
	 * 健身房id
	 * */
	
	private String province;
	
	private String city;

	private String county;
	
	private String fitRoomName;
	
	private String addressRemarks;

	@Name
	private String fitRoomId;
	public String getFitRoomId() {
		return fitRoomId;
	}

	public void setFitRoomId(String fitRoomId) {
		this.fitRoomId = fitRoomId;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getFitRoomName() {
		return fitRoomName;
	}

	public void setFitRoomName(String fitRoomName) {
		this.fitRoomName = fitRoomName;
	}
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddressRemarks() {
		return addressRemarks;
	}

	public void setAddressRemarks(String addressRemarks) {
		this.addressRemarks = addressRemarks;
	}


	
	
}
