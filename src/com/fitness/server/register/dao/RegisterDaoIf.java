package com.fitness.server.register.dao;

import java.util.List;

import com.fitness.datastruts.PersonInfoTab;

public interface RegisterDaoIf {
	String DAO_NAME = "registerDaoIf";
	
	List<PersonInfoTab> queryall(String qqNumber, String userId);
	
	boolean  register(String qqNumber,String userId);
 	
	void uploadPerPic(String userId,String personPicture);
	
	String downLoadPerPic(String userId);
	
}
