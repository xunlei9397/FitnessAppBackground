package com.fitness.server.register;

import java.util.List;

import com.fitness.datastruts.PersonInfoTab;

public interface RegisterMbean {
	String SERVICE_NAME = "registerService";
	
	List<PersonInfoTab> queryAlls(String qqNumber, String userId);
	
	boolean queryRegister(String qqNumber,String userId);
	
	void uploadPerIcon(String userId,String personPicture);
	
	String downLoadPerIcon(String userId);
	
	
}
