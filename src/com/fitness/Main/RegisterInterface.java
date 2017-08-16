package com.fitness.Main;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.fitness.datastruts.PersonInfoTab;
import com.fitness.server.register.RegisterService;

@IocBean
@At("/registerInterface")
public class RegisterInterface {

	@Inject("refer:defaultDao")
	private Dao defaultDao;
	
	@Inject("refer:registerService")
	private RegisterService reg;
	
	private Map<String,String> character;
	

	
	
	
	/*
	 *此方法用于插入个人注册信息 
	 * register.eo?userId=1&qqNumber=888888&signature=1&phoneNumber=1&personPicture=1&password=1&orgnization=1&nickName=1&gender=1&
	 * evalute=1&coachRecord=1&birthDay=1&area=1
	 */
	@At("/register")
	@Ok("json")
	public void insertPerInfo(HttpServletRequest request,  
            HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub		
		doGet(request,response);
		System.out.println("获得的字符串为"+character);
		try {
			List<PersonInfoTab> personInfo=new ArrayList<PersonInfoTab>();
			PersonInfoTab pt=new PersonInfoTab();
			for(String a:character.keySet()){
				pt.setUserId(Integer.valueOf(character.get("userId")));
				pt.setQqNumber(Integer.valueOf(character.get("qqNumber")));
				pt.setSignature(character.get("signature"));
				pt.setPhoneNumber(Integer.valueOf(character.get("phoneNumber")));
				pt.setPersonPicture(character.get("personPicture"));
				pt.setPassword(character.get("password"));//此处要加密传输
				pt.setOrgnization(character.get("orgnization"));
				pt.setNickName(character.get("nickName"));
				pt.setGender(character.get("gender"));
				pt.setEvalute(Float.valueOf(character.get("evalute")));
				pt.setCoachRecord(character.get("coachRecord"));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");	
				pt.setBirthDay(sdf.parse(character.get("birthDay")));
				pt.setArea(character.get("area"));
				
			}
			personInfo.add(pt);
			reg.insertPersonInfo(personInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 *此方法用于查询个人注册信息，queryperinfo?qqNumber=1&userid=2
	 * 
	 */
	@At("/queryperinfo")
	@Ok("json")
	public List<PersonInfoTab> queryPerAll(@Param("qqNumber")String qqNumber,@Param("userid")String userid) {
		// TODO Auto-generated method stub
		List<PersonInfoTab> per=new ArrayList<PersonInfoTab>();
		try {
			List<PersonInfoTab> personInfo=new ArrayList<PersonInfoTab>();
			PersonInfoTab pt=new PersonInfoTab();
	     	per.addAll(reg.queryAlls(qqNumber, userid));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return per;
	
	}
	
	@At("/checkUserExist")
	@Ok("json")
	public boolean checkUserExist(@Param("qqNumber")String qqNumber,@Param("userid")String userid){
		
		boolean check=reg.queryRegister(qqNumber, userid);
		
		
		return check;	
	}
	
	/*
	 * 用于上传个人头像
	 */
	@At("/uploadPersonIcon")
	public void uploadPersonIcon(@Param("userId") String userId, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8"); // 设置response编码方式
		request.setCharacterEncoding("UTF-8"); // 设置request编码方式

		// 创建文件项目工厂对象
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置文件上传路径
		String upload = "../personIcon/"+userId;
		//此处判断若文件夹不存在则创建
		File f=new File(upload);
		if(!f.exists()){
			f.mkdirs();
		}
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
					filename=userId+"."+filename.substring(filename.lastIndexOf(".")+1);
					//此处将用户头像地址信息保存到数据库
					reg.uploadPerIcon(userId, upload+"/"+filename);
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
	public void downloadPersonIcon(@Param("userId") String userId, HttpServletResponse request,
			HttpServletResponse response) {
		try {
			File file = new File(reg.downLoadPerIcon(userId));
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("multipart/form-data");// "multipart/form-data"
			String filename =file.getName();
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
	
	
	 public void doGet(HttpServletRequest request,  
	            HttpServletResponse response) throws ServletException, IOException {  
	        PrintWriter writer = response.getWriter();  
	        writer.println("GET " + request.getRequestURL() + " "   
	                + request.getQueryString());  
	  
	        Map<String, String[]> params = request.getParameterMap();
	        Map<String,String> query=new HashMap<>();// 此map用于接收传传过来的字符串	        
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
	        character=query;
	        writer.println("GET " + request.getRequestURL() + " " + queryString);  
	    }  
	
}
