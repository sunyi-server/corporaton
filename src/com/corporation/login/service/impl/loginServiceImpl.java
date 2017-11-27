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
			return "账户不存在";
		} else {
			if (ui.getUser_password().equals(password)) {
				return "密码正确";
			} else {
				return "密码错误";
			}
		}

	}

	@Override
	public user_information getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return loginDao.login(username);
	}

}
