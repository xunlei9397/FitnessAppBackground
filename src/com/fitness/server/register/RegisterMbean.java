package com.fitness.server.register;

import java.util.List;

import com.fitness.datastruts.PersonInfoTab;

public interface RegisterMbean {
	String SERVICE_NAME = "registerService";
	
	List<PersonInfoTab> queryAlls(String openid);
	
	boolean queryRegister(String qqNumber,String userId);
	
	void uploadPerIcon(String userId,String personPicture);
	
	String downLoadPerIcon(String userId);
	
	void updatePerService(String openid,PersonInfoTab p);
}
