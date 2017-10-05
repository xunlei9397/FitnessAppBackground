package com.fitness.datastruts;

import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("fitroom")
public class FitRoom {

	/*
	 * ������openid
	 * ǰ̨�绰 frontphone
	 * Ӫҵʱ��businessHour
	 * ��Ա���memberLevel
	 * */
	@Name
	private String openid;
	
	private String frontphone;
	
	private String businessHour;
	
	private String memberLevel;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
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

	@Override
	public String toString() {
		return "FitRoom [openid=" + openid + ", frontphone=" + frontphone + ", businessHour=" + businessHour
				+ ", memberLevel=" + memberLevel + "]";
	}
	
	
}
