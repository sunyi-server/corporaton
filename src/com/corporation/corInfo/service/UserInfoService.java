package com.corporation.corInfo.service;

import java.util.List;

import com.corporation.corInfo.domain.UserInfo;
import com.corporation.corInfo.domain.page_list_userInfoVO;


public interface UserInfoService {
	// 用户登录
	UserInfo Userlogin(String user_username);

	// 查询所有用户
	List<UserInfo> getAllUser();

	// 通过id查询用户
	//List<User> getUsers_ById(String id);

	// 添加
	void saveOrUpdate(UserInfo userInfo);

	// 删除
	void delete_User(String id);

	
	//表单回显
	UserInfo getId(String user_id);
	
	//分页
	public page_list_userInfoVO listCorInfoByPage(page_list_userInfoVO page_list_userInfo);

}
