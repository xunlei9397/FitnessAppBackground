package com.fitness.Main;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.fitness.datastruts.FitRoom;
import com.fitness.datastruts.Publish;
import com.fitness.server.publish.PublishService;

@IocBean
@At("/publishInterface")
public class PublishInterface {

	/*
	 * ��ɾ�Ĳ�
	 * �����¿γ�ʱ����ķ���
	 * �����޸�isReceived�ֶεķ���
	 * �����޸�YN�ֶεķ���
	 * �޸������ֶ�evalute�ķ���
	 * ��ѯevalute�ֶεĺ�
	 * */
	@Inject("refer:publishService")
	private PublishService publish;
	Logger logger = Logger.getLogger(PublishInterface.class.getName());
	
	@At("/addObjectInt")
	@Ok("json")
	public Boolean addObjectInt(@Param("objectid")String objectid ,@Param("openid")String openid ,
			@Param("wechatnumber")String wechatnumber,@Param("phonenumber")String phonenumber,
			@Param("object")String object,@Param("time")Date time,@Param("address")String address,
			@Param("fitroom")String  fitroom,@Param("price")int  price,@Param("remarks")String remarks) {
		Boolean b=false;
		try {	
			Publish pub=new Publish();
			pub.setObjectid(objectid);
			pub.setOpenid(openid);
			pub.setPhonenumber(phonenumber);
			pub.setWechatnumber(wechatnumber);
			object = new String(object.getBytes("ISO-8859-1"),"UTF-8");
			pub.setObject(object);
			pub.setTime(time);
			pub.setAddress(address);
			pub.setPrice(price);
			pub.setFitroom(fitroom);
			pub.setRemarks(remarks);
			publish.addObjectServer(pub);
			b=true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("addObjectInt"+e.getMessage());
			e.printStackTrace();
		}
		return b;
		
	}


	@At("/editYNInt")
	@Ok("json")
	public Boolean editYNInt(@Param("objectid")String objectid,@Param("YN") String YN) {
		Boolean b=false;
		try {
			publish.editYNServer(objectid, YN);
			b=true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("editYNInt"+e.getMessage());
			e.printStackTrace();
		}
		return b;
		
	}

	
	@At("/addMapInfoInt")
	@Ok("json")
	public Boolean addMapInfoInt(@Param("objectid")String objectid,@Param("pointX")double pointX,@Param("pointY") double pointY,@Param("mapClass") int mapClass) {
		Boolean b=false;
		try {
			publish.addMapInfoServer(objectid, pointX, pointY, mapClass);;
			b=true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("editEvaluteInt"+e.getMessage());
			e.printStackTrace();
		}
		return b;
		
	}
	
	
	@At("/getScoreInt")
	@Ok("json")
	public int getScoreInt(@Param("openid")String openid){
		String sum;
		sum=publish.getScoreServer(openid);
		return Integer.valueOf(sum);
	}
	
	@At("/addFitRoomInt")
	@Ok("json")
	public Boolean addFitRoomInt(@Param("openid")String openid,@Param("frontphone")String frontphone,
			@Param("businessHour")String businessHour,@Param("memberLevel")String memberLevel){
		Boolean b=false;
		try {
			FitRoom fit=new FitRoom();
			fit.setOpenid(openid);
			fit.setFrontphone(frontphone);
			fit.setBusinessHour(businessHour);
			fit.setMemberLevel(memberLevel);
			publish.addFitRoomServer(fit);
			b=true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("editEvaluteInt"+e.getMessage());
			e.printStackTrace();
		}		
		
		return b;
	}
	
	@At("/fitObjectInt")
	@Ok("json")
	public List<String> fitObjectInt() {
		List<String> list=new ArrayList<>();
		try {
			list=publish.fitObjectServer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("editEvaluteInt"+e.getMessage());
			e.printStackTrace();
		}
		return list;
		
	}
	
	
}
