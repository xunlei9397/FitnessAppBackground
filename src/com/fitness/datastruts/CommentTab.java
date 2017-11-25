package com.fitness.datastruts;

import org.nutz.dao.entity.annotation.Table;

//记录用户说说评论表
@Table("commentTab")
public class CommentTab {

	/*
	 * 评论用户的openid
	 * 被评论的消息的ssid
	 * 评论的内容
	 * */
	private String openid;
	
	private String ssid;
	
	private String comment;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getSsid() {
		return ssid;
	}

	public void setSsid(String ssid) {
		this.ssid = ssid;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "CommentTab [openid=" + openid + ", ssid=" + ssid + ", comment=" + comment + "]";
	}
	
	
	
	
}
