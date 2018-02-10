package com.fitness.server.search.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.fitness.datastruts.CommentTab;
import com.fitness.datastruts.FitRoom;
import com.fitness.datastruts.ManagerPlan;
import com.fitness.datastruts.PersonInfoTab;
import com.fitness.datastruts.PicVidAddress;
import com.fitness.datastruts.Publish;
import com.fitness.datastruts.Search;
import com.fitness.datastruts.Share;
import com.fitness.datastruts.TranTimeTable;

@IocBean
public class SearchDao4Common  implements SearchDaoIf{

	@Inject("refer:defaultDao")
	private Dao defaultdao;
	
	
	public List<String> coordinate(String object) {
		Sql sql = Sqls.create("SELECT pointX,pointY,mapClass FROM publish WHERE object=@object isReceived!='Yes'");
	    sql.params().set("object", object);
	    sql.setCallback(new SqlCallback() {
	        public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
	        	List<String> list = new LinkedList<String>();
	            while (rs.next()){
	            list.add(rs.getString("pointX")+","+rs.getString("pointY")+","+rs.getString("mapClass"));
	            }
	            return list;
	        }
	    });
	    defaultdao.execute(sql);
		
		return sql.getList(String.class);
	}


	public List<FitRoom> queryFitRoom(String fitRoomName) {
		Sql sql=Sqls.create("select * from FitRoom where fitRoomName=@fitRoomName");
		sql.params().set("fitRoomName", fitRoomName);
		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(defaultdao.getEntity(FitRoom.class));
		defaultdao.execute(sql);
		return sql.getList(FitRoom.class);
	}


	@Override
	public List<Publish> searchResult(Map map) {
		Sql sql=Sqls.create("");
		if(!"不限".equals(map.get("county"))&!"不限".equals(map.get("object"))) {
			 sql=Sqls.create("select * from Publish where province=@province and "
					+ "city=@city and county=@county and price>=@pricedown and "
					+ "price<=@priceup and payway=@payway and DATE_FORMAT(time,'%Y-%m-%d')=@time and "
					+ "object=@object");
		}else  if("不限".equals(map.get("county"))&!"不限".equals(map.get("object"))) {
			sql=Sqls.create("select * from Publish where province=@province and "
					+ "city=@city  and price>=@pricedown and "
					+ "price<=@priceup and payway=@payway and DATE_FORMAT(time,'%Y-%m-%d')=@time and "
					+ "object=@object");
		}else if(!"不限".equals(map.get("county"))&"不限".equals(map.get("object"))||map.get("object")==null) {
			sql=Sqls.create("select * from Publish where province=@province and "
					+ "city=@city and county=@county and price>=@pricedown and "
					+ "price<=@priceup and payway=@payway and DATE_FORMAT(time,'%Y-%m-%d')=@time ");
		}else  if("不限".equals(map.get("county"))&"不限".equals(map.get("object"))) {
			sql=Sqls.create("select * from Publish where province=@province and "
					+ "city=@city  and price>=@pricedown and "
					+ "price<=@priceup and payway=@payway and DATE_FORMAT(time,'%Y-%m-%d')=@time ");
		}
		sql.params().set("province", map.get("province"));
		sql.params().set("city", map.get("city"));
		sql.params().set("county", map.get("county"));
		sql.params().set("pricedown", map.get("pricedown"));
		sql.params().set("priceup", map.get("priceup"));
		sql.params().set("payway", map.get("payway"));
		sql.params().set("time", map.get("time"));
		sql.params().set("object", map.get("object"));				
		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(defaultdao.getEntity(Publish.class));
		defaultdao.execute(sql);
		List<Publish> pubList=new ArrayList<>();
		for(Publish pub:sql.getList(Publish.class)) {
			
			PersonInfoTab p = defaultdao.fetch(PersonInfoTab.class,pub.getOpenid());
			if(p==null) {
				continue;
			}
			pub.setProvidername(p.getNickName());
			
			pubList.add(pub);
		}
		
		return pubList;
	}


	@Override
	public Map<String, Object> conditionInfo() {
		// TODO Auto-generated method stub
		
		return null;
	}

}
