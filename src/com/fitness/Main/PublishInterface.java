package com.fitness.Main;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


import org.apache.log4j.Logger;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.fitness.datastruts.FitRoom;
import com.fitness.datastruts.FitRoomList;
import com.fitness.datastruts.Publish;
import com.fitness.server.publish.PublishService;

@IocBean
@At("/publishInterface")
public class PublishInterface {

	@Inject("refer:publishService")
	private PublishService publish;
	
	Logger logger = Logger.getLogger(PublishInterface.class.getName());

//查出接课者的昵称、电话号码
//	SELECT nickname,phonenumber FROM personinfotab where openid =
//	( select openid from search where objectid in( select objectid from publish where openid='890uhhhbn2'));

	
	@At("/addObjectInt")
	@Ok("json")
	public Boolean addObjectInt(@Param("objectid") String objectid, @Param("openid") String openid,
			@Param("wechatnumber") String wechatnumber, @Param("phonenumber") String phonenumber,
			@Param("object") String object, @Param("time") Date time, @Param("province") String province,
			@Param("county") String county,@Param("city") String city,@Param("fitroom") String fitroom, 
			@Param("price")int  price, @Param("remarks") String remarks,@Param("detailAddress")String detailAddress,
			@Param("payway")String payway ) {
		Boolean b = false;
		Date now=new Date();
		now.getTime();
		try {
			Publish pub = new Publish();
			pub.setObjectid(openid+""+now.getTime());
			pub.setOpenid(openid);
			pub.setPhonenumber(phonenumber);
			pub.setWechatnumber(wechatnumber);
//			object = new String(object.getBytes("ISO-8859-1"), "UTF-8");
//			province=new String(province.getBytes("ISO-8859-1"),"UTF-8");
//			city=new String(city.getBytes("ISO-8859-1"),"UTF-8");
//			county=new String(county.getBytes("ISO-8859-1"),"UTF-8");
//			fitroom=new String(fitroom.getBytes("ISO-8859-1"), "UTF-8");
//			remarks=new String(remarks.getBytes("ISO-8859-1"), "UTF-8");
//			detailAddress=new String(detailAddress.getBytes("ISO-8859-1"), "UTF-8");
//			payway=new String(payway.getBytes("ISO-8859-1"), "UTF-8");
			
			
			
			pub.setObject(object);
			pub.setTime(time);
			pub.setProvince(province);
			pub.setCity(city);
			pub.setCounty(county);
			pub.setPrice(price);
			pub.setPayway(payway);
			pub.setFitroom(fitroom);
			pub.setDetailAddress(detailAddress);
			pub.setRemarks(remarks);
			publish.addObjectServer(pub);
			b = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("addObjectInt" + e.getMessage());
			e.printStackTrace();
			return b;
		}
		return b;

	}

	@At("/editYNInt")
	@Ok("json")
	public Boolean editYNInt(@Param("objectid") String objectid, @Param("YN") String YN) {
		Boolean b = false;
		try {
			publish.editYNServer(objectid, YN);
			b = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("editYNInt" + e.getMessage());
			e.printStackTrace();
		}
		return b;

	}

	@At("/addMapInfoInt")
	@Ok("json")
	public Boolean addMapInfoInt(@Param("objectid") String objectid, @Param("pointX") double pointX,
			@Param("pointY") double pointY, @Param("mapClass") int mapClass) {
		Boolean b = false;
		try {
			publish.addMapInfoServer(objectid, pointX, pointY, mapClass);
			;
			b = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("editEvaluteInt" + e.getMessage());
			e.printStackTrace();
		}
		return b;

	}

	@At("/getScoreInt")
	@Ok("json")
	public int getScoreInt(@Param("openid") String openid) {
		String sum;
		sum = publish.getScoreServer(openid);
		return Integer.valueOf(sum);
	}

	@At("/addFitRoomInt")
	@Ok("json")
	public Boolean addFitRoomInt(@Param("fitRoomName") String fitRoomName, @Param("frontphone") String frontphone,
			@Param("businessHour") String businessHour, @Param("memberLevel") String memberLevel) {
		Boolean b = false;
		try {
			FitRoom fit = new FitRoom();
			fit.setFitRoomName(fitRoomName);
			fit.setFrontphone(frontphone);
			fit.setBusinessHour(businessHour);
			fit.setMemberLevel(memberLevel);
			publish.addFitRoomServer(fit);
			b = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("editEvaluteInt" + e.getMessage());
			e.printStackTrace();
		}

		return b;
	}

	
	@At("/fitObjectInt")
	@Ok("json")
	public List<String> fitObjectInt() {
		List<String> list =new ArrayList<>();
		try {
			list = publish.fitObjectServer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("editEvaluteInt" + e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	
	@At("/addFitObjectInt")
	@Ok("json")
	public List<String> addFitObjectInt(@Param("json") String[] json) {
		// 传入的数据应该是["1","2","3","4"]数组形式
		List<String> list = new LinkedList<>();

		try {
			if (json.length == 0) {
				list.add("您传入的数组长度为空");
			} else {
				for (int i = 0; i < json.length; i++) {
					list.add(json[i]);
				}
				list = publish.addFitObjectServer(list);
			}
		} catch (Exception e) {
			logger.error("addFitObjectInt" + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	@At("/deleteFitObjectInt")
	@Ok("json")
	public List<String> deleteFitObjectInt(@Param("object") String object) {
		// 传入的数据应该是["1","2","3","4"]数组形式
		List<String> list = new LinkedList<>();
		try {			
				list = publish.deleteFitObjectServer(object);
			
		} catch (Exception e) {
			logger.error("addFitObjectInt" + e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	// ==================以下为增加健身房列表信息=======
	@At("/addFitRoomListInt")
	@Ok("json")
	public Boolean addFitRoomListInt(@Param("province") String province,@Param("city") String city, @Param("county") String county,
			@Param("addressRemarks") String addressRemarks,	@Param("fitRoomName") String fitRoomName,@Param("frontphone") String frontphone,
			@Param("businessHour") String businessHour, @Param("memberLevel") String memberLevel) {
		Boolean b=false;
		FitRoomList fitroom = new FitRoomList();
		try {
			fitroom.setProvince(province);
			fitroom.setCity(city);
			fitroom.setCounty(county);
			fitroom.setFitRoomName(fitRoomName);
			fitroom.setFitRoomId(String.valueOf(Math.random()+fitRoomName.hashCode()));
			fitroom.setAddressRemarks(addressRemarks);
			publish.addFitRoomListServer(fitroom);
			addFitRoomInt(fitRoomName,frontphone,businessHour,memberLevel);
			b=true;
		} catch (Exception e) {
			logger.error("addFitRoomList" + e.getMessage());
			e.printStackTrace();
		}
		return b;
	}

	@At("/deleteFitRoomListInt")
	@Ok("json")
	public Boolean deleteFitRoomListInt(@Param("fitroomId")String fitroomId) {
		Boolean b=false;
		try {
			publish.deleteFitRoomListServer(fitroomId);
			b=true;
		} catch (Exception e) {
			logger.error("deleteFitRoomList" + e.getMessage());
			e.printStackTrace();
		}
		return b;
	}

	@At("/updateFitRoomListInt")
	@Ok("json")
	public Boolean updateFitRoomListInt(@Param("fitroomId")String fitroomId, @Param("fitroomName")String fitroomName) {
		Boolean b=false;
		try {
			publish.updateFitRoomListServer(fitroomId, fitroomName);
			b=true;
		} catch (Exception e) {
			logger.error("updateFitRoomList" + e.getMessage());
			e.printStackTrace();
		}
		return b;
	}

	@At("/searchFitRoomListInt")
	@Ok("json")
	public List<FitRoomList> searchFitRoomListInt(@Param("province")String province, @Param("city")String city,@Param("county")String county,
			@Param("payway")String payway, @Param("time") Date time,@Param("price")int  price) {
		List<FitRoomList> list=new ArrayList<>();
		try {
			
			list=publish.searchFitRoomListServer(province,city,county);
			
		} catch (Exception e) {
			logger.error("searchFitRoomList" + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	
	@At("/publishList")
	@Ok("json")
	public List<Publish> publishList(@Param("openid")String openid){
		
		
		return publish.searchPublishList(openid);
	}
	
	
	@At("/deletePublish")
	@Ok("json")
	public Boolean deletePublish(@Param("objectid") String objectid) {
		Boolean b = false;
		try {
			publish.deletePub(objectid);
			b = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("editYNInt" + e.getMessage());
			e.printStackTrace();
		}
		return b;

	}
	
	
}
