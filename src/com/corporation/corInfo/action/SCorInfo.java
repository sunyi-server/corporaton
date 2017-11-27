package com.corporation.corInfo.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.corporation.corInfo.domain.CorInfoDTO;
import com.corporation.corInfo.domain.corporation_message;
import com.corporation.corInfo.domain.page_list_corInfoVO;
import com.corporation.corInfo.domain.searchCorInfoListDTO;
import com.corporation.corInfo.service.SCorInfoService;
import com.corporation.manager.domain.corporation_information;
import com.corporation.manager.domain.member_information;
import com.corporation.manager.domain.user_information;
import com.corporation.manager.domain.user_into_corporation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import util.TimeUtil;

/*
 * @author 刘化生
 */
@SuppressWarnings("serial")
public class SCorInfo extends ActionSupport {
	private SCorInfoService sCorInfoService;
	private page_list_corInfoVO page_list_corInfo;
	private searchCorInfoListDTO searchCorInfoList;
	private String option;
	private corporation_information corInfo;
	private String imgName;
	private InputStream inputStream;
	private user_into_corporation userintocor = new user_into_corporation();
	/*
	 * 必须要有，会自动寻找这两个
	 */
	private File corporation_logo;
	private String corporation_logoFileName;
	private String corporation_logoContentType;

	private HttpServletResponse http_response;

	private HttpServletRequest http_request;

	private String corporation_name;
	private String message_id;
	private String message_content;

	public void setCorporation_name(String corporation_name) {
		this.corporation_name = corporation_name;
	}

	/*
	 * 分页查询所有社团信息的方法
	 *
	 */
	public String page_list_corInfo() {

		if (searchCorInfoList != null) {
			System.out.println(searchCorInfoList);
			page_list_corInfo.setSearch(searchCorInfoList);

		}
		page_list_corInfo = sCorInfoService.listCorInfoByPage(page_list_corInfo);
		ActionContext.getContext().getValueStack().set("page_list_corInfo", page_list_corInfo);

		return "page_list_corInfo";

	}

	/*
	 * 查询待审核的社团
	 */
	public String page_list_checkCorInfo() {

		if (searchCorInfoList != null) {

			System.out.println(searchCorInfoList);
			page_list_corInfo.setSearch(searchCorInfoList);

		}
		page_list_corInfo = sCorInfoService.listCorInfoByPage(page_list_corInfo);
		ActionContext.getContext().getValueStack().set("page_list_corInfo", page_list_corInfo);

		return "page_check_corInfo";

	}

	/*
	 * 查询备注未通过入驻的社团信息
	 */
	public String page_list_message() {
		if (searchCorInfoList != null) {
			System.out.println(searchCorInfoList);
			page_list_corInfo.setSearch(searchCorInfoList);

		}
		page_list_corInfo = sCorInfoService.listMessageByPage(page_list_corInfo);
		ActionContext.getContext().getValueStack().set("page_list_corInfo", page_list_corInfo);
		return "page_list_message";
	}

	/*
	 * 添加社团信息的方法
	 */
	public String page_create_corInfo() {

		System.out.println(option);
		ActionContext.getContext().getValueStack().set("corInfo", corInfo);
		ActionContext.getContext().getValueStack().set("option", option);

		return "page_create_corInfo";

	}

	/*
	 * 社团Logo
	 */

	public String getCorporationLogo() throws FileNotFoundException {
		if (imgName.equals("") || imgName == null) {
			imgName = "defaultLogo.png";
		}
		String filepath = ServletActionContext.getServletContext().getRealPath("/upload/corporationLogo/" + imgName);
		File file = new File(filepath);
		try {
			inputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			file = new File(filepath);
			String notFoundPath = ServletActionContext.getServletContext()
					.getRealPath("/upload/corporationLogo/defaultLogo.png");
			inputStream = new FileInputStream(notFoundPath);
		}
		return "getImg";

	}

	/*
	 * 保存社团信息
	 */
	public void page_save_corInfo() {

		System.out.println("欢迎来到保存社团信息的方法。");
		System.out.println(corInfo);
		/*
		 * 存储图片
		 */
		if (corporation_logo != null) {
			if (corporation_logo.length() <= 5242800) {

				String filePath;

				String fileName = UUID.randomUUID().toString()
						+ corporation_logoFileName.substring(corporation_logoFileName.lastIndexOf("."));

				filePath = ServletActionContext.getServletContext().getRealPath("/upload/corporationLogo/" + fileName);

				System.out.println("改名后fileName:" + fileName);

				corInfo.setCorporation_logo(fileName);

				File newFile = new File(filePath);

				try {

					FileUtils.copyFile(corporation_logo, newFile);

				} catch (IOException e) {

					e.printStackTrace();
				}

			}
		} else {
			corInfo.setCorporation_logo("defaultLogo.png");
		}

		/*
		 * 存储数据
		 */
		sCorInfoService.saveCorInfo(corInfo);

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		try {

			http_response.setContentType("text/html;charset=utf-8");

			http_response.getWriter().write(gson.toJson("success"));

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	/*
	 * 删除社团信息
	 */
	public void deleteCorInfo() {
		CorInfoDTO corInfoDTO = sCorInfoService.getCorInfoByCorID(corInfo);
		// 删除对应的社团时应该删除保存文件夹中的社团logo
		if (!corInfoDTO.getCorInfo().getCorporation_logo().equals("defaultLogo.png")) {
			String filePath = ServletActionContext.getServletContext()
					.getRealPath("/upload/corporationLogo/" + corInfoDTO.getCorInfo().getCorporation_logo());
			File oldBimg = new File(filePath);
			oldBimg.delete();
		}
		sCorInfoService.removeCorInfoByID(corInfo);
		// 删除未通过社团时把未通过社团的备注信息删除
		if (sCorInfoService.getMessageByCorporationid(corInfo) != null) {
			sCorInfoService.removeMessageByCorID(corInfo);
		}
		// 删除通过的社团，删除user_into_corporation里面相应的数据
		corInfo.setCorporation_apply_man(corInfoDTO.getCorInfo().getCorporation_apply_man());
		if (sCorInfoService.getUserIntoCorByUserId(corInfo) != null) {
			sCorInfoService.removeUserIntoCorByUserID(corInfo);
		}
		// 删除通过的社团，删除member_information里面的相应的数据
		if (sCorInfoService.getMemberInfoByUserId(corInfo) != null) {
			sCorInfoService.removeMemberInfoByUserId(corInfo);
		}

		try {

			http_response.setContentType("text/html;charset=utf-8");

			http_response.getWriter().write("success");

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	/*
	 * 删除社团审核信息表
	 */

	public void deleteMessage() {
		sCorInfoService.removeMessageByMessageID(message);
		try {

			http_response.setContentType("text/html;charset=utf-8");

			http_response.getWriter().write("success");

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	/*
	 * 点击修改社团信息，通过corporation_id来获取相应的社团信息，返回创建社团信息页面
	 *
	 */
	public String updatecorInfoPage() {

		CorInfoDTO corInfoDTO = sCorInfoService.getCorInfoByCorID(corInfo);

		ActionContext.getContext().getValueStack().set("corInfoDTO", corInfoDTO);

		ActionContext.getContext().getValueStack().set("option", "update");

		return "updatecorInfoPage";

	}

	/*
	 * 更新社团信息
	 */
	public void update_corInfo() {
		System.out.println("------------update_corInfo-----------");
		System.out.println(corporation_logo);
		System.out.println(corInfo);
		CorInfoDTO corInfoDTO = sCorInfoService.getCorInfoByCorID(corInfo);
		if (corporation_logo != null) {
			if (corporation_logo.length() <= 50 * 1024 * 1024) {

				if (!corInfoDTO.getCorInfo().getCorporation_logo().equals("defaultLogo.png")) {
					String filePath = ServletActionContext.getServletContext()
							.getRealPath("/upload/corporationLogo/" + corInfoDTO.getCorInfo().getCorporation_logo());
					File oldBimg = new File(filePath);
					oldBimg.delete();
				}
				String filePath;

				String fileName = UUID.randomUUID().toString()
						+ corporation_logoFileName.substring(corporation_logoFileName.lastIndexOf("."));
				filePath = ServletActionContext.getServletContext().getRealPath("/upload/corporationLogo/" + fileName);
				System.out.println("改名后fileName:" + fileName);
				corInfo.setCorporation_logo(fileName);
				File newFile = new File(filePath);
				try {
					FileUtils.copyFile(corporation_logo, newFile);
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else {
				corInfo.setCorporation_logo(corInfoDTO.getCorInfo().getCorporation_logo());
			}

		} else {
			corInfo.setCorporation_logo(corInfoDTO.getCorInfo().getCorporation_logo());
		}
		sCorInfoService.updateCorInfoAllByID(corInfo);
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		try {

			http_response.setContentType("text/html;charset=utf-8");

			http_response.getWriter().write(gson.toJson("success"));

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private user_information userInfo = new user_information();

	public user_information getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(user_information userInfo) {
		this.userInfo = userInfo;
	}

	private member_information memberInfo = new member_information();

	public member_information getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(member_information memberInfo) {
		this.memberInfo = memberInfo;
	}

	/*
	 * 同意社团申请入驻，将社团状态改为已通过状态， 1.更改社团状态，将社团负责人改为申请的用户，
	 * 2.将申请的用户加入到社团中(user_into_corporation) 3.将申请用户写入到社团会员中并设置改会员角色是社团负责人
	 */
	public void updatecorInfoStatusById() {

		CorInfoDTO corInfoDTO = sCorInfoService.getCorInfoByCorID(corInfo);
		// 1.更改社团状态，将社团负责人改为申请的用户，
		corInfo.setCorporation_leader_id(corInfoDTO.getCorInfo().getCorporation_apply_man());
		sCorInfoService.updateAgreecorInfoStatusById(corInfo);

		// 2.将申请的用户加入到社团中(user_into_corporation)
		userintocor.setUser_corporation_include(corInfoDTO.getCorInfo().getCorporation_name());
		System.out.println("rrrr" + userintocor.getUser_corporation_include());
		userintocor.setInto_status("2");
		userintocor.setInto_man(corInfo.getCorporation_leader_id());

		sCorInfoService.saveUserIntoCor(userintocor);

		// 3.将申请用户写入到社团会员中并设置改会员角色是社团负责人
		userInfo.setUser_id(corInfo.getCorporation_leader_id());
		System.out.println("user----" + userInfo.getUser_id());
		user_information user = sCorInfoService.getUserInfoByUserId(userInfo);
		memberInfo.setMember_id(UUID.randomUUID().toString());
		memberInfo.setMember_corporation(corInfoDTO.getCorInfo().getCorporation_name());
		memberInfo.setMember_name(user.getUser_name());
		memberInfo.setMember_telephone(user.getUser_telephone());
		memberInfo.setMember_sex(user.getUser_sex());
		// memberInfo.setMember_num("");
		memberInfo.setMember_birthday(user.getUser_birthday());
		// memberInfo.setMember_college("");
		memberInfo.setMember_user(user.getUser_id());
		// memberInfo.setMember_major("");
		// memberInfo.setMember_class("");
		// memberInfo.setMember_department("");
		memberInfo.setMember_role("2");
		// memberInfo.setMember_position("");
		memberInfo.setMember_home_address(user.getUser_address());
		// memberInfo.setMember_qq("");
		memberInfo.setMember_join_time(TimeUtil.getStringDay());
		memberInfo.setMember_quit_time("9999-99-99");
		memberInfo.setMember_status("1");
		memberInfo.setMember_gmt_create(TimeUtil.getStringSecond());
		memberInfo.setMember_gmt_modified(TimeUtil.getStringSecond());
		System.out.println("00000000" + memberInfo);
		sCorInfoService.saveMemberInfo(memberInfo);
	}

	private corporation_message message;

	public corporation_message getMessage() {
		return message;
	}

	public void setMessage(corporation_message message) {
		this.message = message;
	}

	/*
	 * 保存审核社团的信息
	 */

	public void saveCorMessage() {
		sCorInfoService.updateDisagreecorInfoStatusById(corInfo);
		message.setCorporation_id(corInfo.getCorporation_id());
		CorInfoDTO corInfoDTO = sCorInfoService.getCorInfoByCorID(corInfo);
		message.setCorporation_name(corInfoDTO.getCorInfo().getCorporation_name());
		System.out.println("保存社团审核信息：----" + message);
		sCorInfoService.saveMessage(message);
	}

	/*
	 * 回到更改审核备注页面，将社团名称及备注信息返回该页面
	 */
	/*
	 * public String updateMessagePage() {
	 * 
	 * MessageDTO messageDTO = sCorInfoService.getMessagePageById(message);
	 * 
	 * ActionContext.getContext().getValueStack().set("messageDTO", messageDTO);
	 * 
	 * return "updatemessagePage";
	 * 
	 * }
	 */

	/*
	 * 保存修改社团申请入驻失败的方法
	 */
	/*
	 * public void updateMessage() { System.out.println(message);
	 * sCorInfoService.updateMessage(message);
	 * 
	 * GsonBuilder gsonBuilder = new GsonBuilder();
	 * gsonBuilder.setPrettyPrinting();// 格式化json数据 Gson gson =
	 * gsonBuilder.create(); try {
	 * 
	 * http_response.setContentType("text/html;charset=utf-8");
	 * 
	 * http_response.getWriter().write(gson.toJson("success"));
	 * 
	 * } catch (IOException e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * }
	 */
	/*
	 * 确认修改
	 */
	public void modifiedMessage() {
		System.out.println("信息id" + message_id);
		System.out.println("信息备注" + message_content);
		sCorInfoService.modifiedMessage(message_id, message_content);
	}

	/*
	 * ajax验证社团名是否重复
	 */
	public String validateCorporationName() throws UnsupportedEncodingException {
		if (sCorInfoService.corporationNameIsValid(corporation_name)) {
			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));

		} else {
			inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
		}

		return "ajax-success";
	}

	/*
	 * 模态框回显图片
	 */
	private String corporation_id;

	public void setCorporation_id(String corporation_id) {
		this.corporation_id = corporation_id;
	}

	public void showCorporaionLogo() throws IOException {
		System.out.println("hhhhhhoousfoud" + corporation_id);
		corporation_information cor = sCorInfoService.getCorInfoLogoByCorID(corporation_id);
		Gson gson = new Gson();
		String result = gson.toJson(cor);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		System.out.println(result);
		pw.write(result);
		pw.flush();
		pw.close();
	}

	public SCorInfoService getsCorInfoService() {
		return sCorInfoService;
	}

	public void setsCorInfoService(SCorInfoService sCorInfoService) {
		this.sCorInfoService = sCorInfoService;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public corporation_information getCorInfo() {
		return corInfo;
	}

	public void setCorInfo(corporation_information corInfo) {
		this.corInfo = corInfo;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
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

	public HttpServletResponse getHttp_response() {
		return http_response;
	}

	public void setHttp_response(HttpServletResponse http_response) {
		this.http_response = http_response;
	}

	public HttpServletRequest getHttp_request() {
		return http_request;
	}

	public void setHttp_request(HttpServletRequest http_request) {
		this.http_request = http_request;
	}

	public page_list_corInfoVO getPage_list_corInfo() {
		return page_list_corInfo;
	}

	public void setPage_list_corInfo(page_list_corInfoVO page_list_corInfo) {
		this.page_list_corInfo = page_list_corInfo;
	}

	public searchCorInfoListDTO getSearchCorInfoList() {
		return searchCorInfoList;
	}

	public void setSearchCorInfoList(searchCorInfoListDTO searchCorInfoList) {
		this.searchCorInfoList = searchCorInfoList;
	}

	public String getMessage_id() {
		return message_id;
	}

	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}

	public String getMessage_content() {
		return message_content;
	}

	public void setMessage_content(String message_content) {
		this.message_content = message_content;
	}

	public String getCorporation_name() {
		return corporation_name;
	}

	public user_into_corporation getUserintocor() {
		return userintocor;
	}

	public void setUserintocor(user_into_corporation userintocor) {
		this.userintocor = userintocor;
	}

}
