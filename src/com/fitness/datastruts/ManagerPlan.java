package com.fitness.datastruts;

import java.util.Date;

public class ManagerPlan {

	private int userId;
	
	private Date startTime;
	 
	private Date endTime;
	
	//存放上课地点，告诉前端用“-”做分隔符，如 中国-湖北省-武汉市-洪山区-华中科技大学
	private String address;
	
	//课种
	private String classType;
	
	//课时费，此处告诉前端传过来时使用加密，数据库使用者也无法看到实际费用
	private String classFee;
	
	
}
