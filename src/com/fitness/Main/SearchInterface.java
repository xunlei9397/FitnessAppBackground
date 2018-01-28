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
import com.fitness.server.search.SearchServer;

@IocBean
@At("/searchInterface")
public class SearchInterface {

	@Inject("refer:publishService")
	private PublishService publish;	
	
	@Inject("refer:searchServer")
	private SearchServer searchServer;
	
	Logger logger = Logger.getLogger(SearchInterface.class.getName());
	
	@At("/editIsReceivedInt")
	@Ok("json")
	public String editIsReceivedInt(@Param("objectid")String objectid,@Param("content") String Content) {
		String isReceived = "课程接受出错，请重新点击接受";
		try {
			if(publish.editIsReceivedServer(objectid, Content)){
				isReceived="课程已正常接收";
			}else{
				isReceived="课程已被他人接收";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("editIsReceivedInt:"+e.getMessage());
			e.printStackTrace();
		}
		return isReceived;	
	}
	
	
	@At("/editEvaluteInt")
	@Ok("json")
	public Boolean editEvaluteInt(@Param("objectid")String objectid,@Param("score") int score) {
		Boolean b=false;
		try {
			publish.editEvaluteServer(objectid,score);
			b=true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("editEvaluteInt:"+e.getMessage());
			e.printStackTrace();
		}
		return b;
		
	}
	
	
	@At("/coordinateInt")
	@Ok("json")
   public List<String> coordinateInt(@Param("object")String object){
		
	   List<String> coordinate = new ArrayList<>();
	try {
		object = new String(object.getBytes("ISO-8859-1"),"UTF-8");
		coordinate=searchServer.coordinateServer(object);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		logger.error("coordinateInt"+e.getMessage());
		e.printStackTrace();
	}
	   
	   return coordinate;
   }
	
   
   @At("/queryFitRoomInt")
   @Ok("json")
   public List<FitRoom> queryFitRoomInt(@Param("openid")String openid){
	   
	   List<FitRoom> fitRoom=new ArrayList<FitRoom>();
	   try {
		   fitRoom=searchServer.queryFitRoomServer(openid);
		} catch (Exception e) {
			logger.error("queryFitRoomServer:"+e.getMessage());
			e.printStackTrace();
		}
	   
	   return fitRoom;   
	}
	
   @At("/searchResult")
   @Ok("json")
   public List<Publish> searchResult(@Param("province")String province, @Param("city")String city,@Param("county")String county,
		   @Param("price")String  priceRange,@Param("payway")String payway, @Param("time")String time,@Param("object")String object){
	   Map<String,Object> map=new HashMap<>();
	   map.put("province", province);
	   map.put("city", city);
	   map.put("county", county);
	   map.put("pricedown", priceRange.split("-")[0]);
	   map.put("priceup", priceRange.split("-")[1]);
	   map.put("payway", payway);
	   map.put("time", time);
	   map.put("object", object);
	   
	   return searchServer.searchResultServer(map);
   }
	
	
	
	
	
}
