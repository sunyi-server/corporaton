package com.corporation.corInfo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.corporation.corInfo.dao.SCorInfoDao;
import com.corporation.corInfo.domain.CorInfoDTO;

import com.corporation.corInfo.domain.corporation_message;
import com.corporation.corInfo.domain.MessageDTO;
import com.corporation.corInfo.domain.page_list_corInfoVO;
import com.corporation.manager.domain.user_into_corporation;
import com.corporation.corInfo.service.SCorInfoService;
import com.corporation.manager.domain.corporation_information;
import com.corporation.manager.domain.member_information;
import com.corporation.manager.domain.user_information;

import util.TimeUtil;

public class SCorInfoServiceImpl implements SCorInfoService {

	private SCorInfoDao sCorInfoDao;

	public void setsCorInfoDao(SCorInfoDao sCorInfoDao) {
		this.sCorInfoDao = sCorInfoDao;
	}

	/*
	 * public List<corporation_information> listCorInfo() { return
	 * sCorInfoDao.listCorInfo(); }
	 */
	@Override
	public boolean saveCorInfo(corporation_information corInfo) {

		// TODO Auto-generated method stub
		corInfo.setCorporation_id(UUID.randomUUID().toString());
		System.out.println(UUID.randomUUID().toString());
		corInfo.setCorporation_gmt_create(TimeUtil.getStringSecond());
		corInfo.setCorporation_gmt_modified(corInfo.getCorporation_gmt_create());
		sCorInfoDao.saveCorInfo(corInfo);

		return true;
	}

	@Override
	public void removeCorInfoByID(corporation_information corInfo) {
		// TODO Auto-generated method stub

		corInfo = sCorInfoDao.getCorInfoByID(corInfo);

		sCorInfoDao.removeCorInfoByID(corInfo);

	}

	@Override
	public page_list_corInfoVO listCorInfoByPage(page_list_corInfoVO page_list_corInfo) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		List<CorInfoDTO> corInfoDTOList = new ArrayList<CorInfoDTO>();

		CorInfoDTO corInfoDTO;

		List<corporation_information> corInfoList = sCorInfoDao.listCorInfoByPage(page_list_corInfo);
		System.out.println("Search��ֵ" + page_list_corInfo.getSearch());
		if (page_list_corInfo.getSearch() != null && !page_list_corInfo.getSearch().getName().equals("")) {

			int i = 0;
			while (i < corInfoList.size()) {

				corInfoList.get(i).setCorporation_name(corInfoList.get(i).getCorporation_name().replaceAll(
						page_list_corInfo.getSearch().getName(),
						"<span style='color: #ff5063;'>" + page_list_corInfo.getSearch().getName() + "</span>"));

				i++;
			}
		}
		// ���������¼
		page_list_corInfo.setTotalRecords(sCorInfoDao.getCorInfoTotalRecords(page_list_corInfo));
		// ��ҳ��
		page_list_corInfo
				.setTotalPages(((page_list_corInfo.getTotalRecords() - 1) / page_list_corInfo.getPageSize()) + 1);

		if (page_list_corInfo.getPageIndex() <= 1) {
			page_list_corInfo.setHavePrePage(false);
		} else {
			page_list_corInfo.setHavePrePage(true);
		}
		if (page_list_corInfo.getPageIndex() >= page_list_corInfo.getTotalPages()) {
			page_list_corInfo.setHaveNextPage(false);
		} else {
			page_list_corInfo.setHaveNextPage(true);
		}

		for (corporation_information corInfo : corInfoList) {

			corInfoDTO = new CorInfoDTO(corInfo);

			corInfoDTOList.add(corInfoDTO);
		}

		page_list_corInfo.setCorInfoDTOList(corInfoDTOList);

		return page_list_corInfo;

	}

	@Override
	public CorInfoDTO getCorInfoByCorID(corporation_information corInfo) {
		// TODO Auto-generated method stub

		corInfo = sCorInfoDao.getCorInfoByCorID(corInfo);

		CorInfoDTO corInfoDTO = new CorInfoDTO(corInfo);

		return corInfoDTO;
	}

	@Override
	public boolean updateCorInfoAllByID(corporation_information corInfo) {
		// TODO Auto-generated method stub
		corInfo.setCorporation_gmt_modified(TimeUtil.getStringSecond());
		System.out.println("hhhhhhhhhhh" + corInfo);
		sCorInfoDao.updateCorInfoAllByID(corInfo);
		return true;
	}

	@Override
	public boolean updateAgreecorInfoStatusById(corporation_information corInfo) {
		// TODO Auto-generated method stub

		corInfo.setCorporation_gmt_modified(TimeUtil.getStringSecond());
		sCorInfoDao.updateAgreecorInfoStatusById(corInfo);
		
		return true;
	}

	@Override
	public boolean saveMessage(corporation_message message) {
		// TODO Auto-generated method stub
		message.setMessage_id(UUID.randomUUID().toString());
		System.out.println(UUID.randomUUID().toString());
		message.setMessage_gmt_create(TimeUtil.getStringSecond());
		message.setMessage_gmt_modified(message.getMessage_gmt_create());
		sCorInfoDao.saveMessage(message);
		return true;
	}

	@Override
	public boolean updateDisagreecorInfoStatusById(corporation_information corInfo) {
		// TODO Auto-generated method stub
		corInfo.setCorporation_gmt_modified(TimeUtil.getStringSecond());
		sCorInfoDao.updateDisagreecorInfoStatusById(corInfo);
		return true;
	}

	@Override
	public page_list_corInfoVO listMessageByPage(page_list_corInfoVO page_list_corInfo) {
		// TODO Auto-generated method stub
		List<MessageDTO> messageDTOList = new ArrayList<MessageDTO>();

		MessageDTO messageDTO;

		List<corporation_message> messageList = sCorInfoDao.listMessageByPage(page_list_corInfo);
		System.out.println("Search��ֵ" + page_list_corInfo.getSearch());
		if (page_list_corInfo.getSearch() != null && !page_list_corInfo.getSearch().getName().equals("")) {

			int i = 0;
			while (i < messageList.size()) {

				messageList.get(i).setCorporation_name(messageList.get(i).getCorporation_name().replaceAll(
						page_list_corInfo.getSearch().getName(),
						"<span style='color: #ff5063;'>" + page_list_corInfo.getSearch().getName() + "</span>"));

				i++;
			}
		}
		// ���������¼
		page_list_corInfo.setTotalRecords(sCorInfoDao.getMessageTotalRecords(page_list_corInfo));
		// ��ҳ��
		page_list_corInfo
				.setTotalPages(((page_list_corInfo.getTotalRecords() - 1) / page_list_corInfo.getPageSize()) + 1);

		if (page_list_corInfo.getPageIndex() <= 1) {
			page_list_corInfo.setHavePrePage(false);
		} else {
			page_list_corInfo.setHavePrePage(true);
		}
		if (page_list_corInfo.getPageIndex() >= page_list_corInfo.getTotalPages()) {
			page_list_corInfo.setHaveNextPage(false);
		} else {
			page_list_corInfo.setHaveNextPage(true);
		}

		for (corporation_message message : messageList) {

			messageDTO = new MessageDTO(message);

			messageDTOList.add(messageDTO);
		}

		page_list_corInfo.setMessageDTOList(messageDTOList);

		return page_list_corInfo;

	}

	@Override
	public void removeMessageByMessageID(corporation_message message) {
		// TODO Auto-generated method stub

		sCorInfoDao.removeMessageByMessageID(message);

	}

	@Override
	public MessageDTO getMessagePageById(corporation_message message) {
		// TODO Auto-generated method stub
		message = sCorInfoDao.getMessagePageById(message);

		MessageDTO messageDTO = new MessageDTO(message);

		return messageDTO;

	}

	@Override
	public boolean updateMessage(corporation_message message) {
		// TODO Auto-generated method stub
		message.setMessage_gmt_modified(TimeUtil.getStringSecond());
		sCorInfoDao.updateMessage(message);
		return true;
	}

	@Override
	public boolean corporationNameIsValid(String corporation_name) {
		// TODO Auto-generated method stub
		return sCorInfoDao.corporationNameIsValid(corporation_name) == null;
	}

	@Override
	public void modifiedMessage(String message_id, String message_content) {
		// TODO Auto-generated method stub

		sCorInfoDao.modifiedMessageByMessageid(message_id, message_content);
	}

	@Override
	public MessageDTO getMessageByCorporationid(corporation_information corInfo) {
		// TODO Auto-generated method stub
		corporation_message corMessage = sCorInfoDao.getMessageByCorporationid(corInfo);
		MessageDTO messageDTO = new MessageDTO(corMessage);
		return messageDTO;
	}

	@Override
	public void removeMessageByCorID(corporation_information corInfo) {
		// TODO Auto-generated method stub
		sCorInfoDao.removeMessageByCorID(corInfo);
	}

	@Override
	public corporation_information getCorInfoLogoByCorID(String corporation_id) {
		// TODO Auto-generated method stub
		corporation_information cor=sCorInfoDao.getCorInfoLogoByCorID(corporation_id);
		return cor;
	}

	@Override
	public void saveUserIntoCor(user_into_corporation userintocor) {
		// TODO Auto-generated method stub
		userintocor.setInto_id(UUID.randomUUID().toString());
	    userintocor.setInto_time(TimeUtil.getStringDay());
	    userintocor.setInto_gmt_create(TimeUtil.getStringSecond());
	    userintocor.setInto_gmt_modified(TimeUtil.getStringSecond());
		System.out.println("4444"+userintocor);
		
		sCorInfoDao.saveUserIntoCor(userintocor);
	}

	@Override
	public user_information getUserInfoByUserId(user_information userInfo) {
		// TODO Auto-generated method stub
		user_information user=sCorInfoDao.getUserInfoByUserId(userInfo);
		return user;
	}

	@Override
	public void saveMemberInfo(member_information memberInfo) {
		// TODO Auto-generated method stub
		sCorInfoDao.saveMemberInfo(memberInfo);
	}

	@Override
	public user_into_corporation getUserIntoCorByUserId(corporation_information corInfo) {
		// TODO Auto-generated method stub
		user_into_corporation userIntoCor=sCorInfoDao.getUserIntoCorByUserId(corInfo);
       return userIntoCor;
	}

	@Override
	public void removeUserIntoCorByUserID(corporation_information corInfo) {
		// TODO Auto-generated method stub
		sCorInfoDao.removeUserIntoCorByUserID(corInfo);
	}

	@Override
	public member_information getMemberInfoByUserId(corporation_information corInfo) {
		// TODO Auto-generated method stub
		member_information memberInfo=sCorInfoDao.getMemberInfoByUserId(corInfo);
		return memberInfo;
	}

	@Override
	public void removeMemberInfoByUserId(corporation_information corInfo) {
		// TODO Auto-generated method stub
		sCorInfoDao.removeMemberInfoByUserId(corInfo);
	}

	

}
