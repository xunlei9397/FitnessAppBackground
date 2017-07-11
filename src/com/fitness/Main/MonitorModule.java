package com.fitness.Main;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.nutz.dao.Dao;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.fitness.datastruts.PersonInfoTab;
import com.fitness.server.register.RegisterMbean;
import com.fitness.server.register.RegisterService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@IocBean
public class MonitorModule extends HttpServlet {

	@Inject("refer:defaultDao")
	private Dao defaultDao;

	@Inject("refer:registerService")
	private RegisterService r;

	private Map<String, String> character;

	public MonitorModule() {
		super();
	}

	private Ioc iocBus;

	@At("/hello")
	@Ok("json")
	public Map getHello() {
		Map<String, List<String>> hehe = new HashMap<>();
		List<String> a = new ArrayList<>();
		List<String> b = new ArrayList<>();
		a.add("1");
		a.add("2");
		a.add("3");
		a.add("4");
		a.add("5");
		b.addAll(a);
		hehe.put("1", a);
		b.add("b1");
		hehe.put("2", b);
		System.out.println("jdkjdkj");
		return hehe;
	}

	@At("/dao")
	@Ok("json")
	public Object getDao() {
		return defaultDao;
	}

	
	/*
	 * 用于上传个人头像
	 */
	@At("/uploadPersonIcon")
	public void uploadPersonIcon(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8"); // 设置response编码方式
		request.setCharacterEncoding("UTF-8"); // 设置request编码方式

		// 创建文件项目工厂对象
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置文件上传路径
		String upload = "../videofile";
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
					// 以流的形式返回上传文件的数据内容
					InputStream in = item.getInputStream();
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
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 用于上传个人头像
	 */
	@At("/downloadPersonIcon")
	@Ok("json")
	public void downloadPersonIcon(@Param("fileId") long fileId, HttpServletResponse request,
			HttpServletResponse response) {
		try {

			// FileBase fb =
			// FileManager.getInstance().getFileBaseById(Long.valueOf(fileId));
			File filePathBuffer = new File(System.getProperty("user.dir") + File.separator + "../videofile");
			String filePath = filePathBuffer.getCanonicalPath().toString();
			System.out.println("filePath" + filePath);
			File file = new File(filePath + "/copy.jpg");
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("multipart/form-data");// "multipart/form-data"
			String filename = "copy.jpg";
			response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			try {
				bis = new BufferedInputStream(new FileInputStream(file));
				bos = new BufferedOutputStream(response.getOutputStream());
				byte[] buff = new byte[1024];
				int bytesRead = 0;
				while (-1 != (bytesRead = (bis.read(buff)))) {
					bos.write(buff, 0, bytesRead);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (bis != null) {
					bis.close();
				}
				if (bos != null) {
					bos.close();
				}
			}

		} catch (Exception e) {

		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		writer.println("GET " + request.getRequestURL() + " " + request.getQueryString());

		Map<String, String[]> params = request.getParameterMap();
		Map<String, String> query = new HashMap<>();// 此map用于接收传传过来的字符串
		String queryString = "";
		for (String key : params.keySet()) {
			String[] values = params.get(key);
			for (int i = 0; i < values.length; i++) {
				String value = values[i];
				query.put(key, value);
				queryString += key + "=" + value + "&";
			}
		}
		// 去掉最后一个空格
		queryString = queryString.substring(0, queryString.length() - 1);
		character = query;
		writer.println("GET " + request.getRequestURL() + " " + queryString);
	}

	@At("/dopost")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		Map<String, String[]> params = request.getParameterMap();
		String queryString = "";
		for (String key : params.keySet()) {
			String[] values = params.get(key);
			for (int i = 0; i < values.length; i++) {
				String value = values[i];
				queryString += key + "=" + value + "&";
			}
		}
		// 去掉最后一个空格
		queryString = queryString.substring(0, queryString.length() - 1);
		writer.println("POST " + request.getRequestURL() + " " + queryString);
	}

	public Map<String, String> getCharacter() {
		return character;
	}

	public void setCharacter(Map<String, String> character) {
		this.character = character;
	}

}
