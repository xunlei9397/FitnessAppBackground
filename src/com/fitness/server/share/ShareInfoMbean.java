package com.fitness.server.share;

import java.util.List;

import com.fitness.datastruts.CommentTab;
import com.fitness.datastruts.PicVidAddress;
import com.fitness.datastruts.Share;

public interface ShareInfoMbean {

	
	void shareServer(Share share);
	
	void commentServer(CommentTab comment);
	
	List<Share> searchShareServer(int pageNumber,int pageSize);
	
	List<CommentTab> searchCommentServer(String ssid);
	
	int pageNumberServer(int pageSize);
	
	int addAdmireNumServer(String ssid);
	
	void updateAddressServer(PicVidAddress pvAddress,Boolean isPic);
	
	List<String> searchAddressServer(String ssid);
}
