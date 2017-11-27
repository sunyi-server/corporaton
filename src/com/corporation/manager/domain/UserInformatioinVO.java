package com.corporation.manager.domain;

import java.util.List;

public class UserInformatioinVO {
	private user_information userInfor;
	private List<user_into_corporation> listInto;

	public user_information getUserInfor() {
		return userInfor;
	}

	public void setUserInfor(user_information userInfor) {
		this.userInfor = userInfor;
	}

	public List<user_into_corporation> getListInto() {
		return listInto;
	}

	public void setListInto(List<user_into_corporation> listInto) {
		this.listInto = listInto;
	}

}
