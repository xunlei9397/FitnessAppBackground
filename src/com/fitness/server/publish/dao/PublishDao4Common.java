package com.fitness.server.publish.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.nutz.dao.Chain;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.fitness.datastruts.FitRoom;
import com.fitness.datastruts.Publish;
import com.fitness.datastruts.FitRoomList;
import com.fitness.datastruts.PersonInfoTab;

@IocBean
public class PublishDao4Common implements PublishDaoIf {

	@Inject("refer:defaultDao")
	private Dao defaultdao;
	
	@Override
	public void addObject(Publish pub) {
		// TODO Auto-generated method stub
		defaultdao.insert(pub);
	}

	@Override
	public Boolean editIsReceived(String objectid,String Content) {
		boolean check=false;
		Sql sql = Sqls.create("SELECT isReceived FROM PersonInfoTab WHERE objectid=@objectid");
	    sql.params().set("objectid", objectid);
	    sql.setCallback(new SqlCallback() {
	        public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
	            List<String> list = new LinkedList<String>();
	            while (rs.next()){
	            list.add(rs.getString("objectid"));
	            }
	            return list;
	        }
	    });
	    defaultdao.execute(sql);
	    if(sql.getList(String.class).toString()=="yes"){
	    	check=false;
	    }else{
	    	Sql sql2 = Sqls.create("update publish set isReceived=@Content WHERE objectid=@objectid isReceived!='Yes'");
			sql2.params().set("objectid", objectid);
			sql2.params().set("Content", Content);
			sql2.setCallback(Sqls.callback.entities());
			sql2.setEntity(defaultdao.getEntity(Publish.class));
			defaultdao.execute(sql2);
			check=true;
	    }
		return check;
		
		
		
	}

	@Override
	public void editYN(String objectid,String YN) {
		Publish pub=defaultdao.fetch(Publish.class,objectid);
		pub.setYN(YN);
		defaultdao.update(pub);
	}

	@Override
	public void editEvalute(String objectid,int score) {
		defaultdao.fetch(Publish.class,objectid);
		defaultdao.update(Publish.class, Chain.makeSpecial("evalute", "+"+score), null);
	}

	@Override
	public void addMapInfo(String objectid, double pointX, double pointY, int mapClass) {
		// TODO Auto-generated method stub
		Publish pub=defaultdao.fetch(Publish.class,objectid);
		pub.setPointX(pointX);
		pub.setPointY(pointY);
		pub.setMapClass(mapClass);
		defaultdao.update(pub);
	}

	
	public String getScore(String openid) {
		Sql sql = Sqls.create("SELECT sum(evalute) FROM publish WHERE openid=@openid");
	    sql.params().set("openid", openid);
	    sql.setCallback(new SqlCallback() {
	        public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
	        	List<String> list = new LinkedList<String>();
	            while (rs.next()){
	            list.add(rs.getString("sum(evalute)"));
	            }
	            return list;
	        }
	    });
	    defaultdao.execute(sql);
	    String sum=sql.getList(String.class).get(0);
		
		return sum;
	}

	@Override
	public void addFitRoomInfo(FitRoom fitRoom) {
		defaultdao.insert(fitRoom);
	}

	@Override
	public List<String> fitObject() {
		// TODO Auto-generated method stub
		Sql sql = null;
		sql = Sqls.create("SELECT name FROM fitobject");
		sql.setCallback(new SqlCallback() {
			public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
				List<String> list = new LinkedList<>();
				while (rs.next()) {
					list.add(rs.getString("name"));
				}
				return list;
			}
		});
		defaultdao.execute(sql);
		return sql.getList(String.class);
	}

	@Override
	public List<String> addFitObject(List<String> list) {
		
		for(String s:list){
			Sql	sql = Sqls.create("insert into fitobject (name) values('"+s+"')");
			defaultdao.execute(sql);
		}
		return fitObject();
	}

	@Override
	public List<String> deleteFitObject(String[] object) {
		for(String s:object){
			Sql	sql = Sqls.create("delete from fitobject where name ='"+s+"'");
			defaultdao.execute(sql);
		}
		return fitObject();
	}

	@Override
	public void addFitRoomList(FitRoomList fitroom) {
		defaultdao.insert(fitroom);
	}

	@Override
	public void deleteFitRoomList(String fitroomId) {
		Sql sql=Sqls.create("delete from fitroom where  fitroomId=@fitroomId");
	    sql.params().set("fitroomId",fitroomId);
	    defaultdao.execute(sql);
		
	}

	@Override
	public void updateFitRoomList(String fitroomId,String fitroomName) {
		// TODO Auto-generated method stub
		FitRoomList fit=defaultdao.fetch(FitRoomList.class,fitroomId.toString());
		fit.setFitRoomName(fitroomName);
		defaultdao.update(fit);
	}

	@Override
	public List<FitRoomList> searchFitRoomList(String province,String city, String county) {
		// TODO Auto-generated method stub
		Sql sql=Sqls.create("select * from fitroomlist where  province=@province or city=@city or county=@county ");
		sql.params().set("province", province);
		sql.params().set("city", city);
		sql.params().set("county", county);
		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(defaultdao.getEntity(FitRoomList.class));
		defaultdao.execute(sql);
		return sql.getList(FitRoomList.class);
	}


}
