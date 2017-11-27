package com.corporation.corInfo.service;

import java.util.List;

import com.corporation.corInfo.domain.CorInfoDTO;
import com.corporation.corInfo.domain.MessageDTO;

import com.corporation.corInfo.domain.corporation_message;
import com.corporation.corInfo.domain.page_list_corInfoVO;
import com.corporation.manager.domain.user_into_corporation;
import com.corporation.manager.domain.corporation_information;
import com.corporation.manager.domain.member_information;
import com.corporation.manager.domain.user_information;


public interface SCorInfoService {

	public boolean saveCorInfo(corporation_information corInfo);

	public void removeCorInfoByID(corporation_information corInfo);

	public page_list_corInfoVO listCorInfoByPage(page_list_corInfoVO page_list_corInfo);

	public CorInfoDTO getCorInfoByCorID(corporation_information corInfo);

	public boolean updateCorInfoAllByID(corporation_information corInfo);

	public boolean updateAgreecorInfoStatusById(corporation_information corInfo);

	public boolean saveMessage(corporation_message message);

	public boolean updateDisagreecorInfoStatusById(corporation_information corInfo);

	public page_list_corInfoVO listMessageByPage(page_list_corInfoVO page_list_corInfo);

	public void removeMessageByMessageID(corporation_message message);

	public MessageDTO getMessagePageById(corporation_message message);

	public boolean updateMessage(corporation_message message);

	public boolean corporationNameIsValid(String corporation_name);

	public void modifiedMessage(String message_id, String message_content);

	public MessageDTO getMessageByCorporationid(corporation_information corInfo);

	public void removeMessageByCorID(corporation_information corInfo);

	public corporation_information getCorInfoLogoByCorID(String corporation_id);

	public void saveUserIntoCor(user_into_corporation userintocor);

	public user_information getUserInfoByUserId(user_information userInfo);

	public void saveMemberInfo(member_information memberInfo);

	public user_into_corporation getUserIntoCorByUserId(corporation_information corInfo);

	public void removeUserIntoCorByUserID(corporation_information corInfo);

	public member_information getMemberInfoByUserId(corporation_information corInfo);

	public void removeMemberInfoByUserId(corporation_information corInfo);
}
