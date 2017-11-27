package com.corporation.manager.service;

import java.util.List;

import com.corporation.manager.domain.UserApplyCorVO;
import com.corporation.manager.domain.UserInformatioinVO;
import com.corporation.manager.domain.corporation_information;
import com.corporation.manager.domain.user_information;

public interface UserApplyCorService {
	public UserApplyCorVO intoApply(String user_id, String currPage, String queryName);

	public String applyCorporation(String user_id, String corporation_id);

	public UserInformatioinVO getUserInfor(String user_id);

	public void updateUserInfor(user_information ui);

	public String corporatonNameIsExsit(String corporation_name);

	public void submitApplyCorporation(corporation_information ci);

	public List<corporation_information> getUserHaveCor(String user_id);
}
