package com.corporation.corInfo.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.corporation.corInfo.domain.CorInfoDTO;

import com.corporation.corInfo.domain.corporation_message;
import com.corporation.corInfo.domain.page_list_corInfoVO;
import com.corporation.manager.domain.user_into_corporation;
import com.corporation.manager.domain.corporation_information;
import com.corporation.manager.domain.member_information;
import com.corporation.manager.domain.user_information;


public interface SCorInfoDao {
	/*
	 * ����id��ȡ��Ӧ��������Ϣ
	 */
	public corporation_information getCorInfoByID(corporation_information corInfo);
	/*
	 * 
	 * ����������Ϣ
	 */

	public boolean saveCorInfo(corporation_information corInfo);

	/*
	 * ����IDɾ�����Ӧ��������Ϣ
	 */

	public void removeCorInfoByID(corporation_information corInfo);
	/*
	 * ��ҳ������ʾ���е�������Ϣ
	 */

	public List<corporation_information> listCorInfoByPage(page_list_corInfoVO page_list_corInfo);
	/*
	 * ��������ܵļ�¼��
	 */

	public int getCorInfoTotalRecords(page_list_corInfoVO page_list_corInfo);
	/*
	 * ͨ������ID���Ҷ�Ӧ��������Ϣ
	 */

	public corporation_information getCorInfoByCorID(corporation_information corInfo);
	/*
	 * �Ƿ����������Ϣ
	 */

	public boolean updateCorInfoAllByID(corporation_information corInfo);

	/*
	 * ���ͬ���������פ���������ŵ�����״̬��Ϊ��ͨ��
	 */
	public boolean updateAgreecorInfoStatusById(corporation_information corInfo);

	/*
	 * ����δͨ����������Ϣ
	 */
	public boolean saveMessage(corporation_message message);
	/*
	 * �����ͬ���������פ���������ŵ�����״̬��Ϊδͨ��
	 */

	public boolean updateDisagreecorInfoStatusById(corporation_information corInfo);
	/*
	 * �������Ϣ��ҳ������ʾ����
	 */

	public List<corporation_message> listMessageByPage(page_list_corInfoVO page_list_corInfo);

	/*
	 * ��ȡ�����Ϣ���ܼ�¼��
	 */
	public int getMessageTotalRecords(page_list_corInfoVO page_list_corInfo);

	/*
	 * ����message_idɾ�����Ӧ�����������Ϣ
	 */
	public void removeMessageByMessageID(corporation_message message);
	/*
	 * ����message_id�����Ӧ�����ݣ������ҳ����ʾ
	 */

	public corporation_message getMessagePageById(corporation_message message);

	/*
	 * ���������Ϣ
	 */
	public boolean updateMessage(corporation_message message);

	/*
	 * ��֤������Ϣ�Ƿ��ظ�
	 */
	public corporation_information corporationNameIsValid(String corporation_name);

	/*
	 * �޸�����δͨ����ע��Ϣ
	 */
	public void modifiedMessageByMessageid(String message_id, String message_content);

	/*
	 * ����id��ȡ����δͨ����Ϣ
	 */
	public corporation_message getMessageByCorporationid(corporation_information corInfo);

	/*
	 * ɾ��������δͨ����Ϣ
	 */
	public void removeMessageByCorID(corporation_information corInfo);
/*
 * ����������Ϣ���õ�logo������corportion_id
 */
	public corporation_information getCorInfoLogoByCorID(String corporation_id);
/*
 * 绑定用户和社团之间的关系
 */
	public void saveUserIntoCor(user_into_corporation userintocor);
/*
 * 通过用户id获取用户表信息
 */
	public user_information getUserInfoByUserId(user_information userInfo);
/*
 * 保存会员表信息
 */
	public void saveMemberInfo(member_information memberInfo);
/*
 * 获取user_into_corporation
 */
	public user_into_corporation getUserIntoCorByUserId(corporation_information corInfo);
/*
 * 删除user_into_corporation
 */
	public void removeUserIntoCorByUserID(corporation_information corInfo);

	public member_information getMemberInfoByUserId(corporation_information corInfo);

	public void removeMemberInfoByUserId(corporation_information corInfo);

}
