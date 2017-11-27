package com.corporation.corInfo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.weaver.AjAttribute.PrivilegedAttribute;

import com.corporation.corInfo.dao.UserInfoDao;
import com.corporation.corInfo.dao.impl.UserInfoDaoImpl;
import com.corporation.corInfo.domain.UserInfo;
import com.corporation.corInfo.domain.UserInfoDTO;
import com.corporation.corInfo.domain.page_list_userInfoVO;
import com.corporation.corInfo.service.UserInfoService;

public class UserInfoServiceImpl implements UserInfoService{
	private UserInfoDao userInfoDao;
	public void setUserInfoDao(UserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}
	
	//用户登录
	@Override
	public UserInfo Userlogin(String user_username) {
		// TODO Auto-generated method stub
		return userInfoDao.Userlogin(user_username);
	}

	@Override
	public List<UserInfo> getAllUser() {
		List<UserInfo> userInfo=userInfoDao.getAllUser();
		return userInfo;
	}

	@Override
	public void saveOrUpdate(UserInfo userInfo) {
		// TODO Auto-generated method stub
		userInfoDao.saveOrUpdate(userInfo);
	}

	@Override
	public void delete_User(String user_id) {
		// TODO Auto-generated method stub
		userInfoDao.delete_User(user_id);
	}

	//表单回显
	@Override
	public UserInfo getId(String user_id) {
		// TODO Auto-generated method stub
		return userInfoDao.getId(user_id);
	}
	
	
	//分页
	@Override
	public page_list_userInfoVO listCorInfoByPage(page_list_userInfoVO page_list_userInfo) {
		// TODO Auto-generated method stub
		List<UserInfoDTO> userInfoDTOList=new ArrayList<UserInfoDTO>();
		
		UserInfoDTO userInfoDTO;
		
		//获取所有用户数据
		List<UserInfo> userInfoList=userInfoDao.listUserInfoByPage(page_list_userInfo);
		
		// 获得整个记录
		page_list_userInfo.setTotalRecords(userInfoDao.getUserInfoTotalRecords(page_list_userInfo));
		
		// 总页数
		page_list_userInfo
		.setTotalPages(((page_list_userInfo.getTotalRecords() - 1) / page_list_userInfo.getPageSize()) + 1);
		
		
		/*
		 * 
		 * 判断是否有上一页下一页操作
		 * 
		 * 
		 */
		
		//如果当前页小于或者等于1没有上一页操作
		if(page_list_userInfo.getPageIndex()<=1){
			page_list_userInfo.setHavePrePage(false);
		}else {
			page_list_userInfo.setHavePrePage(true);
		}
		//如果当前页大于或者等于总页数就没有下一页操作
		if(page_list_userInfo.getPageIndex()>=page_list_userInfo.getTotalPages()){
			page_list_userInfo.setHaveNextPage(false);
		}else{
			page_list_userInfo.setHaveNextPage(true);
		}
		
		for(UserInfo userInfo:userInfoList){
			userInfoDTO=new UserInfoDTO(userInfo);
			
			userInfoDTOList.add(userInfoDTO);
		}
		
		page_list_userInfo.setUserInfoDTOList(userInfoDTOList);
		
		return page_list_userInfo;
	}

}
