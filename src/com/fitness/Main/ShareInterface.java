package com.fitness.Main;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
	 * 用于上传个人图片视频
	 */
	@At("/uploadPicVid")
	public Boolean uploadPersonIcon(@Param("openid") String openid, @Param("ssid") String ssid,
			@Param("isPic") Boolean isPic, HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		// isPic字段判断是视频还是文件，当为true时为图片，当为false时为视频
		Boolean b=false;
		response.setCharacterEncoding("UTF-8"); // 设置response编码方式
		request.setCharacterEncoding("UTF-8"); // 设置request编码方式
		// 创建文件项目工厂对象
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置文件上传路径
		String upload = "/personFile/" + openid;
		// 此处判断若文件夹不存在则创建

		// 获取系统默认的临时文件保存路径，该路径为Tomcat根目录下的temp文件夹
		String temp = System.getProperty("java.io.tmpdir");
		// 设置缓冲区大小为 1M
		factory.setSizeThreshold(1024 * 1024);
		// 设置临时文件夹为temp
		factory.setRepository(new File(temp));
		// 用工厂实例化上传组件,ServletFileUpload 用来解析文件上传请求
		ServletFileUpload servletFileUpload = new ServletFileUpload(factory);

		// 解析request
		try {
			// 解析结果放在List中
			List<FileItem> list = servletFileUpload.parseRequest(request);
			for (FileItem item : list) {// 遍历所有FileItem
				// 获取表单属性名称
				String name = item.getFieldName();

				// 如果为文本域
				if (item.isFormField()) {
					String value = item.getString();// 获取用户输入字符串
					System.out.println(item.getFieldName() + ":" + value);
					request.setAttribute(name, value);
				} else {// 否则为文件域
						// 获取上传文件名
					String filename = item.getName();
					filename = filename.hashCode() + "." + filename.substring(filename.lastIndexOf(".") + 1);
					// 此处将用户头像地址信息保存到数据库
					PicVidAddress address = new PicVidAddress();
					address.setOpenid(openid);
					address.setSsid(ssid);
					if (isPic) {
						address.setPicAddress("http://47.94.149.197:"+request.getLocalPort()+upload + "/" + filename);
					} else {
						address.setVidAddress("http://47.94.149.197:"+request.getLocalPort()+upload + "/" + filename);
					}
					shareInfo.updateAddressServer(address, isPic);
					// 以流的形式返回上传文件的数据内容
					InputStream in = item.getInputStream();
					upload="../webapps"+upload;
					File f = new File(upload);
					if (!f.exists()) {
						f.mkdirs();
					}

					// 创建一个向指定 File 对象表示的文件中写入数据的文件输出流
					FileOutputStream outs = new FileOutputStream(new File(upload, filename));
					int len = 0;
					// 定义字节数组buffer, 大小为1024
					byte[] buffer = new byte[1024];
					System.out.println("上传文件大小：" + item.getSize() + " KB");
					while ((len = in.read(buffer)) != -1) {
						// 将指定 buffer 数组中从偏移量 0 开始的 len 个字节写入此文件输出流
						outs.write(buffer, 0, len);
					}
					in.close(); // 关闭输入流
					outs.close(); // 关闭输出流
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
