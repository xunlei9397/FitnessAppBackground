package com.fitness.datastruts;

import java.util.Date;

import org.nutz.dao.entity.annotation.Table;


@Table("personinfotab")
public class PersonInfoTab {
		
	
	/*
	 *��ʵ�������û�������Ϣ��һЩ���ԣ�ע��ʱ���˴��棬������Ϣ�Ӵ˴�ȡ
	 *�û�id
	 *΢��openid
	 *qq��
	 *�ֻ���
	 *����
	 *�ǳ�
	 *�Ա�
	 *����:ʡ-��-��
	 *����ǩ��
	 *����
	 *��������
	 *������λ
	 *�ҵ�����:����ǰ̨float���͵�����
	 *����ͷ��
	 */
	private int userId;
	
	private String openid;
	
	private int qqNumber;
	
	//΢��id
	private String weiXinId;
	
	//΢��id
	private String weiBoId;
	
	private int phoneNumber;
	
	private String password;
	
	private String nickName;
	
	private String gender;
	
	private String area;
	
	//ǩ��
	private String signature;
	
	private Date birthDay;
	
	//��������
	private String coachRecord;
	
	//������λ
	private String orgnization;
	
	//����
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
