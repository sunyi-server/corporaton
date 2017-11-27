package com.corporation.corInfo.dao;

import java.util.List;

import com.corporation.corInfo.domain.UserInfo;
import com.corporation.corInfo.domain.page_list_userInfoVO;


public interface UserInfoDao {

	// 用户登录
    //根据user_username查询用户
	UserInfo Userlogin(String  user_username);

	
	// 查询所有用户
	List<UserInfo> getAllUser();

	// 添加
	void saveOrUpdate(UserInfo userInfo);

	// 删除
	void delete_User(String user_id);

	
	//表单回显
	UserInfo getId(String user_id);
	
	
	
	
	
	//分页查询
	 
	
	/*
	 * 在页面中显示所有的用户的信息
	 */

	public List<UserInfo> listUserInfoByPage(page_list_userInfoVO page_list_userInfo);
	
	
	
	/*
	 * 获得用户的记录数
	 */

	public int getUserInfoTotalRecords(page_list_userInfoVO page_list_userInfo);
	
	
	
	//分页查询

}
