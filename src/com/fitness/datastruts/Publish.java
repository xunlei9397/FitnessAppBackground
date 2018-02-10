package com.fitness.datastruts;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Many;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;


@Table("Publish")
public class Publish {
/*
 * 此类是发布方的数据结构
 * */
	
	/*@author("liuhaijian")
	 * 此刻发布课程的objectid：openid_yyyymmddhh_24miss格式的时间组成
	 * 发布方的openid
	 * 发布者昵称providername
	 * 微信号 wechatnumber,让使用者自己填，可不填
	 * 电话号phonenumber，让使用者自己填，一定要填
	 * 课程object
	 * 时间time
	 * 地点address
	 * 省province
	 * 市city
	 * 县/区 county
	 * 价格price
	 * 付款方式payway
	 * 健身房名称fitroom
	 * 健身房详细地址detailAddress
	 * 备注remarks
	 * 健身房的地图地址 纬度ponitX
	 * 健身房的地图地址 经度ponitY
	 * 地图级别 mapClass
	 * ===================
	 * 课程是否被接收isReceived
	 * 任务是否被接收YN
	 * 教练给的评价evalute:分为差评，中评，好评，积分制。
	 * */
	@Name
	private String objectid;
	@Column
	private String openid;
	
	private String providername;
	@Column
	private String wechatnumber;
	@Column
	private String phonenumber;
	@Column
	private String object;
	@Column
	private Date time;
	
	@Column
	private String province;
	@Column
	private String county;
	@Column
	private String city;
	@Column
	private int price;
	@Column
	private String payway;
	@Column
	private String fitroom;
	@Column
	private String detailAddress;
	@Column
	private String remarks;
	@Column
	private String isReceived;
	@Column
	private String YN;
	@Column
	private int evalute;
	@Column
	private double pointX;
	@Column
	private double pointY;
	@Column
	private int mapClass;
	@Column
	private Date  vertime=new Date();
	
	private boolean disabled=false;
	
	
	private boolean hidden=false;
	
	

	@Many(target = Search.class, field = "objectid")
	private List<Search> receiver;

	public String getObjectid() {
		return objectid;
	}

	public void setObjectid(String objectid) {
		this.objectid = objectid;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getWechatnumber() {
		return wechatnumber;
	}

	public void setWechatnumber(String wechatnumber) {
		this.wechatnumber = wechatnumber;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	

	public String getIsReceived() {
		return isReceived;
	}

	public void setIsReceived(String isReceived) {
		this.isReceived = isReceived;
	}

	public String getYN() {
		return YN;
	}

	public void setYN(String yN) {
		YN = yN;
	}

	public int getEvalute() {
		return evalute;
	}

	public void setEvalute(int evalute) {
		this.evalute = evalute;
	}

	public double getPointX() {
		return pointX;
	}

	public void setPointX(double pointX) {
		this.pointX = pointX;
	}

	public double getPointY() {
		return pointY;
	}

	public void setPointY(double pointY) {
		this.pointY = pointY;
	}

	public int getMapClass() {
		return mapClass;
	}

	public void setMapClass(int mapClass) {
		this.mapClass = mapClass;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	
	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	
	public String getFitroom() {
		return fitroom;
	}

	public void setFitroom(String fitroom) {
		this.fitroom = fitroom;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getPayway() {
		return payway;
	}

	public void setPayway(String payway) {
		this.payway = payway;
	}

	public List<Search> getReceiver() {
		return receiver;
	}

	public void setReceiver(List<Search> receiver) {
		this.receiver = receiver;
	}

	public String getProvidername() {
		return providername;
	}

	public void setProvidername(String providername) {
		this.providername = providername;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public Date getVertime() {
		return vertime;
	}

	public void setVertime(Date vertime) {
		this.vertime = vertime;
	}
	
	
	
	
	
}
