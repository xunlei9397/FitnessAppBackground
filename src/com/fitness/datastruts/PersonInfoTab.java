package com.fitness.datastruts;

import java.util.Date;

import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;


@Table("personinfotab")
public class PersonInfoTab {
		
	
	/*
	 *本实体类是用户个人信息的一些属性，注册时往此处存，基本信息从此处取
	 *用户id
	 *微信openid
	 *qq号
	 *手机号
	 *密码
	 *昵称
	 *性别
	 *地区:省-市-区
	 *个性签名
	 *生日
	 *教练履历
	 *工作单位
	 *我的评价:返回前台float类型的数字
	 *个人头像
	 */
	private int userId;
	
	@Name
	private String openid;
	
	private int qqNumber;
	
	//微信id
	private String weiXinId;
	
	//微博id
	private String weiBoId;
	
	private int phoneNumber;
	
	private String password;
	
	private String nickName;
	
	private String gender;
	
	private String area;
	
	//签名
	private String signature;
	
	private Date birthDay;
	
	//教练履历
	private String coachRecord;
	
	//工作单位
	private String orgnization;
	
	//评价
	private float evalute;

	private String personPicture;
	

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public String getCoachRecord() {
		return coachRecord;
	}

	public void setCoachRecord(String coachRecord) {
		this.coachRecord = coachRecord;
	}

	public String getOrgnization() {
		return orgnization;
	}

	public void setOrgnization(String orgnization) {
		this.orgnization = orgnization;
	}

	public float getEvalute() {
		return evalute;
	}

	public void setEvalute(float evalute) {
		this.evalute = evalute;
	}



	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPersonPicture() {
		return personPicture;
	}

	public void setPersonPicture(String personPicture) {
		this.personPicture = personPicture;
	}

	public int getQqNumber() {
		return qqNumber;
	}

	public void setQqNumber(int qqNumber) {
		this.qqNumber = qqNumber;
	}

	public String getWeiXinId() {
		return weiXinId;
	}

	public void setWeiXinId(String weiXinId) {
		this.weiXinId = weiXinId;
	}

	public String getWeiBoId() {
		return weiBoId;
	}

	public void setWeiBoId(String weiBoId) {
		this.weiBoId = weiBoId;
	}


	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	@Override
	public String toString() {
		return "PersonInfoTab [userId=" + userId + ", openid=" + openid + ", qqNumber=" + qqNumber + ", weiXinId="
				+ weiXinId + ", weiBoId=" + weiBoId + ", phoneNumber=" + phoneNumber + ", password=" + password
				+ ", nickName=" + nickName + ", gender=" + gender + ", area=" + area + ", signature=" + signature
				+ ", birthDay=" + birthDay + ", coachRecord=" + coachRecord + ", orgnization=" + orgnization
				+ ", evalute=" + evalute + ", personPicture=" + personPicture + "]";
	}
		
	
}
