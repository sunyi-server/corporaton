package com.corporation.manager.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.corporation.manager.domain.UserApplyCorVO;
import com.corporation.manager.domain.UserInformatioinVO;
import com.corporation.manager.domain.corporation_information;
import com.corporation.manager.domain.user_information;
import com.corporation.manager.domain.user_into_corporation;
import com.corporation.manager.service.CorporationInforService;
import com.corporation.manager.service.UserApplyCorService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;

import util.TimeUtil;
import util.uuid;

public class UserApplyCorAction {
	private UserApplyCorService userApplyCorService;
	private CorporationInforService corporationInforService;

	// -----------------------------------------------------通过user_id获得所拥有的社团----------------------------------------------
	public void getCorporation() throws IOException {
		System.out.println("进入了我的社团中");
		String user_id = (String) ActionContext.getContext().getSession().get("user_id");
		List<user_into_corporation> list = corporationInforService.getCorporationByUserId(user_id);
		System.out.println("用户所属社团：" + list.get(0).getUser_corporation_include());
		Gson gson = new Gson();
		String result = gson.toJson(list);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter pw = ServletActionContext.getResponse().getWriter();
		System.out.println(result);
		pw.write(result);
		pw.flush();
	}

	// ---------------------------------------------------------进入申请页面------------------------------------------------------------------
	public String skipToApply() {
		String user_id = (String) ActionContext.getContext().getSession().get("user_id");
		UserApplyCorVO userApplyCorVO = userApplyCorService.intoApply(user_id, currPage, queryName);
		ActionContext.getContext().getValueStack().set("applyVO", userApplyCorVO);
		return "skipToApply";
	}

	// ---------------------------------------------------------提交申请------------------------------------------------------------------

	public String applyCorporation() {
		String user_id = (String) ActionContext.getContext().getSession().get("user_id");
		System.out.println("申请的社团id:" + corporation_id);
		userApplyCorService.applyCorporation(user_id, corporation_id);
		return "applySuccess";
	}
	// -----------------------------------------------------审核流程--------------------------------------------------------------

	public String applyProcedure() {

		return null;
	}

	// ----------------------------------------------------获得用户的个人资料--------------------------------------------------------

	public String getUserInfor() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		String user_id = (String) ActionContext.getContext().getSession().get("user_id");
		UserInformatioinVO uiv = userApplyCorService.getUserInfor(user_id);
		ActionContext.getContext().getValueStack().set("userInforVO", uiv);
		return "skipToUserInfor";
	}

	// ------------------------------------------------------修改用户的个人资料-----------------------------------------------------------
	/*
	 * formData.append("user_nickname", user_nickname.value);
	 * formData.append("user_telephone",user_telephone.value);
	 * formData.append("user_address", user_address.value);
	 * formData.append("user_birthday", user_birthday.value);
	 * formData.append("user_introduce", user_introduce.value);
	 */

	public void updateUserInfor() throws IOException {
		System.out.println("用户的文件名");
		String user_id = (String) ActionContext.getContext().getSession().get("user_id");
		user_information ui = userApplyCorService.getUserInfor(user_id).getUserInfor();
		user_information uiN = new user_information();
		if (user_logoFileName != null) {
			System.out.println("头像已经替换" + user_logoFileName);
			String filename = uuid.getUuid() + user_logoFileName.substring(user_logoFileName.indexOf("."));
			String path = ServletActionContext.getServletContext().getRealPath("/upload/userLogo/" + filename);
			File newFile = new File(path);
			FileUtils.copyFile(user_logo, newFile);
			if (ui.getUser_logo() != "defaultLogo.png") {
				File oldFile = new File(
						ServletActionContext.getServletContext().getRealPath("/upload/userLogo/" + ui.getUser_logo()));
				oldFile.delete();
			}
			uiN.setUser_logo(filename);
		} else {
			uiN.setUser_logo(ui.getUser_logo());
			System.out.println("头像未替换");
		}
		uiN.setUser_id(user_id);
		uiN.setUser_address(user_address);
		uiN.setUser_introduce(user_introduce);
		uiN.setUser_nickname(user_nickname);
		uiN.setUser_telephone(user_telephone);
		uiN.setUser_birthday(ui.getUser_birthday());
		uiN.setUser_username(ui.getUser_username());
		uiN.setUser_password(ui.getUser_password());
		uiN.setUser_sex(ui.getUser_sex());
		uiN.setUser_name(ui.getUser_name());
		uiN.setUser_role(ui.getUser_role());
		uiN.setUser_gmt_create(ui.getUser_gmt_create());
		uiN.setUser_gmt_modified(TimeUtil.getStringSecond());
		userApplyCorService.updateUserInfor(uiN);
		System.out.println("修改成功");
	}

	// ------------------------------------------------------下载头像--------------------------------------------------------------------------
	public void downloadImg() throws IOException {
		File file = new File(
				ServletActionContext.getServletContext().getRealPath("/upload/" + fileDirc + "/" + filename));
		OutputStream os = ServletActionContext.getResponse().getOutputStream();
		FileInputStream fis = new FileInputStream(file);
		byte[] b = new byte[1024];
		int length;
		while ((length = fis.read(b)) != -1) {
			os.write(b);
			os.flush();
		}
		os.close();
		fis.close();
	}

	// -------------------------------------------------验证申请的社团名是否重复------------------------------------------------------------------------------

	/*
	 * 0通过 1未通过
	 */
	public void corporationNameIsExsit() throws IOException {
		System.out.println(corporation_name);
		String result = userApplyCorService.corporatonNameIsExsit(corporation_name);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		System.out.println("社团是否存在" + result);
		pw.write(result);
		pw.flush();
		pw.close();

	}

	// --------------------------------------------------提交入驻申请-------------------------------------------------------------------------------

	public String submitApplyCorporation() throws IOException {
		corporation_information ci = new corporation_information();
		ci.setCorporation_apply_time(TimeUtil.getStringDay());
		String user_id = (String) ActionContext.getContext().getSession().get("user_id");
		System.out.println("用户为：" + user_id);
		ci.setCorporation_apply_man(user_id);
		ci.setCorporation_id(uuid.getUuid());
		ci.setCorporation_introduce(corporation_introduce);
		ci.setCorporation_name(corporation_name);
		ci.setCorporation_status("0");
		ci.setCorporation_solidify("0");
		ci.setCorporation_type(corporation_type);
		ci.setCorporation_gmt_create(TimeUtil.getStringSecond());
		ci.setCorporation_gmt_modified(TimeUtil.getStringSecond());
		HttpServletRequest request = ServletActionContext.getRequest();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/"
				+ path + "/";
		if (corporation_logoFileName != null) {
			String filename = uuid.getUuid()
					+ corporation_logoFileName.substring(corporation_logoFileName.indexOf("."));
			String filePath = ServletActionContext.getServletContext()
					.getRealPath("/upload/corporationLogo/" + filename);
			File file = new File(filePath);
			FileUtils.copyFile(corporation_logo, file);
			ci.setCorporation_logo(filename);
		} else {
			ci.setCorporation_logo("defaultLogo.png");
		}
		userApplyCorService.submitApplyCorporation(ci);
		return "applyCorporationSuccess";
	}

	// --------------------------------------------------------------获得用户所有拥有的社团-----------------------------------------------------------------------

	public String getUserHaveCor() {
		String user_id = (String) ActionContext.getContext().getSession().get("user_id");
		System.out.println("获得到了用户进入申请" + user_id);
		List<corporation_information> list = userApplyCorService.getUserHaveCor(user_id);
		ActionContext.getContext().getValueStack().set("corporationInfoVO", list);
		return "skipToUser";
	}

	// ---------------------------------------------------------进入首页---------------------------------------------------------------------

	public String intoIndex() {
		return "intoIndex";
	}

	// -----------------------------------------------------获取请求参数--------------------------------------------------------------------------

	private String corporation_name;
	private String corporation_type;
	private String corporation_introduce;
	private File corporation_logo;
	private String corporation_logoFileName;
	private String corporation_logoContentType;
	private String filename;
	private String fileDirc;
	private File user_logo;
	private String user_logoFileName;
	private String user_logoContentType;
	private String user_introduce;
	private String user_nickname;
	private String user_birthday;
	private String user_telephone;
	private String user_address;
	private String currPage;
	private String queryName;
	private String corporation_id;

	public String getCorporation_name() {
		return corporation_name;
	}

	public void setCorporation_name(String corporation_name) {
		this.corporation_name = corporation_name;
	}

	public String getCorporation_type() {
		return corporation_type;
	}

	public void setCorporation_type(String corporation_type) {
		this.corporation_type = corporation_type;
	}

	public String getCorporation_introduce() {
		return corporation_introduce;
	}

	public void setCorporation_introduce(String corporation_introduce) {
		this.corporation_introduce = corporation_introduce;
	}

	public File getCorporation_logo() {
		return corporation_logo;
	}

	public void setCorporation_logo(File corporation_logo) {
		this.corporation_logo = corporation_logo;
	}

	public String getCorporation_logoFileName() {
		return corporation_logoFileName;
	}

	public void setCorporation_logoFileName(String corporation_logoFileName) {
		this.corporation_logoFileName = corporation_logoFileName;
	}

	public String getCorporation_logoContentType() {
		return corporation_logoContentType;
	}

	public void setCorporation_logoContentType(String corporation_logoContentType) {
		this.corporation_logoContentType = corporation_logoContentType;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFileDirc() {
		return fileDirc;
	}

	public void setFileDirc(String fileDirc) {
		this.fileDirc = fileDirc;
	}

	public File getUser_logo() {
		return user_logo;
	}

	public void setUser_logo(File user_logo) {
		this.user_logo = user_logo;
	}

	public String getUser_logoFileName() {
		return user_logoFileName;
	}

	public void setUser_logoFileName(String user_logoFileName) {
		this.user_logoFileName = user_logoFileName;
	}

	public String getUser_introduce() {
		return user_introduce;
	}

	public void setUser_introduce(String user_introduce) {
		this.user_introduce = user_introduce;
	}

	public String getUser_nickname() {
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}

	public String getUser_birthday() {
		return user_birthday;
	}

	public void setUser_birthday(String user_birthday) {
		this.user_birthday = user_birthday;
	}

	public String getUser_telephone() {
		return user_telephone;
	}

	public void setUser_telephone(String user_telephone) {
		this.user_telephone = user_telephone;
	}

	public String getUser_address() {
		return user_address;
	}

	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}

	public String getCorporation_id() {
		return corporation_id;
	}

	public void setCorporation_id(String corporation_id) {
		this.corporation_id = corporation_id;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public String getCurrPage() {
		return currPage;
	}

	public void setCurrPage(String currPage) {
		this.currPage = currPage;
	}

	public CorporationInforService getCorporationInforService() {
		return corporationInforService;
	}

	public void setCorporationInforService(CorporationInforService corporationInforService) {
		this.corporationInforService = corporationInforService;
	}

	public String getUser_logoContentType() {
		return user_logoContentType;
	}

	public void setUser_logoContentType(String user_logoContentType) {
		this.user_logoContentType = user_logoContentType;
	}

	public UserApplyCorService getUserApplyCorService() {
		return userApplyCorService;
	}

	public void setUserApplyCorService(UserApplyCorService userApplyCorService) {
		this.userApplyCorService = userApplyCorService;
	}

}
