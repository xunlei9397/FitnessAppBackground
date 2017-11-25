package com.fitness.server.register.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;

import com.fitness.datastruts.PersonInfoTab;

@IocBean
public class RegisterDao4Common implements RegisterDaoIf {

	@Inject("refer:defaultDao")
	private Dao defaultDao;
	
	
	
	
	
	public List<PersonInfoTab> queryall(String openid) {
		// TODO Auto-generated method stub
		
		Sql sql=Sqls.create("select * from PersonInfoTab where  openid=@openid");
		sql.params().set("openid", openid);
		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(defaultDao.getEntity(PersonInfoTab.class));
		defaultDao.execute(sql);
		System.out.println("查询出的数据为"+sql.getList(PersonInfoTab.class));
		return sql.getList(PersonInfoTab.class);
	}
	
	public void insert(List<PersonInfoTab> personInfo){
		List<PersonInfoTab> personInsert=new ArrayList<PersonInfoTab>();
		
		for(PersonInfoTab pif:personInfo){
			personInsert.add(pif);
			
			
		}
		defaultDao.insert(personInsert);
		
	}

	@Override
	public boolean register(String qqNumber, String userId) {
		boolean check=true;
		Sql sql = Sqls.create("SELECT userid,qqNumber FROM PersonInfoTab WHERE qqNumber=@qqNumber or userId=@userId");
	    sql.params().set("qqNumber", qqNumber);
	    sql.params().set("userId", userId);
	    sql.setCallback(new SqlCallback() {
	        public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
	            List<String> list = new LinkedList<String>();
	            while (rs.next()){
	            list.add(rs.getString("qqNumber"));
	            list.add(rs.getString("userId"));
	            }
	            return list;
	        }
	    });
	    defaultDao.execute(sql);
	    if(sql.getList(String.class).size()>0){
	    	check=true;
	    }else{
	    	check=false;
	    }
		
		return check;
	}

	@Override
	public void uploadPerPic(String userId, String personPicture) {
		Sql sql=Sqls.create(" update PersonInfoTab set  personPicture=@personPicture where  userId=@userId");
		sql.params().set("personPicture", personPicture);
	    sql.params().set("userId", userId);
		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(defaultDao.getEntity(PersonInfoTab.class));
		defaultDao.execute(sql);	
	}

	@Override
	public String downLoadPerPic(String userId) {
		Sql sql = Sqls.create("SELECT personPicture FROM PersonInfoTab WHERE userId=@userId");
	    sql.params().set("userId", userId);
	    sql.setCallback(new SqlCallback() {
	        public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
	            List<String> list = new LinkedList<String>();
	            while (rs.next()){
	            list.add(rs.getString("personPicture"));
	            }
	            return list;
	        }
	    });
	    defaultDao.execute(sql);
	    String address=sql.getList(String.class).get(0);
		return address;
	}
	
	
}
