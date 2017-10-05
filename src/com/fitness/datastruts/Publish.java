package com.fitness.datastruts;

import java.util.Date;

import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;


@Table("Publish")
public class Publish {
/*
 * �����Ƿ����������ݽṹ
 * */
	
	/*@author("liuhaijian")
	 * �˿̷����γ̵�objectid��openid_yyyymmddhh_24miss��ʽ��ʱ�����
	 * ��������openid
	 * ΢�ź� wechatnumber,��ʹ�����Լ���ɲ���
	 * �绰��phonenumber����ʹ�����Լ��һ��Ҫ��
	 * �γ�object
	 * ʱ��time
	 * �ص�address
	 * �۸�price
	 * ��������fitroom
	 * ��עremarks
	 * �����ĵ�ͼ��ַ γ��ponitX
	 * �����ĵ�ͼ��ַ ����ponitY
	 * ��ͼ���� mapClass
	 * ===================
	 * �γ��Ƿ񱻽���isReceived
	 * �����Ƿ񱻽���YN
	 * ������������evalute:��Ϊ�����������������������ơ�
	 * */
	
	@Name
	private String objectid;
	
	private String openid;
	
	private String wechatnumber;
	
	private String phonenumber;
	
	private String object;
	
	private Date time;
	
	private String address;
	
	private int price;
	
	private String fitroom;
	
	private String remarks;
	
	private String isReceived;
	
	private String YN;
	
	private int evalute;
	
	private double pointX;
	
	private double pointY;
	
	private int mapClass;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int  getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
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

	@Override
	public String toString() {
		return "Publish [objectid=" + objectid + ", openid=" + openid + ", wechatnumber=" + wechatnumber
				+ ", phonenumber=" + phonenumber + ", object=" + object + ", time=" + time + ", address=" + address
				+ ", price=" + price + ", fitroom=" + fitroom + ", remarks=" + remarks + ", isReceived=" + isReceived
				+ ", YN=" + YN + ", evalute=" + evalute + ", pointX=" + pointX + ", pointY=" + pointY + ", mapClass="
				+ mapClass + "]";
	}
	
	
	
}
