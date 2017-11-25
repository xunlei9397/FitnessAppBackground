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
import java.text.ParseException;
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
import org.apache.log4j.Logger;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.adaptor.injector.RequestInjector;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.fitness.datastruts.PersonInfoTab;
import com.fitness.server.register.RegisterService;

@IocBean
@At("/registerInterface")
public class RegisterInterface {
	
	@Inject("refer:registerService")
	private RegisterService reg;
	
	private Map<String,String> character;
	
	Logger logger = Logger.getLogger(RegisterInterface.class.getName());
	
	
	@At("/selectMethod")
	@Ok("json")
	public String selectMethod(HttpServletRequest request,  
            HttpServletResponse response) throws ServletException, IOException{
		String info="";
		if(!checkUserExist(request.getParameter("openid"))){
			insertPerInfo(request,response);
			info= "用户信息插入成功";
		}else{
			System.out.println("进入了用户修改");
			updatePer(request.getParameter("openid"),request,response);
			info="用户信息修改成功";
			System.out.println(info);
		}
		return  info;
	}
	
	
	/*
	 *�˷������ڲ������ע����Ϣ 
	 * register.eo?userId=1&openid=5526gfghbb&qqNumber=888888&signature=1&phoneNumber=1&personPicture=1&password=1&orgnization=1&nickName=1&gender=1&
	 * evalute=1&coachRecord=1&birthDay=1&area=1
	 */
	@At("/register")
	@Ok("json")
	public Boolean insertPerInfo(HttpServletRequest request,  
            HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub	
		//request.setCharacterEncoding("UTF-8");//�ͻ�����ҳ���ǿ���ΪUTF-8
		Boolean b=false;
		doGet(request,response);
		System.out.println("��õ��ַ���Ϊ"+character);
		try {
			List<PersonInfoTab> personInfo=new ArrayList<PersonInfoTab>();
			PersonInfoTab pt=new PersonInfoTab();
				//pt.setUserId(Integer.valueOf(character.get("userId")));
				pt.setOpenid(character.get("openid"));
				//pt.setQqNumber(Integer.valueOf(character.get("qqNumber")));
				pt.setSignature(character.get("signature"));
				pt.setPhoneNumber(character.get("phoneNumber"));
				//pt.setPersonPicture(character.get("personPicture"));
				//pt.setPassword(character.get("password"));//�˴�Ҫ���ܴ���
				pt.setOrgnization(character.get("orgnization"));
				pt.setNickName(character.get("nickName"));
				pt.setGender(character.get("gender"));
				//pt.setEvalute(Float.valueOf(character.get("evalute")));
				pt.setCoachRecord(character.get("coachRecord"));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");	
				pt.setBirthDay(sdf.parse(character.get("birthDay")));
				pt.setArea(character.get("area"));
				
			
			personInfo.add(pt);
			reg.insertPersonInfo(personInfo);
			b=true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("register:"+e.getMessage());
			e.printStackTrace();
		}
		return b;
	}
	
	/*
	 *�˷������ڲ�ѯ����ע����Ϣ��queryperinfo?openid=2
	 * 
	 */
	@At("/queryperinfo")
	@Ok("json")
	public List<PersonInfoTab> queryPerAll(@Param("openid")String openid) {
		// TODO Auto-generated method stub
		List<PersonInfoTab> per=new ArrayList<PersonInfoTab>();
		try {
	     	per.addAll(reg.queryAlls(openid));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("queryperinfo:"+e.getMessage());
			e.printStackTrace();
		}
		return per;
	
	}
	
	@At("/checkUserExist")
	@Ok("json")
	public boolean checkUserExist(@Param("openid")String openid){
		
		boolean check = false;
		try {
			check = reg.queryRegister(openid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("checkUserExist:"+e.getMessage());
			e.printStackTrace();
		}
		
		
		return check;	
	}
	
	//��Ҫ��Ҫ���µ���Ϣ�����������ܻ��Ϊ�գ��˴�Ӧ�����쳣
	@At("/updatePer")
	@Ok("json")
	public Boolean updatePer(String openid,HttpServletRequest request,  
            HttpServletResponse response){
		PersonInfoTab pt=new PersonInfoTab();
		Boolean b=false;
		try {
			doGet(request,response);
			pt.setOpenid(character.get("openid"));
			pt.setSignature(character.get("signature"));
			pt.setPhoneNumber(character.get("phoneNumber"));
			pt.setOrgnization(character.get("orgnization"));
			pt.setNickName(character.get("nickName"));
			//pt.setGender(map.get("gender"));
			//pt.setEvalute(Float.valueOf(character.get("evalute")));
			pt.setCoachRecord(character.get("coachRecord"));
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");	
			pt.setBirthDay(sdf.parse(character.get("birthDay")));
			pt.setArea(character.get("area"));
			reg.updatePerService(openid, pt);
			b=true;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return b;
		} catch (ParseException e) {
			
			e.printStackTrace();
			return b;
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return b;
	}
	
	
	/*
	 * �����ϴ�����ͷ��
	 */
	@At("/uploadPersonIcon")
	public void uploadPersonIcon(@Param("userId") String userId, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8"); // ����response���뷽ʽ
		request.setCharacterEncoding("UTF-8"); // ����request���뷽ʽ

		// �����ļ���Ŀ��������
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// �����ļ��ϴ�·��
		String upload = "../personIcon/"+userId;
		//�˴��ж����ļ��в������򴴽�
		
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
					filename=userId+"."+filename.substring(filename.lastIndexOf(".")+1);
					//�˴����û�ͷ���ַ��Ϣ���浽���ݿ�
					reg.uploadPerIcon(userId, upload+"/"+filename);
					// ��������ʽ�����ϴ��ļ�����������
					InputStream in = item.getInputStream();
					
					File f=new File(upload);
					if(reg.downLoadPerIcon(userId).length()>0){
					if(!f.exists()){
						f.mkdirs();
					}
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
		} catch (FileUploadException e) {
			logger.error("uploadPersonIcon:"+e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * �������ظ���ͷ��
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
			logger.error("downloadPersonIcon:"+e.getMessage());
		}
	}
	
	
	 public void doGet(HttpServletRequest request,  
	            HttpServletResponse response) throws ServletException, IOException {  
	        PrintWriter writer = response.getWriter();  
	 
	        Map<String, String[]> params = request.getParameterMap();
	        Map<String,String> query=new HashMap<>();// ��map���ڽ��մ����������ַ���	        
	        String queryString = "";  
	        for (String key : params.keySet()) {  
	            String[] values = params.get(key);  
	            for (int i = 0; i < values.length; i++) {  
	                String value = new String(values[i].getBytes("ISO-8859-1"),"UTF-8");  
	                query.put(key, value);
	                queryString += key + "=" + value + "&";  
	            }  
	        }  
	        // ȥ�����һ���ո�  
	        queryString = queryString.substring(0, queryString.length() - 1);  
	        character=query;
	    }  
	
}
