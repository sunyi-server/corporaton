package com.corporation.manager.dao;

import java.util.List;

import com.corporation.manager.domain.corporation_information;
import com.corporation.manager.domain.user_information;
import com.corporation.manager.domain.user_into_corporation;

public interface UserApplyCorDao {
	public List<user_into_corporation> getUserApplyCor(String user_id);

	public int getCorporationCount(String queryName);

	public List<corporation_information> getCorporationByPage(int currPage, String queryName, int pageSize);

	public corporation_information getCorporationById(String corporation_id);

	public int judgeIsApply(String user_id, String corporation_name);

	public void applyCorporation(user_into_corporation uic);

	public user_information getUserInfor(String user_id);

	public List<user_into_corporation> getIntoCorporation(String user_id);

	public void updateUserInfor(user_information ui);

	public String corporatonNameIsExsit(String corporation_name);

	public void submitApplyCorporation(corporation_information ci);

	public List<corporation_information> getUserHaveCor(String user_id);
}
