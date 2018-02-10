package com.fitness.server.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.fitness.datastruts.FitRoom;
import com.fitness.datastruts.Publish;
import com.fitness.server.publish.dao.PublishDao4Common;
import com.fitness.server.search.dao.SearchDao4Common;

@IocBean
public class SearchServer implements SearchMbean{

	@Inject("refer:searchDao4Common")
	private SearchDao4Common  searchDao;
	
	@Inject("refer:publishDao4Common")
	private PublishDao4Common publish;
	
	@Override
	public List<String> coordinateServer(String object) {
		// TODO Auto-generated method stub
		
		return searchDao.coordinate(object);
	}

	@Override
	public List<FitRoom> queryFitRoomServer(String fitRoomName) {
		// TODO Auto-generated method stub
		return searchDao.queryFitRoom(fitRoomName);
	}

	@Override
	public List<Publish> searchResultServer(Map map) {
		// TODO Auto-generated method stub
		
		return searchDao.searchResult(map);
	}

	@Override
	public Map<String, Object> condition() {
		// TODO Auto-generated method stub
		Map<String, Object> map1=new HashMap<>();//第一层
		Map<String, Object> map2=new HashMap<>();//第二层
		
		List<Map<String, Object>> list3=new ArrayList<>();//包裹第三层的list
		
		for(int i=0;i<publish.fitObject().size();i++) {
			Map<String, Object> map3=new HashMap<>();//第三层
			map3.put("id", i );
			map3.put("name", publish.fitObject().get(i));
			list3.add(map3);
		}
		map2.put("object_list", list3);//作为课种选择条件
		map1.put("conditionList", map2);
		
		
		return map1;
	}

	
	
}
