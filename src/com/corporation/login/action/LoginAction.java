package com.corporation.login.action;

import com.corporation.login.service.impl.loginService;
import com.opensymphony.xwork2.ActionContext;

public class LoginAction {
	private loginService loginService;

	public String toSystem() {

		String loginResult = loginService.login(username, password);
		if (loginResult.equals("密码正确")) {
			ActionContext.getContext().getSession().put("user_id",
					loginService.getUserByUsername(username).getUser_id());
			return "loginSuccess";
		} else if (loginResult.equals("账户不存在")) {
			return "";
		}

		return password;
	}

	private String username;
	private String password;

	public loginService getLoginService() {
		return loginService;
	}

	public void setLoginService(loginService loginService) {
		this.loginService = loginService;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
