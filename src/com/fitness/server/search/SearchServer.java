package com.fitness.server.search;

import java.util.List;
import java.util.Map;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.fitness.datastruts.FitRoom;
import com.fitness.datastruts.Publish;
import com.fitness.server.search.dao.SearchDao4Common;

@IocBean
public class SearchServer implements SearchMbean{

	@Inject("refer:searchDao4Common")
	private SearchDao4Common  searchDao;
	
	@Override
	public List<String> coordinateServer(String object) {
		// TODO Auto-generated method stub
		
		return searchDao.coordinate(object);
	}

	@Override
	public List<FitRoom> queryFitRoomServer(String openid) {
		// TODO Auto-generated method stub
		return searchDao.queryFitRoom(openid);
	}

	@Override
	public List<Publish> searchResultServer(Map map) {
		// TODO Auto-generated method stub
		
		return searchDao.searchResult(map);
	}

	
	
}
