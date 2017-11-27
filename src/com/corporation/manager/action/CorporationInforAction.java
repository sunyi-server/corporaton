package com.corporation.manager.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.struts2.ServletActionContext;

import com.corporation.manager.domain.CorporationDepartmentManVO;
import com.corporation.manager.domain.CorporationMemberInforVO;
import com.corporation.manager.domain.UserApplyManVO;
import com.corporation.manager.domain.corporation_department;
import com.corporation.manager.domain.corporation_information;
import com.corporation.manager.domain.member_information;
import com.corporation.manager.service.CorporationInforService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import util.TimeUtil;
import util.uuid;

/*
 * 会员信息管理类
 * 
 */
public class CorporationInforAction extends ActionSupport {
	private CorporationInforService corporationInforService;

	public CorporationInforService getCorporationInforService() {
		return corporationInforService;
	}

	public void setCorporationInforService(CorporationInforService corporationInforService) {
		this.corporationInforService = corporationInforService;
	}

	// ---------------------------------------------显示所有成员--------------------------------------------------

	public String showMember() throws UnsupportedEncodingException {
		// 检查权限

		System.out.println(timeSort);
		System.out.println(member_corporation);
		System.out.println(queryName);
		CorporationMemberInforVO memberVO = corporationInforService.getMemberByCondition(queryStartTime, queryEndTime,
				queryName, timeSort, currPage, member_corporation, queryDepartment, queryMemberStatus);
		memberVO.setCurrCorporation(member_corporation);
		ActionContext.getContext().getValueStack().set("memberVO", memberVO);
		return "skipToShowMember";
	}

	// ------------------------------------------通过Id获得会员------------------------------------------------------------

	public void getMemberById() throws IOException {
		member_information mi = corporationInforService.getMemberById(memberId);
		Gson gson = new Gson();
		String jsonMi = gson.toJson(mi);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		System.out.println(jsonMi);
		pw.write(jsonMi);
		pw.flush();
		System.out.println(jsonMi);
	}

	// ------------------------------------------修改会员信息------------------------------------------------------------
	public void updateMemberInfo() throws IOException {
		member_information mi = new member_information();
		if (member_role == "管理员") {
			member_role = "1";
		} else {
			member_role = "0";
		}
		mi.setMember_birthday(member_birthday);
		mi.setMember_class(member_class);
		mi.setMember_college(member_college);
		mi.setMember_corporation(member_corporation);
		mi.setMember_department(member_department);
		mi.setMember_gmt_create(member_gmt_create);
		mi.setMember_gmt_modified(TimeUtil.getStringSecond());
		mi.setMember_home_address(member_home_address);
		mi.setMember_id(member_id);
		mi.setMember_join_time(member_join_time);
		mi.setMember_major(member_major);
		mi.setMember_name(member_name);
		mi.setMember_num(member_num);
		mi.setMember_position(member_position);
		mi.setMember_qq(member_qq);
		mi.setMember_quit_time(member_quit_time);
		mi.setMember_role(member_role);
		mi.setMember_sex(member_sex);
		mi.setMember_status(member_status);
		mi.setMember_telephone(member_telephone);
		mi.setMember_user(member_user);
		System.out.println(member_id);
		corporationInforService.updateMemberInfo(mi);
		HttpServletRequest request = ServletActionContext.getRequest();
		member_corporation = URLEncoder.encode(member_corporation, "UTF-8"); // 对字符串重新编码为UTF-8类型
		String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/"
				+ "manager/manager_showMember?queryMemberStatus=&queryDepartment=&member_corporation="
				+ member_corporation
				+ "&queryStartTime=0000-00-00&queryEndTime=9999-99-99&queryName=&timeSort=desc&currPage=1";
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().sendRedirect(path);

	}
	// ------------------------------------------删除成员------------------------------------------------------------

	public void deleteMember() throws UnsupportedEncodingException {

		System.out.println("进入了删除" + memberId);
		String result = corporationInforService.deleteMember(memberId, member_corporation);
		this.member_corporation = URLDecoder.decode(member_corporation, "gb2312");
		if (result == "删除成功" || "删除成功".equals(result)) {

		} else {

		}

	}

	// ------------------------------------------------------添加会员-----------------------------------------------
	public String addNewMember() throws UnsupportedEncodingException {
		member_information mi = new member_information();
		System.out.println(member_status + "用户的状态是");
		mi.setMember_birthday(member_birthday);
		mi.setMember_class(member_class);
		mi.setMember_college(member_college);
		mi.setMember_corporation(member_corporation);
		mi.setMember_department(member_department);
		mi.setMember_gmt_create(TimeUtil.getStringSecond());
		mi.setMember_gmt_modified(TimeUtil.getStringSecond());
		mi.setMember_home_address(member_home_address);
		mi.setMember_id(uuid.getUuid());
		mi.setMember_join_time(member_join_time);
		mi.setMember_major(member_major);
		mi.setMember_name(member_name);
		mi.setMember_num(member_num);
		mi.setMember_position(member_position);
		mi.setMember_qq(member_qq);
		mi.setMember_quit_time(member_quit_time);
		mi.setMember_sex(member_sex);
		mi.setMember_role("0");
		mi.setMember_status(member_status);
		mi.setMember_telephone(member_telephone);
		mi.setMember_user("");
		corporationInforService.addNewMember(mi);
		return "addMemberSuccess";
	}

	// ---------------------------------------------------------------导出excel表-------------------------------------------------------------------------

	public void exportExcel() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, IOException {

		// 获得表内数据
		System.out.println("会员状态：" + queryMemberStatus);
		System.out.println("会员性别：" + queryMemberSex);
		List<member_information> list = corporationInforService.getMemberByCondition(queryStartTime, queryEndTime,
				member_corporation, queryDepartment, queryMemberSex, queryMemberStatus);

		System.out.println(queryStartTime + queryEndTime + queryDepartment + queryMemberSex + queryMemberStatus
				+ member_corporation);
		// 创建一个workboot相当于一张excel表
		HSSFWorkbook wb = new HSSFWorkbook();
		// 创建一个sheet(纸)
		HSSFSheet sheet = wb.createSheet("会员信息表一");
		// 在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 创建一个居中格式
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		String[] realColumn = new String[selectedColumn.length];

		// --------------------------------------为第一行的列名赋值
		for (int i = 0; i < realColumn.length; i++) {
			switch (selectedColumn[i]) {
			case "Member_telephone":
				realColumn[i] = "会员电话";
				break;
			case "Member_num":
				realColumn[i] = "会员学号";
				break;
			case "Member_birthday":
				realColumn[i] = "会员生日";
				break;
			case "Member_college":
				realColumn[i] = "会员学院";
				break;
			case "Member_major":
				realColumn[i] = "会员专业";
				break;
			case "Member_class":
				realColumn[i] = "会员班级";
				break;
			case "Member_department":
				realColumn[i] = "会员部门";
				break;
			case "Member_position":
				realColumn[i] = "会员职务";
				break;
			case "Member_home_address":
				realColumn[i] = "会员家庭住址";
				break;
			case "Member_qq":
				realColumn[i] = "会员qq";
				break;
			case "Member_join_time":
				realColumn[i] = "会员加入时间";
				break;
			case "Member_quit_time":
				realColumn[i] = "会员退出时间";
				break;
			case "Member_status":
				realColumn[i] = "会员状态";
				break;
			case "Member_name":
				realColumn[i] = "会员姓名";
				break;
			case "Member_sex":
				realColumn[i] = "会员性别";
				break;
			}
		}

		// --------------------------创建第一行
		HSSFCell cell;
		for (int j = 0; j < realColumn.length; j++) {
			// 设置列宽35.7表示单位 150表示像素 第一个参数表示第几列
			sheet.setColumnWidth(j, (int) ((35.7) * 150));
			cell = row.createCell((short) j);
			cell.setCellValue(realColumn[j]);
			cell.setCellStyle(style);
		}

		// ----------------------------利用反射获得list集合中的数据(由于是自选择列所以在运行时调用对应方法)
		Class miC = member_information.class;
		for (int i = 0; i < list.size(); i++) {
			// ---------------创建下一行
			row = sheet.createRow((int) i + 1);

			member_information mi = list.get(i);
			for (int j = 0; j < selectedColumn.length; j++) {

				// 通过反射获得对应的方法
				Method method = miC.getMethod("get" + selectedColumn[j]);
				cell = row.createCell(j);
				if ("Member_status".equals(selectedColumn[j])) {
					System.out.println(selectedColumn[j]);
					if ("1".equals(method.invoke(mi))) {
						cell.setCellValue("在会");
					} else {
						cell.setCellValue("已退会");
					}
				} else if ("Member_sex".equals(selectedColumn[j])) {
					if ("1".equals(method.invoke(mi))) {
						cell.setCellValue("男");
					} else {
						cell.setCellValue("女");
					}
				} else {
					// 通过反射调用方法并获得值
					cell.setCellValue((String) method.invoke(mi));
				}
				cell.setCellStyle(style);
			}
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();

		// ----------------------------------将文件下载到本地临时文件目录
		String fileName = uuid.getUuid() + ".xls";
		String path = ServletActionContext.getServletContext().getRealPath("/upload/excel/" + fileName);
		System.out.println(path);
		File file = new File(path);
		if (!file.exists()) {
			System.out.println("文件不存在");
			file.createNewFile();
		}
		FileOutputStream out = new FileOutputStream(file);
		wb.write(out);
		out.close();
		response.getWriter().write(fileName);
		response.getWriter().flush();
		response.getWriter().close();

	}

	// ---------------------------------------------------------下载生成的excel文件--------------------------------------------------------------
	public void downExcel() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		String fileName = ServletActionContext.getServletContext().getRealPath("/upload/excel/" + filename);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=" + filename);
		File file = new File(fileName);
		FileInputStream fi = new FileInputStream(file);
		byte[] b = new byte[1024];
		int length;
		OutputStream os = response.getOutputStream();
		while ((length = fi.read(b)) != -1) {
			os.write(b);
			os.flush();
		}
		os.close();
		fi.close();
		// ------------------------------关闭到文件的各种流用于删除文件
		if (file.delete()) {
			System.out.println("删除成功");
		} else {
			System.out.println("删除失败");
		}

	}

	// ---------------------------------------------------------获取待审核用户-----------------------------------------------------
	public String getApplyUser() {

		UserApplyManVO userManVO = corporationInforService.getUserApplyManVO(currPage, timeSort, member_corporation);
		Gson gson = new Gson();
		String result = gson.toJson(userManVO);
		System.out.println(result);
		ActionContext.getContext().getValueStack().set("userApplyVO", userManVO);
		return "getApplyUserSuccess";
	}

	// ----------------------------------------------------------驳回申请----------------------------------------------
	public void rejectApply() throws IOException {
		String currCorporation = corporationInforService.rejectApply(into_id);
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		// URLEncoder.encode(currCorporation,
		// "utf-8")将URL中文参数编码成指定格式防止get请求中的乱码
		response.sendRedirect(basePath + "/manager/manager_getApplyUser?member_corporation="
				+ URLEncoder.encode(currCorporation, "utf-8") + "&timeSort=desc&currPage=1");
	}

	// ---------------------------------------------------------获得没有绑定的会员信息---------------------------------------------
	public void getMemberByUser() throws IOException {
		List<member_information> mi = corporationInforService.getMemberByUser(member_corporation);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		PrintWriter pw = ServletActionContext.getResponse().getWriter();
		Gson gson = new Gson();
		String result = gson.toJson(mi);
		pw.write(result);
		pw.flush();
		pw.close();
	}

	// --------------------------------------------------------------同意申请-------------------------------------------------------------

	public void agreeApply() throws UnsupportedEncodingException, IOException {

		corporationInforService.agreeApply(into_id, currMember);

		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		// URLEncoder.encode(currCorporation,
		// "utf-8")将URL中文参数编码成指定格式防止get请求中的乱码
		response.sendRedirect(basePath + "/manager/manager_getApplyUser?member_corporation="
				+ URLEncoder.encode(member_corporation, "utf-8") + "&timeSort=desc&currPage=1");
	}
	// ------------------------------------------------------通过社团名获得部门--------------------------------------------------

	public String skipToDepartment() {
		CorporationDepartmentManVO dpv = corporationInforService.getDepartmentVO(member_corporation);
		ActionContext.getContext().getValueStack().set("departmentVO", dpv);
		return "skipToDepartment";
	}
	// -----------------------------------------验证部门名-------------------------------------------------------------

	public void validateDepartment() throws IOException {
		String result = corporationInforService.validateDepartment(member_corporation, department_name);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.write(result);
		pw.flush();
		pw.close();
	}

	// --------------------------------------添加部门到数据库---------------------------------------------------------------
	public void addDepartment() throws UnsupportedEncodingException, IOException {

		corporation_department cd = new corporation_department();
		cd.setDepartment_include(member_corporation);
		cd.setDepartment_introduce(department_introduce);
		cd.setDepartment_name(department_name);
		corporationInforService.addDepartment(cd);
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		// URLEncoder.encode(currCorporation,
		// "utf-8")将URL中文参数编码成指定格式防止get请求中的乱码
		response.sendRedirect(basePath + "/manager/manager_skipToDepartment?member_corporation="
				+ URLEncoder.encode(member_corporation, "utf-8"));
	}

	// -----------------------------------------------获得部门通过部门id--------------------------------------------
	public void getDepartmentById() throws IOException {
		corporation_department cd = corporationInforService.getDepartmentById(department_id);
		Gson gson = new Gson();
		String result = gson.toJson(cd);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		System.out.println(result);
		pw.write(result);
		pw.flush();
		pw.close();

	}

	// ----------------------------------------------修改部门信息----------------------------------------------------

	public void updateDepartment() throws UnsupportedEncodingException, IOException {
		corporationInforService.updateDepartment(department_id, department_introduce);
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		// URLEncoder.encode(currCorporation,
		// "utf-8")将URL中文参数编码成指定格式防止get请求中的乱码
		response.sendRedirect(basePath + "/manager/manager_skipToDepartment?member_corporation="
				+ URLEncoder.encode(member_corporation, "utf-8"));
	}
	// --------------------------------------------删除部门------------------------------------------------------------

	public void deleteDepartment() throws UnsupportedEncodingException, IOException {
		System.out.println("部门id:" + department_id + "社团名:" + member_corporation);
		corporationInforService.deleteDepartment(department_id, member_corporation);
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		// URLEncoder.encode(currCorporation,
		// "utf-8")将URL中文参数编码成指定格式防止get请求中的乱码
		response.sendRedirect(basePath + "/manager/manager_skipToDepartment?member_corporation="
				+ URLEncoder.encode(member_corporation, "utf-8"));
	}

	// ----------------------------------------------------展示会员信息(管理员模块)--------------------------------------------------------
	public String showMemberManager() throws UnsupportedEncodingException {
		// 检查权限

		System.out.println(timeSort);
		System.out.println(member_corporation);
		System.out.println(queryName);
		CorporationMemberInforVO memberVO = corporationInforService.getMemberByCondition(queryStartTime, queryEndTime,
				queryName, timeSort, currPage, member_corporation, queryDepartment, queryMemberStatus);
		memberVO.setCurrCorporation(member_corporation);
		ActionContext.getContext().getValueStack().set("memberVO", memberVO);
		return "skipToShowMemberManager";
	}

	// -----------------------------------------------改变会员权限——---------------------------------------------------------------
	public void changeMemberRole() {
		corporationInforService.changeMemberRole(member_id, member_role);
	}

	// ----------------------------------------------得到社团信息------------------------------------------------------------
	public String getCoporationInfor() {
		corporation_information ci = corporationInforService.getCorporationInfor(member_corporation);
		System.out.println("社团头像：" + ci.getCorporation_logo());
		ActionContext.getContext().getValueStack().set("corporationInfo", ci);
		return "skipToCorporationInfor";
	}

	// --------------------------------------社团信息展示-----------------------------------------------------------------------

	public String getCoporationInforShow() {
		corporation_information ci = corporationInforService.getCorporationInfor(member_corporation);
		System.out.println("社团头像：" + ci.getCorporation_logo());
		ActionContext.getContext().getValueStack().set("corporationInfo", ci);
		return "skipToCorporationInforShow";
	}

	// ------------------------------------------------上传临时头像-----------------------------------------------------------------

	public void uploadTemporaryLogo() throws IOException {
		String temp = "upload/corporationLogoTemp/" + UUID.randomUUID().toString()
				+ logoTempFileName.substring(logoTempFileName.lastIndexOf("."));
		String fileName = ServletActionContext.getServletContext().getRealPath("/" + temp);
		System.out.println(fileName);
		File destFile = new File(fileName);
		destFile.createNewFile();
		System.out.println(destFile.exists());
		System.out.println("文件大小：" + logoTemp.length());
		FileUtils.copyFile(logoTemp, destFile);
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		PrintWriter pw = response.getWriter();
		pw.write(basePath + temp);
		System.out.println(basePath + temp);
		pw.flush();
		pw.close();
	}

	// ----------------------------------------------------修改社团信息------------------------------------------------------
	public void updateCorInfo() throws IOException, InterruptedException {
		System.out.println("社团名称：" + member_corporation);
		corporation_information ci = corporationInforService.getCorporationInfor(member_corporation);
		if (corporation_logoFileName != null) {
			if (ci.getCorporation_logo() == "defaultLogo.png" || "defaultLogo.png".equals(ci.getCorporation_logo())) {

			} else {
				String oldFilename = ServletActionContext.getServletContext()
						.getRealPath("/upload/corporationLogo/" + ci.getCorporation_logo());
				System.out.println("旧头像：" + oldFilename);
				File file = new File(oldFilename);
				if (file.exists()) {
					file.delete();
				}
			}
			String filename = UUID.randomUUID().toString()
					+ corporation_logoFileName.substring(corporation_logoFileName.lastIndexOf("."));
			System.out.println("新的地址" + filename);
			String temp = "upload/corporationLogo/" + filename;
			String fileName = ServletActionContext.getServletContext().getRealPath("/" + temp);
			File destFile = new File(fileName);
			FileUtils.copyFile(corporation_logo, destFile);
			ci.setCorporation_logo(filename);
		}
		ci.setCorporation_apply_time(corporation_apply_time);
		ci.setCorporation_introduce(corporation_introduce);
		System.out.println("新的介绍" + corporation_introduce);
		corporationInforService.updateCorInfo(ci);
		Thread.sleep(1000);
	}

	// ----------------------------------------------分页获得公告-----------------------------------------------

	/*
	 * @获得条件
	 * 
	 * 1.通过公告名搜索
	 * 
	 * 2.通过时间筛选 发布时间
	 * 
	 * 3.通过时间进行排序
	 */

	public String getAnnouncementByPage() {

		return "getSuccess";
	}

	
	private String queryStartTime;
	private String queryEndTime;
	private String queryName;
	private String timeSort;
	private File corporation_logo;
	private String corporation_logoFileName;
	private String corporation_logoContentType;
	private String corporation_id;
	private String corporation_introduce;
	private String corporation_apply_time;
	private File logoTemp;
	private String logoTempFileName;
	private String logoTempContentType;
	private String department_id;
	private String department_name;
	private String department_introduce;
	private String currUser;
	private String currMember;
	private String into_id;
	private String corporationName;
	private String filename;
	private String[] selectedColumn;
	private String memberId;
	private String queryDepartment;
	private String queryMemberStatus;
	private String queryMemberSex;
	private int currPage;
	private String corporation_name;
	private String member_name;
	private String member_telephone;
	private String member_num;
	private String member_college;
	private String member_major;
	private String member_class;
	private String member_department;
	private String member_role;
	private String member_position;
	private String member_home_address;
	private String member_qq;
	private String member_join_time;
	private String member_quit_time;
	private String member_status;
	private String member_birthday;
	private String member_sex;
	private String member_id;
	private String member_gmt_create;
	private String member_gmt_modified;
	private String member_user;
	private String member_corporation;

	public String getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}

	public String getCurrUser() {
		return currUser;
	}

	public void setCurrUser(String currUser) {
		this.currUser = currUser;
	}

	public String getCurrMember() {
		return currMember;
	}

	public void setCurrMember(String currMember) {
		this.currMember = currMember;
	}

	public String getInto_id() {
		return into_id;
	}

	public void setInto_id(String into_id) {
		this.into_id = into_id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String[] getSelectedColumn() {
		return selectedColumn;
	}

	public void setSelectedColumn(String[] selectedColumn) {
		this.selectedColumn = selectedColumn;
	}

	public String getQueryMemberSex() {
		return queryMemberSex;
	}

	public void setQueryMemberSex(String queryMemberSex) {
		this.queryMemberSex = queryMemberSex;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getDepartment_introduce() {
		return department_introduce;
	}

	public void setDepartment_introduce(String department_introduce) {
		this.department_introduce = department_introduce;
	}

	public String getMember_gmt_create() {
		return member_gmt_create;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public void setMember_gmt_create(String member_gmt_create) {
		this.member_gmt_create = member_gmt_create;
	}

	public String getMember_gmt_modified() {
		return member_gmt_modified;
	}

	public void setMember_gmt_modified(String member_gmt_modified) {
		this.member_gmt_modified = member_gmt_modified;
	}

	public String getMember_user() {
		return member_user;
	}

	public void setMember_user(String member_user) {
		this.member_user = member_user;
	}

	public String getMember_quit_time() {
		return member_quit_time;
	}

	public void setMember_quit_time(String member_quit_time) {
		this.member_quit_time = member_quit_time;
	}

	public String getMember_sex() {
		return member_sex;
	}

	public File getLogoTemp() {
		return logoTemp;
	}

	public void setLogoTemp(File logoTemp) {
		this.logoTemp = logoTemp;
	}

	public String getLogoTempFileName() {
		return logoTempFileName;
	}

	public void setLogoTempFileName(String logoTempFileName) {
		this.logoTempFileName = logoTempFileName;
	}

	public String getLogoTempContentType() {
		return logoTempContentType;
	}

	public void setLogoTempContentType(String logoTempContentType) {
		this.logoTempContentType = logoTempContentType;
	}

	public void setMember_sex(String member_sex) {
		this.member_sex = member_sex;
	}

	public String getMember_birthday() {
		return member_birthday;
	}

	public void setMember_birthday(String member_birthday) {
		this.member_birthday = member_birthday;
	}

	public String getCorporation_name() {
		return corporation_name;
	}

	public void setCorporation_name(String corporation_name) {
		this.corporation_name = corporation_name;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public String getMember_telephone() {
		return member_telephone;
	}

	public void setMember_telephone(String member_telephone) {
		this.member_telephone = member_telephone;
	}

	public String getMember_num() {
		return member_num;
	}

	public void setMember_num(String member_num) {
		this.member_num = member_num;
	}

	public String getMember_college() {
		return member_college;
	}

	public void setMember_college(String member_college) {
		this.member_college = member_college;
	}

	public String getMember_major() {
		return member_major;
	}

	public void setMember_major(String member_major) {
		this.member_major = member_major;
	}

	public String getMember_class() {
		return member_class;
	}

	public void setMember_class(String member_class) {
		this.member_class = member_class;
	}

	public String getMember_department() {
		return member_department;
	}

	public void setMember_department(String member_department) {
		this.member_department = member_department;
	}

	public String getMember_role() {
		return member_role;
	}

	public void setMember_role(String member_role) {
		this.member_role = member_role;
	}

	public String getMember_position() {
		return member_position;
	}

	public void setMember_position(String member_position) {
		this.member_position = member_position;
	}

	public String getMember_home_address() {
		return member_home_address;
	}

	public void setMember_home_address(String member_home_address) {
		this.member_home_address = member_home_address;
	}

	public String getMember_qq() {
		return member_qq;
	}

	public void setMember_qq(String member_qq) {
		this.member_qq = member_qq;
	}

	public String getMember_join_time() {
		return member_join_time;
	}

	public void setMember_join_time(String member_join_time) {
		this.member_join_time = member_join_time;
	}

	public String getMember_status() {
		return member_status;
	}

	public void setMember_status(String member_status) {
		this.member_status = member_status;
	}

	public String getMember_corporation() {
		return member_corporation;
	}

	public String getQueryDepartment() {
		return queryDepartment;
	}

	public void setQueryDepartment(String queryDepartment) {
		this.queryDepartment = queryDepartment;
	}

	public String getQueryMemberStatus() {
		return queryMemberStatus;
	}

	public void setQueryMemberStatus(String queryMemberStatus) {
		this.queryMemberStatus = queryMemberStatus;
	}

	public void setMember_corporation(String member_corporation) {
		this.member_corporation = member_corporation;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getQueryStartTime() {
		return queryStartTime;
	}

	public void setQueryStartTime(String queryStartTime) {
		this.queryStartTime = queryStartTime;
	}

	public String getQueryEndTime() {
		return queryEndTime;
	}

	public void setQueryEndTime(String queryEndTime) {
		this.queryEndTime = queryEndTime;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public String getTimeSort() {
		return timeSort;
	}

	public void setTimeSort(String timeSort) {
		this.timeSort = timeSort;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public String getCorporationName() {
		return corporationName;
	}

	public void setCorporationName(String corporationName) {
		this.corporationName = corporationName;
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

	public String getCorporation_id() {
		return corporation_id;
	}

	public void setCorporation_id(String corporation_id) {
		this.corporation_id = corporation_id;
	}

	public String getCorporation_introduce() {
		return corporation_introduce;
	}

	public void setCorporation_introduce(String corporation_introduce) {
		this.corporation_introduce = corporation_introduce;
	}

	public String getCorporation_apply_time() {
		return corporation_apply_time;
	}

	public void setCorporation_apply_time(String corporation_apply_time) {
		this.corporation_apply_time = corporation_apply_time;
	}

}
