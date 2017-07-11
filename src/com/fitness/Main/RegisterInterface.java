package com.fitness.Main;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.fitness.datastruts.PersonInfoTab;
import com.fitness.server.register.RegisterService;

@IocBean
@At("/registerInterface")
public class RegisterInterface {

	@Inject("refer:defaultDao")
	private Dao defaultDao;
	
	@Inject("refer:registerService")
	private RegisterService reg;
	
	private Map<String,String> character;
	

	
	
	
	/*
	 *此方法用于插入个人注册信息 
	 * register.eo?userId=1&qqNumber=888888&signature=1&phoneNumber=1&personPicture=1&password=1&orgnization=1&nickName=1&gender=1&
	 * evalute=1&coachRecord=1&birthDay=1&area=1
	 */
	@At("/register")
	@Ok("json")
	public void insertPerInfo(HttpServletRequest request,  
            HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub		
		doGet(request,response);
		System.out.println("获得的字符串为"+character);
		try {
			List<PersonInfoTab> personInfo=new ArrayList<PersonInfoTab>();
			PersonInfoTab pt=new PersonInfoTab();
			for(String a:character.keySet()){
				pt.setUserId(Integer.valueOf(character.get("userId")));
				pt.setQqNumber(Integer.valueOf(character.get("qqNumber")));
				pt.setSignature(character.get("signature"));
				pt.setPhoneNumber(Integer.valueOf(character.get("phoneNumber")));
				pt.setPersonPicture(character.get("personPicture"));
				pt.setPassword(character.get("password"));//此处要加密传输
				pt.setOrgnization(character.get("orgnization"));
				pt.setNickName(character.get("nickName"));
				pt.setGender(character.get("gender"));
				pt.setEvalute(Float.valueOf(character.get("evalute")));
				pt.setCoachRecord(character.get("coachRecord"));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");	
				pt.setBirthDay(sdf.parse(character.get("birthDay")));
				pt.setArea(character.get("area"));
				
			}
			personInfo.add(pt);
			reg.insertPersonInfo(personInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 *此方法用于查询个人注册信息，queryperinfo?qqNumber=1&userid=2
	 * 
	 */
	@At("/queryperinfo")
	@Ok("json")
	public List<PersonInfoTab> queryPerAll(@Param("qqNumber")String qqNumber,@Param("userid")String userid) {
		// TODO Auto-generated method stub
		List<PersonInfoTab> per=new ArrayList<PersonInfoTab>();
		try {
			List<PersonInfoTab> personInfo=new ArrayList<PersonInfoTab>();
			PersonInfoTab pt=new PersonInfoTab();
	     	per.addAll(reg.queryAlls(qqNumber, userid));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return per;
	
	}
	
	@At("/checkUserExist")
	@Ok("json")
	public boolean checkUserExist(@Param("qqNumber")String qqNumber,@Param("userid")String userid){
		
		boolean check=reg.queryRegister(qqNumber, userid);
		
		
		return check;	
	}
	
	
	 public void doGet(HttpServletRequest request,  
	            HttpServletResponse response) throws ServletException, IOException {  
	        PrintWriter writer = response.getWriter();  
	        writer.println("GET " + request.getRequestURL() + " "   
	                + request.getQueryString());  
	  
	        Map<String, String[]> params = request.getParameterMap();
	        Map<String,String> query=new HashMap<>();// 此map用于接收传传过来的字符串	        
	        String queryString = "";  
	        for (String key : params.keySet()) {  
	            String[] values = params.get(key);  
	            for (int i = 0; i < values.length; i++) {  
	                String value = values[i];  
	                query.put(key, value);
	                queryString += key + "=" + value + "&";  
	            }  
	        }  
	        // 去掉最后一个空格  
	        queryString = queryString.substring(0, queryString.length() - 1);  
	        character=query;
	        writer.println("GET " + request.getRequestURL() + " " + queryString);  
	    }  
	
}
