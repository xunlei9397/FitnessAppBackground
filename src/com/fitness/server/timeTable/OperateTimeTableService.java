package com.fitness.server.timeTable;

import java.util.List;
import java.util.Map;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.fitness.datastruts.TranTimeTable;
import com.fitness.server.timeTable.dao.OpTimeTableDao4Common;

@IocBean
public class OperateTimeTableService implements OperateTimeTableBean {

	@Inject("refer:opTimeTableDao4Common")
	private OpTimeTableDao4Common opTimeTable;
	
	@Override
	public boolean judgeExistService(String userid, String startTime, String endTime) {
		// TODO Auto-generated method stub
		opTimeTable.judgeExist(userid, startTime, endTime);
		return false;
	}

	@Override
	public void insertClassService(List<TranTimeTable> timeTable) {
		// TODO Auto-generated method stub
		opTimeTable.insertClass(timeTable);
	}

	@Override
	public void updateClassService(String userid, String startTime, String endTime, Map<String, String> course) {
		// TODO Auto-generated method stub
		opTimeTable.updateClass(userid, startTime, endTime, course);
	}

	@Override
	public void deleteClassService(String userid, String startTime, String endTime) {
		// TODO Auto-generated method stub
		opTimeTable.deleteClass(userid, startTime, endTime);
	}

	@Override
	public List<TranTimeTable> selectClassService(String userid) {
		// TODO Auto-generated method stub
		
		return opTimeTable.selectClass(userid);
	}
	

	
	
	
	
}
