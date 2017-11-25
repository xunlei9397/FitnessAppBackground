package com.fitness.server.timeTable.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.fitness.datastruts.PersonInfoTab;
import com.fitness.datastruts.TranTimeTable;

@IocBean
public class OpTimeTableDao4Common  implements OpTimeTableDaoIf {

	
	@Inject("refer:defaultDao")
	private Dao defaultDao;
	
	@Override
	public boolean judgeExist(String openid, String startTime, String endTime) {
		boolean check=true;
		Sql sql = Sqls.create("SELECT openid,startTime,endTime FROM TranTimeTable WHERE  openid=@openid and startTime=@startTime and endTime=@endTime");
	    sql.params().set("openid", openid);
	    sql.params().set("startTime", startTime);
	    sql.params().set("endTime", endTime);
	    sql.setCallback(new SqlCallback() {
	        public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
	            List<String> list = new LinkedList<String>();
	            while (rs.next()){
	            list.add(rs.getString("openid"));
	            list.add(rs.getString("startTime"));
	            list.add(rs.getString("endTime"));
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
	public void insertClass(List<TranTimeTable> timeTable) {
		//此处需要有一个方法把map解析后直接放到sql语句中，用逗号分隔的内容
		List<TranTimeTable> tranTimeTable=new ArrayList<TranTimeTable>();
		
		for(TranTimeTable table:timeTable){
			tranTimeTable.add(table);
		}
		defaultDao.insert(tranTimeTable);
	}

	@Override
	public void updateClass(String userid, String startTime, String endTime, Map<String, String> course) {
		Sql sql=Sqls.create("update TranTimeTable  set "+getMapSql(course)+"  where  userId=@userid and startTime=@startTime and endTime=@endTime");
		sql.params().set("userid", userid);
	    sql.params().set("startTime", startTime);
	    sql.params().set("endTime", endTime);
		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(defaultDao.getEntity(TranTimeTable.class));
		defaultDao.execute(sql);
		
	}

	@Override
	public void deleteClass(String userid, String startTime, String endTime) {
		Sql sql=Sqls.create("delete from TranTimeTable where  userId=@userid and startTime=@startTime and endTime=@endTime ");
	    sql.params().set("userid", userid);
	    sql.params().set("startTime", startTime);
	    sql.params().set("endTime", endTime);
/*		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(defaultDao.getEntity(TranTimeTable.class));*/
		defaultDao.execute(sql);
	}

	@Override
	public List<TranTimeTable> selectClass(String userid) {
		// TODO Auto-generated method stub
		Sql sql=Sqls.create("select * from TranTimeTable where  userId=@userid");
	    sql.params().set("userid", userid);
		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(defaultDao.getEntity(TranTimeTable.class));
		defaultDao.execute(sql);
		System.out.println("查询出的数据为"+sql.getList(TranTimeTable.class));
		return sql.getList(TranTimeTable.class);
	}


	public static String getMapSql(Map<String,String> course){
		String field="";
		for(String key:course.keySet() ){
			field+=key+"="+course.get(key)+",";			
		}
		field=field.substring(0, field.lastIndexOf(","));
		return field;
	}

	
	
	
	
}
