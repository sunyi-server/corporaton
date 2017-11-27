package com.corporation.corInfo.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.KeyStore.PrivateKeyEntry;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import com.corporation.corInfo.dao.UserInfoDao;
import com.corporation.corInfo.dao.impl.UserInfoDaoImpl;
import com.corporation.corInfo.domain.UserInfo;
import com.corporation.corInfo.domain.page_list_userInfoVO;
import com.corporation.corInfo.service.UserInfoService;
import com.corporation.corInfo.service.impl.UserInfoServiceImpl;
import com.google.gson.Gson;
import com.mysql.jdbc.TimeUtil;
import com.mysql.jdbc.interceptors.ServerStatusDiffInterceptor;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import util.md5;

public class UserInfoAction extends ActionSupport implements RequestAware, ModelDriven<UserInfo>, Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserInfoService userInfoService;
	private page_list_userInfoVO page_list_userInfo=new page_list_userInfoVO();
	
	public page_list_userInfoVO getPage_list_userInfo() {
		return page_list_userInfo;
	}

	public void setPage_list_userInfo(page_list_userInfoVO page_list_userInfo) {
		this.page_list_userInfo = page_list_userInfo;
	}



	private String user_id;
	
	private UserInfo userInfo = new UserInfo();
	private String user_username;
	
	
	private md5 md5;
	private TimeUtil timeUtil;
	private String user_password;

	private File logo;
	private String logoContentType;
	private String logoFileName;
	
	//分页显示用户
	public String page_list_userInfo() {
		
		page_list_userInfo = userInfoService.listCorInfoByPage(page_list_userInfo);
		ActionContext.getContext().getValueStack().set("page_list_userInfo", page_list_userInfo);

		return "page_list_userInfo";
		

	}


	// 显示所用用户信息
	public String list() {
		request.put("userInfo", userInfoService.getAllUser());
		return "list";
	}

	// 删除用户
	public String delete() {
		System.out.println(user_id);
		userInfoService.delete_User(user_id);
		
		return "delete";
	}

	/* 添加用户 */

	// ajax修改用户回显
	public void inputUser() throws IOException {

		if (user_id != null) {
			model = userInfoService.getId(user_id);
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=utf-8");
		Gson gson = new Gson();
		String result = gson.toJson(model);
		PrintWriter pw = response.getWriter();
		System.out.println(result);
		pw.write(result);
		pw.flush();
		pw.close();

	}
	
	//添加用户
	public String save() throws IOException {
		System.out.println("输出model："+model);
		if (user_id == null) {
			// 创建时间和修改时间
			Date date = new Date();
			String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
			model.setUser_gmt_create(dateStr);
			model.setUser_gmt_modified(dateStr);

			// 生成uuid
			UUID uuid = UUID.randomUUID();
			String s = uuid.toString();
			model.setUser_id(s);
			System.out.println(getUser_password());
			String md5password = md5.GetMD5Code(getUser_password());
			model.setUser_password(md5password);

		}
		
		// 头像上传
		System.out.println("头像是："+logo);
		System.out.println(logoContentType);
		System.out.println(logoFileName);
		 
		System.out.println("生成Id后的model："+model);
		if (user_id == null) {//添加时候头像上传
			if(logo!=null){
				String uuid_real_logo=UUID.randomUUID().toString() + logoFileName.substring(logoFileName.lastIndexOf("."));
				System.out.println("uuid_real_logo是："+uuid_real_logo);
			    String fileName = ServletActionContext.getServletContext().getRealPath("/upload/userLogo/" + uuid_real_logo);
				
				File destFile = new File(fileName);
				FileUtils.copyFile(logo, destFile);
				//System.out.println(fileName);
				model.setUser_logo(uuid_real_logo);
			}else {
				model.setUser_logo("upload/userLogo/headImg.png");
			}
			
		} else {//修改的时候头像修改（当选择修改时）
			if(logo!=null){
				String uuid_real_logo=UUID.randomUUID().toString() + logoFileName.substring(logoFileName.lastIndexOf("."));
				String fileName = ServletActionContext.getServletContext().getRealPath("/upload/" + uuid_real_logo);
				/*String fileName = "E:\\eclipseworks1\\corporation\\WebContent\\upload\\" 
						+ UUID.randomUUID().toString()
						+ logoFileName.substring(logoFileName.lastIndexOf("."));*/
				File destFile = new File(fileName);
				FileUtils.copyFile(logo, destFile);
				//System.out.println(fileName);
				model.setUser_logo(uuid_real_logo);
				UserInfo userInfo = userInfoService.getId(model.getUser_id());
			}else {//（不修改时）
				UserInfo userInfo = userInfoService.getId(model.getUser_id());
				model.setUser_logo(userInfo.getUser_logo());
			}
			
			
		}
		
		Date date = new Date();
		String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
		model.setUser_gmt_modified(dateStr);
		// 保存
		userInfoService.saveOrUpdate(model);
		System.out.println(model);
		return SUCCESS;
	}
		
		
	
	public void prepareSave() {
		System.out.println("用户的id是：" + user_id);

		if (user_id == null) {
			// 添加时user_id为空行创建一个空的UserInfo对象
			model = new UserInfo();
		} else {
			// 修改时根据user_id从数据库中查询所有字段封装到UserInfo中
			model = userInfoService.getId(user_id);
		}
	}
	
	
	//根据user_username查询用户信息
	public String findByUser_name(){
		System.out.println("user_name是："+user_username);
		request.put("oneUser", userInfoService.Userlogin(user_username));
		
		return "listone";
	}
	
	
	
	// 登陆时用户注册
	private String birthday_year;
	private String birthday_month;
	private String birthday_day;
	
	public String getBirthday_year() {
		return birthday_year;
	}

	public void setBirthday_year(String birthday_year) {
		this.birthday_year = birthday_year;
	}

	public String getBirthday_month() {
		return birthday_month;
	}

	public void setBirthday_month(String birthday_month) {
		this.birthday_month = birthday_month;
	}

	public String getBirthday_day() {
		return birthday_day;
	}

	public void setBirthday_day(String birthday_day) {
		this.birthday_day = birthday_day;
	}
	public String registration() {
		// 创建时间和修改时间
		Date date = new Date();
		String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
		model.setUser_gmt_create(dateStr);
		model.setUser_gmt_modified(dateStr);
		
		//设置默认头像
		model.setUser_logo("default.png");
		
		//设置出生日期
		String birthday=birthday_year+"-"+birthday_month+"-"+birthday_day;
		System.out.println("生日是："+birthday);
		model.setUser_birthday(birthday);
		
		// 生成uuid
		UUID uuid = UUID.randomUUID();
		String s = uuid.toString();
		model.setUser_id(s);
		//model.setUser_logo(user_logo);
		System.out.println(getUser_password());
		String md5password = md5.GetMD5Code(getUser_password());
		model.setUser_password(md5password);
		
		//默认角色为普通用户
		String role="0";
		model.setUser_role(role);
		
		// 保存
		userInfoService.saveOrUpdate(model);
		System.out.println(model);
		return "registration";

	}
	
	public void prepareRegistration(){
		model = new UserInfo();
	}
	
	//用户登录
	public String login() {
		UserInfo userInfo = null;
		String page = "fail";
		//System.out.println(userInfo.getUser_username());
		System.out.println(user_username);
		userInfo = userInfoService.Userlogin(user_username);
		String md5passworld=md5.GetMD5Code(user_password);
		if (userInfo != null) {
			if (userInfo.getUser_password().endsWith(md5passworld)) {
				//普通用户登录
				if(userInfo.getUser_role().endsWith("0")) {
					page = "ordinaryuser";
				}
				//超级用户登录
				if(userInfo.getUser_role().endsWith("1")||userInfo.getUser_role().endsWith("2")) {
					page ="superuser";
				}
			
				//
				ActionContext.getContext().getValueStack().set("userInVO", userInfo);
				//
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpSession session = request.getSession();
				if (session.getAttribute("user_username") != null) {
					session.removeAttribute("user_username");
					session.removeAttribute("user_id");
				}
				session.setAttribute("user_username", userInfo.getUser_username());
				session.setAttribute("user_id", userInfo.getUser_id());
				System.out.println(session.getAttribute("user_id"));
			} else {
				HttpServletRequest request = ServletActionContext.getRequest();
				request.setAttribute("msg", "用户或密码错误！");
			}
			

		} else {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("msg", "用户或者密码名错误！");
		}
		return page;
	}
	
	//用户登出
	public String logout(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		if (session.getAttribute("user_username") != null) {
			session.removeAttribute("user_username");
			session.removeAttribute("user_id");
		}
		
		HttpServletResponse response = (HttpServletResponse) ServletActionContext.getResponse();
		PrintWriter out=null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print("<script language=javascript>");
		out.print("top.location.href='"+request.getContextPath()+"/login.jsp'");
		out.print("</script>");
		
		return null;
	}
	
	
	
	
	

	private Map<String, Object> request;

	@Override
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;

	}

	@Override
	public void prepare() throws Exception {
	}

	private UserInfo model;

	@Override
	public UserInfo getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	
	public void setModel(UserInfo model){
		this.model = model;
	}
	
	public Map<String, Object> getRequest(){
		return request;
	}
	
	
	

	public md5 getMd5() {
		return md5;
	}

	public void setMd5(md5 md5) {
		this.md5 = md5;
	}

	public TimeUtil getTimeUtil() {
		return timeUtil;
	}

	public void setTimeUtil(TimeUtil timeUtil) {
		this.timeUtil = timeUtil;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUser_id() {
		return user_id;
	}

	public String getUser_password(){
		return user_password;
	}

	public String getUser_username() {
		return user_username;
	}

	public void setUser_username(String user_username) {
		this.user_username = user_username;
	}

	public File getLogo() {
		return logo;
	}

	public void setLogo(File logo) {
		this.logo = logo;
	}

	public String getLogoContentType() {
		return logoContentType;
	}

	public void setLogoContentType(String logoContentType) {
		this.logoContentType = logoContentType;
	}

	public String getLogoFileName() {
		return logoFileName;
	}

	public void setLogoFileName(String logoFileName) {
		this.logoFileName = logoFileName;
	}

	public UserInfoService getUserInfoService() {
		return userInfoService;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}
	
	

}
