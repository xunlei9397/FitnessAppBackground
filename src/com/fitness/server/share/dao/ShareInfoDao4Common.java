package com.fitness.server.share.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.fitness.datastruts.CommentTab;
import com.fitness.datastruts.PicVidAddress;
import com.fitness.datastruts.Publish;
import com.fitness.datastruts.Share;

@IocBean
public class ShareInfoDao4Common implements ShareInfoDaoIf {

	@Inject("refer:defaultDao")
	private Dao defaultdao;

	@Override
	public void share(Share share) {
		// TODO Auto-generated method stub
		defaultdao.insert(share);
	}

	@Override
	public void comment(CommentTab comment) {
		// TODO Auto-generated method stub
		defaultdao.insert(comment);
	}

	@Override
	public List<Share> searchShare(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		List<Share> share = defaultdao.query(Share.class, Cnd.orderBy().desc("time"),
				defaultdao.createPager(pageNumber, pageSize));

		return share;
	}

	@Override
	public List<CommentTab> searchComment(String ssid) {
		List<CommentTab> comment = defaultdao.query(CommentTab.class, Cnd.where("ssid", "=", ssid));
		return comment;
	}

	@Override
	public int pageNumber(int pageSize) {
		// TODO Auto-generated method stub
		int number = 0;

		if (pageSize != 0) {
			if (defaultdao.count(Share.class) % pageSize != 0) {
				number = defaultdao.count(Share.class) / pageSize + 1;
			} else {
				number = defaultdao.count(Share.class) / pageSize;
			}

		}
		return number;
	}

	@Override
	public int addAdmireNum(String ssid) {
		defaultdao.fetch(Share.class, ssid);
		defaultdao.update(Share.class, Chain.makeSpecial("admireNum", "+" + 1), null);
		Sql sql = Sqls.create("SELECT admireNum FROM share WHERE ssid=@ssid");
		sql.params().set("ssid", ssid);
		sql.setCallback(new SqlCallback() {
			public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {

				while (rs.next()) {
					rs.getInt("admireNum");
				}
				return null;
			}
		});
		defaultdao.execute(sql);
		return sql.getInt();
	}

	@Override
	public void updateAddress(PicVidAddress pvAddress, Boolean isPic) {
		// TODO Auto-generated method stub
		defaultdao.insert(pvAddress);

	}

	@Override
	public List<String> searchAddress(String ssid) {
		// TODO Auto-generated method stub
		Sql sql = null;
		sql = Sqls.create("SELECT picAddress,vidAddress FROM picVidAddress WHERE ssid=@ssid");
		sql.params().set("ssid", ssid);
		sql.setCallback(new SqlCallback() {
			public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
				List<String> list = new LinkedList<>();
				while (rs.next()) {
					list.add(rs.getString("picAddress"));
					list.add(rs.getString("vidAddress"));
				}
				return list;
			}
		});
		defaultdao.execute(sql);
		return sql.getList(String.class);
	}

}
