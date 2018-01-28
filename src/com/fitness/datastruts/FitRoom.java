package com.fitness.datastruts;

import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("fitroom")
public class FitRoom {

	/*
	 * 健身房名称
	 * 前台电话 frontphone
	 * 营业时间businessHour
	 * 会员层次memberLevel
	 * */
	@Name
	private String fitRoomName;
	
	private String frontphone;
	
	private String businessHour;
	
	private String memberLevel;


	public String getFitRoomName() {
		return fitRoomName;
	}

	public void setFitRoomName(String fitRoomName) {
		this.fitRoomName = fitRoomName;
	}


	public String getFrontphone() {
		return frontphone;
	}

	public void setFrontphone(String frontphone) {
		this.frontphone = frontphone;
	}

	public String getBusinessHour() {
		return businessHour;
	}

	public void setBusinessHour(String businessHour) {
		this.businessHour = businessHour;
	}

	public String getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
	}


	
	
}
