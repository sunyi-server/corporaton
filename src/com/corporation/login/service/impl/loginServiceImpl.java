package com.corporation.login.service.impl;

import com.corporation.login.dao.impl.loginDao;
import com.corporation.manager.domain.user_information;

public class loginServiceImpl implements loginService {
	private loginDao loginDao;

	public loginDao getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(loginDao loginDao) {
		this.loginDao = loginDao;
	}

	@Override
	public String login(String username, String password) {
		// TODO Auto-generated method stub

		user_information ui = loginDao.login(username);

		if (ui.getUser_id() == null) {
			return "�˻�������";
		} else {
			if (ui.getUser_password().equals(password)) {
				return "������ȷ";
			} else {
				return "�������";
			}
		}

	}

	@Override
	public user_information getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return loginDao.login(username);
	}

}
