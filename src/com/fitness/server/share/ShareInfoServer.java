package com.fitness.server.share;

import java.util.List;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.fitness.datastruts.CommentTab;
import com.fitness.datastruts.PicVidAddress;
import com.fitness.datastruts.Share;
import com.fitness.server.share.dao.ShareInfoDao4Common;

@IocBean
public class ShareInfoServer implements ShareInfoMbean {

	@Inject("shareInfoDao4Common")
	private ShareInfoDao4Common shareInfo;
	
	@Override
	public void shareServer(Share share) {
		shareInfo.share(share);
		
	}

	@Override
	public void commentServer(CommentTab comment) {
		shareInfo.comment(comment);
		
	}

	@Override
	public List<Share> searchShareServer(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return shareInfo.searchShare(pageNumber, pageSize);
	}

	@Override
	public List<CommentTab> searchCommentServer(String ssid) {
		// TODO Auto-generated method stub
		return shareInfo.searchComment(ssid);
	}

	@Override
	public int pageNumberServer(int pageSize) {
		// TODO Auto-generated method stub
		return shareInfo.pageNumber(pageSize);
	}

	@Override
	public int addAdmireNumServer(String ssid) {
		// TODO Auto-generated method stub
		return shareInfo.addAdmireNum(ssid);
	}

	@Override
	public void updateAddressServer(PicVidAddress pvAddress,Boolean isPic) {
		// TODO Auto-generated method stub
		shareInfo.updateAddress(pvAddress,isPic);
	}

	@Override
	public List<String> searchAddressServer(String ssid) {
		// TODO Auto-generated method stub
		return shareInfo.searchAddress(ssid);
	}

}
