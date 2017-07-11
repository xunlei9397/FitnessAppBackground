package com.fitness.server.register;

import java.util.List;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.fitness.datastruts.PersonInfoTab;
import com.fitness.server.register.dao.RegisterDao4Common;
import com.fitness.server.register.dao.RegisterDaoIf;

@IocBean
public class RegisterService  implements RegisterMbean {

	@Inject("refer:registerDao4Common")
	private RegisterDao4Common registerDao4Common;
	
	
	public List<PersonInfoTab> queryAlls(String qqNumber, String userId) {
		// TODO Auto-generated method stub
		return registerDao4Common.queryall(qqNumber,userId);
		
	}
	
	
	public void insertPersonInfo(List<PersonInfoTab> personInfo){
		registerDao4Common.insert(personInfo);
		
	}


	@Override
	public boolean queryRegister(String qqNumber, String userId) {
		// TODO Auto-generated method stub
		boolean check=registerDao4Common.register(qqNumber, userId);
		
		return check;
	}


	

}
