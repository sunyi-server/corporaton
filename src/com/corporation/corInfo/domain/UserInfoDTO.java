package com.corporation.corInfo.domain;

public class UserInfoDTO {
private UserInfo userInfo;

public UserInfo getUserInfo() {
	return userInfo;
}
public void setUserInfo(UserInfo userInfo) {
	this.userInfo = userInfo;
}



public UserInfoDTO(UserInfo userInfo){
	this.userInfo = userInfo;
}


}
