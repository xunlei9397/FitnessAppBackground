package com.fitness.server.publish;

import java.util.List;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.fitness.datastruts.FitRoom;
import com.fitness.datastruts.Publish;
import com.fitness.server.publish.dao.PublishDao4Common;

@IocBean
public class PublishService implements PublishMbean {

	@Inject("refer:publishDao4Common")
	private PublishDao4Common publish;
	
	
	public void addObjectServer(Publish pub) {
		publish.addObject(pub);
		
	}

	
	public Boolean editIsReceivedServer(String objectid, String Content) {
		return publish.editIsReceived(objectid, Content);
		
	}

	
	public void editYNServer(String objectid, String YN) {
		publish.editYN(objectid, YN);
		
	}

	
	public void editEvaluteServer(String objectid, int score) {
		publish.editEvalute(objectid, score);
		
	}


	@Override
	public void addMapInfoServer(String objectid, double pointX, double pointY, int mapClass) {
		// TODO Auto-generated method stub
		publish.addMapInfo(objectid, pointX, pointY, mapClass);	
	}


	@Override
	public String getScoreServer(String openid) {
		return publish.getScore(openid);
	}


	@Override
	public void addFitRoomServer(FitRoom fitRoom) {
		// TODO Auto-generated method stub
		publish.addFitRoomInfo(fitRoom);
	}


	@Override
	public List<String> fitObjectServer() {
		// TODO Auto-generated method stub
		
		return publish.fitObject();
	}

	
}
