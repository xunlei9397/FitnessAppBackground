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
	 * �����ϴ�����ͷ��
	 */
	@At("/uploadPersonIcon")
	public void uploadPersonIcon(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8"); // ����response���뷽ʽ
		request.setCharacterEncoding("UTF-8"); // ����request���뷽ʽ

		// �����ļ���Ŀ��������
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// �����ļ��ϴ�·��
		String upload = "../videofile";
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
					// ��������ʽ�����ϴ��ļ�����������
					InputStream in = item.getInputStream();
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
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}

	/*
	 * �����ϴ�����ͷ��
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
		Map<String, String> query = new HashMap<>();// ��map���ڽ��մ����������ַ���
		String queryString = "";
		for (String key : params.keySet()) {
			String[] values = params.get(key);
			for (int i = 0; i < values.length; i++) {
				String value = values[i];
				query.put(key, value);
				queryString += key + "=" + value + "&";
			}
		}
		// ȥ�����һ���ո�
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
		// ȥ�����һ���ո�
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
