package com.fitness.datastruts;

import java.util.Date;

import org.nutz.dao.entity.annotation.Table;

@Table("TranTimeTable")
public class TranTimeTable {

	/*教练课表
	 * 记录内容教练从周一到周日的课程
	 *主键为教练的userId 
	 *其中周一到周日字段下记录教练的上课科目
	 *startTime、endTime 记录上课开始结束时间
	*/
	
	 private int userId;
	 
	 private String startTime;
	 
	 private String endTime;
	 
	 private String Mon;
	 
	 private String Tue;
	 
	 private String Wed;
	 
	 private String Thu;
	 
	 private String  Fri;
	 
	 private String Sat;
	 
	 private String Sun;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}



	public String getMon() {
		return Mon;
	}

	public void setMon(String mon) {
		Mon = mon;
	}

	public String getTue() {
		return Tue;
	}

	public void setTue(String tue) {
		Tue = tue;
	}

	public String getWed() {
		return Wed;
	}

	public void setWed(String wed) {
		Wed = wed;
	}

	public String getThu() {
		return Thu;
	}

	public void setThu(String thu) {
		Thu = thu;
	}

	public String getFri() {
		return Fri;
	}

	public void setFri(String fri) {
		Fri = fri;
	}

	public String getSat() {
		return Sat;
	}

	public void setSat(String sat) {
		Sat = sat;
	}

	public String getSun() {
		return Sun;
	}

	public void setSun(String sun) {
		Sun = sun;
	}


	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "TranTimeTable [userId=" + userId + ", startTime=" + startTime + ", endTime=" + endTime + ", Mon=" + Mon
				+ ", Tue=" + Tue + ", Wed=" + Wed + ", Thu=" + Thu + ", Fri=" + Fri + ", Sat=" + Sat + ", Sun=" + Sun
				+ "]";
	}

	
}
