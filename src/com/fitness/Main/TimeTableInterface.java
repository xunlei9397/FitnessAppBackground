package com.fitness.Main;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.fitness.datastruts.TranTimeTable;
import com.fitness.server.timeTable.OperateTimeTableService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.sf.json.JSONObject;


@IocBean
@At("/timeTableInterface")
public class TimeTableInterface {

	
	@Inject("refer:operateTimeTableService")
	private OperateTimeTableService opTimeTable;
	
	
	@At("/checkClassExist")
	@Ok("json")
	public boolean checkUserExist(@Param("userid")String userid,@Param("startTime")String startTime,@Param("endTime")String endTime){		
		boolean check=true;
			check = opTimeTable.judgeExistService(userid, startTime, endTime);
		return check;	
	}
	

	
	@At("/insertClassIf")
	@Ok("json")
	public void insertClassIf(@Param("timeTables")String timeTables) {
		/*
		 * 举例：[{"userId":123456,"startTime":"18:00","endTime":"19:00","Mon":"1","Tue":"2","Wed":"3","Thu":"4","Fri":"5","Sat":"6","Sun":"7"},
			{"userId":789,"startTime":"18:00","endTime":"19:00","Mon":"1","Tue":"2","Wed":"3","Thu":"4","Fri":"5","Sat":"6","Sun":"7"}]
		 * */
		//timeTables 接受json，周一到到周末的课表
		Gson gson = new Gson();  
		List<TranTimeTable> timeTable = gson.fromJson(timeTables, new TypeToken<List<TranTimeTable>>(){}.getType()); 
		opTimeTable.insertClassService(timeTable);
	}

	@At("/updateClassIf")
	@Ok("json")
	public void updateClassIf(@Param("userid")String userid,@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("courses") String courses) {
		/*
		 * 举例updateClassIf.eo?userid=1145&startTime=18:00&endTime=19:00&courses={"Mon":"1","Tue":"1","Wed":"1","Thu":"1","Fri":"1","Sat":"1","Sun":"1"}
		 * */
		//courses 接受json，周一到到周末的课程	
		// TODO Auto-generated method stub
		opTimeTable.updateClassService(userid, startTime, endTime, JsonToMap(courses));
	}

	@At("/deleteClassIf")
	@Ok("json")
	public void deleteClassIf(@Param("userid")String userid,@Param("startTime") String startTime,@Param("endTime") String endTime) {
		// TODO Auto-generated method stub
		opTimeTable.deleteClassService(userid, startTime, endTime);
	}

	@At("/selectClassIf")
	@Ok("json")
	public List<TranTimeTable> selectClassIf(@Param("userid")String userid) {
		// TODO Auto-generated method stub
		
		return opTimeTable.selectClassService(userid);
	}
	
	public  Map<String,String> JsonToMap(String jsonStr) {
		Map<String, String> map = new HashMap<String, String>();

		JSONObject jsonObject = JSONObject.fromObject(jsonStr);

		Iterator<String> keys = jsonObject.keys();// 定义迭代器

		String key = null;
		String value = null;

		while (keys.hasNext()) {
			key = keys.next();
			value = (String) jsonObject.get(key);

			map.put(key, value);
		}
		System.out.println("map" + map);
		return map;
	}
	
	
	
	
	
}
