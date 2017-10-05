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

}