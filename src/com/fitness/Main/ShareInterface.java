package com.fitness.Main;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.fitness.datastruts.CommentTab;
import com.fitness.datastruts.PicVidAddress;
import com.fitness.datastruts.Share;
import com.fitness.server.share.ShareInfoServer;

@IocBean
@At("/shareInterface")
public class ShareInterface {

	@Inject("shareInfoServer")
	private ShareInfoServer shareInfo;

	Logger logger = Logger.getLogger(ShareInterface.class.getName());

	@At("/shareInt")
	@Ok("json")
	Boolean shareInt(@Param("openid") String openid, @Param("time") Date time, @Param("message") String message,
			@Param("videoAddress") String videoAddress) {
		Boolean b = false;
		try {
			Share share = new Share();
			share.setOpenid(openid);
			share.setSsid(openid + "+" + time.hashCode());
			share.setTime(time);
			share.setMessage(message);
			shareInfo.shareServer(share);
			b = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("shareInt:" + e.getMessage());
			e.printStackTrace();
		}
		return b;
	}

	@At("/commentInt")
	@Ok("json")
	public Boolean commentInt(@Param("openid") String openid, @Param("ssid") String ssid, @Param("comment") String comment) {
		Boolean b = false;
		try {
			CommentTab com = new CommentTab();
			com.setOpenid(openid);
			com.setSsid(ssid);
			com.setComment(comment);
			shareInfo.commentServer(com);
			b = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("commentInt:" + e.getMessage());
			e.printStackTrace();
		}
		return b;
	}

	@At("/searchShareInt")
	@Ok("json")
	public List<Share> searchShareInt(int pageNumber, int pageSize) {
		List<Share> share = new ArrayList<>();
		try {
			share = shareInfo.searchShareServer(pageNumber, pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("searchShareInt:" + e.getMessage());
			e.printStackTrace();
		}
		return share;
	}

	@At("/searchCommentInt")
	@Ok("json")
	public List<CommentTab> searchCommentInt(String ssid) {
		List<CommentTab> comment = new ArrayList<>();
		try {
			comment = shareInfo.searchCommentServer(ssid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("searchCommentInt:" + e.getMessage());
			e.printStackTrace();
		}
		return comment;
	}

	@At("/pageNumberInt")
	@Ok("json")
	public int pageNumberInt(int pageSize) {
		int number = 0;
		try {
			number = shareInfo.pageNumberServer(pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("pageNumberInt:" + e.getMessage());
			e.printStackTrace();
		}
		return number;
	}

	@At("/addAdmireNumInt")
	@Ok("json")
	public int addAdmireNumInt(@Param("ssid") String ssid) {
		int num = 0;

		try {
			num = shareInfo.addAdmireNumServer(ssid);
		} catch (Exception e) {
			logger.error("addAdmireNumInt:" + e.getMessage());
			e.printStackTrace();
		}

		return num;
	}

	/*
	 * �����ϴ�����ͼƬ��Ƶ
	 */
	@At("/uploadPicVid")
	public Boolean uploadPersonIcon(@Param("openid") String openid, @Param("ssid") String ssid,
			@Param("isPic") Boolean isPic, HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		// isPic�ֶ��ж�����Ƶ�����ļ�����ΪtrueʱΪͼƬ����ΪfalseʱΪ��Ƶ
		Boolean b=false;
		response.setCharacterEncoding("UTF-8"); // ����response���뷽ʽ
		request.setCharacterEncoding("UTF-8"); // ����request���뷽ʽ
		// �����ļ���Ŀ��������
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// �����ļ��ϴ�·��
		String upload = "/personFile/" + openid;
		// �˴��ж����ļ��в������򴴽�

		// ��ȡϵͳĬ�ϵ���ʱ�ļ�����·������·��ΪTomcat��Ŀ¼�µ�temp�ļ���
		String temp = System.getProperty("java.io.tmpdir");
		// ���û�������СΪ 1M
		factory.setSizeThreshold(1024 * 1024);
		// ������ʱ�ļ���Ϊtemp
		factory.setRepository(new File(temp));
		// �ù���ʵ�����ϴ����,ServletFileUpload ���������ļ��ϴ�����
		ServletFileUpload servletFileUpload = new ServletFileUpload(factory);

		// ����request
		try {
			// �����������List��
			List<FileItem> list = servletFileUpload.parseRequest(request);
			for (FileItem item : list) {// ��������FileItem
				// ��ȡ����������
				String name = item.getFieldName();

				// ���Ϊ�ı���
				if (item.isFormField()) {
					String value = item.getString();// ��ȡ�û������ַ���
					System.out.println(item.getFieldName() + ":" + value);
					request.setAttribute(name, value);
				} else {// ����Ϊ�ļ���
						// ��ȡ�ϴ��ļ���
					String filename = item.getName();
					filename = filename.hashCode() + "." + filename.substring(filename.lastIndexOf(".") + 1);
					// �˴����û�ͷ���ַ��Ϣ���浽���ݿ�
					PicVidAddress address = new PicVidAddress();
					address.setOpenid(openid);
					address.setSsid(ssid);
					if (isPic) {
						address.setPicAddress("http://47.94.149.197:"+request.getLocalPort()+upload + "/" + filename);
					} else {
						address.setVidAddress("http://47.94.149.197:"+request.getLocalPort()+upload + "/" + filename);
					}
					shareInfo.updateAddressServer(address, isPic);
					// ��������ʽ�����ϴ��ļ�����������
					InputStream in = item.getInputStream();
					upload="../webapps"+upload;
					File f = new File(upload);
					if (!f.exists()) {
						f.mkdirs();
					}

					// ����һ����ָ�� File �����ʾ���ļ���д�����ݵ��ļ������
					FileOutputStream outs = new FileOutputStream(new File(upload, filename));
					int len = 0;
					// �����ֽ�����buffer, ��СΪ1024
					byte[] buffer = new byte[1024];
					System.out.println("�ϴ��ļ���С��" + item.getSize() + " KB");
					while ((len = in.read(buffer)) != -1) {
						// ��ָ�� buffer �����д�ƫ���� 0 ��ʼ�� len ���ֽ�д����ļ������
						outs.write(buffer, 0, len);
					}
					in.close(); // �ر�������
					outs.close(); // �ر������
				}
			}
			b=true;
		} catch (FileUploadException e) {
			logger.error("uploadPersonIcon:" + e.getMessage());
			e.printStackTrace();
		}
		return b;
	}

	@At("/downloadList")
	@Ok("json")
	public List<String> downloadList(@Param("ssid") String ssid){
		List<String> list=new ArrayList<>();	
		try {
			list=shareInfo.searchAddressServer(ssid);
		} catch (Exception e) {
			logger.error("downloadList:" + e.getMessage());
			e.printStackTrace();
		}
		
		return list;	
	}
	


}
