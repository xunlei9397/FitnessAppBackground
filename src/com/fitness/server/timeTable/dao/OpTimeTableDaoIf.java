package com.fitness.server.timeTable.dao;

import java.util.List;
import java.util.Map;

import com.fitness.datastruts.TranTimeTable;

public interface OpTimeTableDaoIf {

	//课表插入时的查询条件：userid，开始结束时间，最终查询字段为 星期字段中的内容
		/*写一方法，里面有一判断，判断的字段有两个，userid&&开始结束时间（为插入或修改时的时间）这一行值是否存在，
		 *若无值，用insert语句，
		 *若存在则用upString语句
		 */
	
	//判断userid&&开始结束时间 是否存在
	boolean judgeExist(String openid,String startTime,String endTime);
	
	//这里传过来时数据应该解析成一条一条的进行传输map里面存放的周一到周日的课程键值对
	void insertClass(List<TranTimeTable> timeTable);
	
	//upString可以批量，可以单个，批量时多个时间段的数据应该被解析成一条一条的数据，单个时直接传输，若某课程之惟空则直接set 列名=null
	void updateClass(String userid,String startTime,String endTime,Map<String,String> course);
	
	//删除某一时间段的课程
	void deleteClass(String userid,String startTime,String endTime);
	
	//查询用户的所有课程
	List<TranTimeTable> selectClass(String userid);
	
	
}
