package com.corporation.login.service.impl;

import com.corporation.manager.domain.user_information;

public interface loginService {
	public String login(String username, String password);

	public user_information getUserByUsername(String username);
}
