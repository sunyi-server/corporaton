package com.corporation.manager.service.impl;

import java.util.List;

import com.corporation.manager.dao.UserApplyCorDao;
import com.corporation.manager.domain.UserApplyCorVO;
import com.corporation.manager.domain.UserInformatioinVO;
import com.corporation.manager.domain.corporation_information;
import com.corporation.manager.domain.user_information;
import com.corporation.manager.domain.user_into_corporation;
import com.corporation.manager.service.UserApplyCorService;

import util.TimeUtil;
import util.uuid;

public class UserApplyCorServiceImpl implements UserApplyCorService {
	private UserApplyCorDao userApplyCorDao;

	public UserApplyCorDao getUserApplyCorDao() {
		return userApplyCorDao;
	}

	public void setUserApplyCorDao(UserApplyCorDao userApplyCorDao) {
		this.userApplyCorDao = userApplyCorDao;
	}

	// --------------------------------------------获得前台展示VO----------------------------------------
	@Override
	public UserApplyCorVO intoApply(String user_id, String currPage, String queryName) {
		// TODO Auto-generated method stub
		int curr = Integer.parseInt(currPage);
		int pageSize = 6;

		List<user_into_corporation> listApply = userApplyCorDao.getUserApplyCor(user_id);
		List<corporation_information> listCorporation = userApplyCorDao.getCorporationByPage(curr, queryName, pageSize);
		int totalSize = userApplyCorDao.getCorporationCount(queryName);
		UserApplyCorVO userVO = new UserApplyCorVO();
		double count = (double) totalSize;
		double pageSiz = 6.0;
		int totalPage = (int) Math.ceil(count / pageSize);
		userVO.setCurrPage(curr);
		userVO.setPageSize(pageSize);
		userVO.setTotalPage(totalPage);
		userVO.setTotalSize(totalSize);
		userVO.setCorporationInfo(listCorporation);
		userVO.setQueryName(queryName);
		userVO.setUserInCorporation(listApply);
		return userVO;
	}

	// -----------------------------------------------提交申请--------------------------------------------------------
	@Override
	public String applyCorporation(String user_id, String corporation_id) {
		// TODO Auto-generated method stub
		System.out.println("进入了service");
		corporation_information ci = userApplyCorDao.getCorporationById(corporation_id);
		System.out.println("当前社团名" + ci.getCorporation_name());
		int isExist = userApplyCorDao.judgeIsApply(user_id, ci.getCorporation_name());
		if (isExist == 1) {
			return "isExist";
		} else {
			user_into_corporation uic = new user_into_corporation();
			uic.setInto_id(uuid.getUuid());
			uic.setInto_man(user_id);
			uic.setUser_corporation_include(ci.getCorporation_name());
			uic.setInto_time(TimeUtil.getStringDay());
			uic.setInto_status("0");
			uic.setInto_gmt_create(TimeUtil.getStringSecond());
			uic.setInto_gmt_modified(TimeUtil.getStringSecond());
			userApplyCorDao.applyCorporation(uic);
			return "applySuccess";
		}

	}

	// ------------------------------------------------获得用户信息------------------------------------------
	@Override
	public UserInformatioinVO getUserInfor(String user_id) {
		// TODO Auto-generated method stub
		user_information ui = userApplyCorDao.getUserInfor(user_id);
		List<user_into_corporation> listIntoCor = userApplyCorDao.getIntoCorporation(user_id);
		UserInformatioinVO uiv = new UserInformatioinVO();
		uiv.setListInto(listIntoCor);
		uiv.setUserInfor(ui);
		return uiv;
	}

	@Override
	public void updateUserInfor(user_information ui) {
		// TODO Auto-generated method stub
		userApplyCorDao.updateUserInfor(ui);
	}

	@Override
	public String corporatonNameIsExsit(String corporation_name) {
		// TODO Auto-generated method stub
		System.out.println("来到了service层:" + corporation_name);
		String result = userApplyCorDao.corporatonNameIsExsit(corporation_name);
		return result;
	}

	@Override
	public void submitApplyCorporation(corporation_information ci) {
		// TODO Auto-generated method stub
		userApplyCorDao.submitApplyCorporation(ci);
	}

	@Override
	public List<corporation_information> getUserHaveCor(String user_id) {
		// TODO Auto-generated method stub
		List<corporation_information> list = userApplyCorDao.getUserHaveCor(user_id);
		return list;
	}
}
