package com.corporation.corInfo.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.corporation.corInfo.dao.SCorInfoDao;
import com.corporation.corInfo.domain.CorInfoDTO;

import com.corporation.corInfo.domain.corporation_message;
import com.corporation.corInfo.domain.page_list_corInfoVO;
import com.corporation.manager.domain.user_into_corporation;
import com.corporation.manager.domain.corporation_information;
import com.corporation.manager.domain.member_information;
import com.corporation.manager.domain.user_information;

import util.TimeUtil;

public class SCorInfoDaoImpl implements SCorInfoDao {

	private SessionFactory sessionFactory;

	public Session getSession() {

		return this.sessionFactory.getCurrentSession();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<corporation_information> listCorInfo() {

		String hql = "from corporation_information";
		return getSession().createQuery(hql).list();
	}
	/*
	 * public corporation_information get(String id){ String
	 * hql="select * form corporation_information where corporation_id=?"; return
	 * (corporation_information) getSession().createQuery(hql).list(); }
	 */

	@Override
	public boolean saveCorInfo(corporation_information corInfo) {
		// TODO Auto-generated method stub
		System.out.println(corInfo);
		Session session = getSession();
		session.save(corInfo);
		return true;
	}

	@Override
	public corporation_information getCorInfoByID(corporation_information corInfo) {
		// TODO Auto-generated method stub
		Session session = getSession();

		String hql = "from corporation_information corInfo where corInfo.corporation_id='" + corInfo.getCorporation_id()
				+ "'";

		Query query = session.createQuery(hql);

		corInfo = (corporation_information) query.uniqueResult();

		return corInfo;
	}

	@Override
	public void removeCorInfoByID(corporation_information corInfo) {
		// TODO Auto-generated method stub

		Session session = getSession();
		session.delete(corInfo);
	}

	@Override
	public List<corporation_information> listCorInfoByPage(page_list_corInfoVO page_list_corInfo) {

		Session session = getSession();
		String sqrt = "corporation_gmt_create";
		String sqrt_sc = "desc";
		String corporation_name = "%%";
		String corporation_status = "%%";
		if (page_list_corInfo.getSearch() != null) {
			sqrt = page_list_corInfo.getSearch().getSqrt();
			sqrt_sc = page_list_corInfo.getSearch().getSqrt_sc();
			corporation_name = "%" + page_list_corInfo.getSearch().getName() + "%";
			corporation_status = "%" + page_list_corInfo.getSearch().getCheck() + "%";
		}
		String hql = "from corporation_information corInfo where (corInfo.corporation_name like '" + corporation_name
				+ "') and corInfo.corporation_status like '" + corporation_status + "'    order by corInfo." + sqrt
				+ " " + sqrt_sc;
		System.out.println("hql:" + hql);

		Query query = session.createQuery(hql);

		query.setFirstResult((page_list_corInfo.getPageIndex() - 1) * page_list_corInfo.getPageSize());

		query.setMaxResults(page_list_corInfo.getPageSize());

		List<corporation_information> corInfoList = query.list();

		session.clear();

		return corInfoList;

	}

	@Override
	public int getCorInfoTotalRecords(page_list_corInfoVO page_list_corInfo) {
		Session session = getSession();
		String sqrt = "corporation_gmt_create";
		String sqrt_sc = "desc";
		String corporation_name = "%%";
		String corporation_status = "%%";
		if (page_list_corInfo.getSearch() != null) {
			sqrt = page_list_corInfo.getSearch().getSqrt();
			sqrt_sc = page_list_corInfo.getSearch().getSqrt_sc();
			corporation_name = "%" + page_list_corInfo.getSearch().getName() + "%";
			corporation_status = "%" + page_list_corInfo.getSearch().getCheck() + "%";
		}
		String hql = "select count(*) from corporation_information corInfo where (corInfo.corporation_status  like '"
				+ corporation_status + "') and corInfo.corporation_name like '" + corporation_name
				+ "'   order by corInfo." + sqrt + " " + sqrt_sc;

		Query query = session.createQuery(hql);

		int count = ((Number) query.uniqueResult()).intValue();

		//
		return count;
	}

	@Override
	public corporation_information getCorInfoByCorID(corporation_information corInfo) {
		// TODO Auto-generated method stub

		String hql = "from  corporation_information corInfo  where corInfo.corporation_id='"
				+ corInfo.getCorporation_id() + "'";

		return (corporation_information) getSession().createQuery(hql).uniqueResult();

	}

	@Override
	public boolean updateCorInfoAllByID(corporation_information corInfo) {
		// TODO Auto-generated method stub
		Session session = getSession();
		System.out.println("hufhsfhsdkfh" + corInfo);
		String hql = "update corporation_information corInfo set corInfo.corporation_name='"
				+ corInfo.getCorporation_name() + "',corInfo.corporation_introduce='"
				+ corInfo.getCorporation_introduce() + "',corInfo.corporation_apply_man='"
				+ corInfo.getCorporation_apply_man() + "',corInfo.corporation_apply_time='"
				+ corInfo.getCorporation_apply_time() + "' ,corInfo.corporation_leader_id='"
				+ corInfo.getCorporation_leader_id() + "',corInfo.corporation_logo='" + corInfo.getCorporation_logo()
				+ "',corInfo.corporation_type='" + corInfo.getCorporation_type() + "' ,corInfo.corporation_solidify='"
				+ corInfo.getCorporation_solidify() + "',corInfo.corporation_status='" + corInfo.getCorporation_status()
				+ "',corInfo.corporation_gmt_modified='" + corInfo.getCorporation_gmt_modified()
				+ "' where corInfo.corporation_id='" + corInfo.getCorporation_id() + "'";
		Query query = session.createQuery(hql);

		query.executeUpdate();

		return true;
	}

	@Override
	public boolean updateAgreecorInfoStatusById(corporation_information corInfo) {
		// TODO Auto-generated method stub
		Session session = getSession();
		System.out.println("通过修改"+corInfo);
		String hql = "update corporation_information corInfo set corInfo.corporation_status=2,corInfo.corporation_gmt_modified='"
				+ corInfo.getCorporation_gmt_modified() + "',corInfo.corporation_leader_id='"+corInfo.getCorporation_leader_id()+"' where corInfo.corporation_id='"
				+ corInfo.getCorporation_id() + "'";
		Query query = session.createQuery(hql);

		query.executeUpdate();
		return true;
	}

	@Override
	public boolean saveMessage(corporation_message message) {
		// TODO Auto-generated method stub
		System.out.println(message);
		Session session = getSession();
		session.save(message);
      
		return true;
	}

	@Override
	public boolean updateDisagreecorInfoStatusById(corporation_information corInfo) {
		// TODO Auto-generated method stub
		System.out.println("��ͬ��ʱ��Ϊ:" + corInfo.getCorporation_gmt_modified());
		Session session = getSession();
		String hql = "update corporation_information corInfo set corInfo.corporation_status=1,corInfo.corporation_gmt_modified='"
				+ corInfo.getCorporation_gmt_modified() + "' where corInfo.corporation_id='"
				+ corInfo.getCorporation_id() + "'";
		Query query = session.createQuery(hql);

		query.executeUpdate();
		return true;
	}

	@Override
	public List<corporation_message> listMessageByPage(page_list_corInfoVO page_list_corInfo) {
		// TODO Auto-generated method stub
		Session session = getSession();
		String sqrt = "message_gmt_create";
		String sqrt_sc = "desc";
		String corporation_name = "%%";
		/* String corporation_status = "%%"; */
		if (page_list_corInfo.getSearch() != null) {
			sqrt = page_list_corInfo.getSearch().getSqrt();
			sqrt_sc = page_list_corInfo.getSearch().getSqrt_sc();
			corporation_name = "%" + page_list_corInfo.getSearch().getName() + "%";

		}
		String hql = "from corporation_message message where (message.corporation_name like '" + corporation_name
				+ "')   order by message." + sqrt + " " + sqrt_sc;

		System.out.println("hql:" + hql);

		Query query = session.createQuery(hql);

		query.setFirstResult((page_list_corInfo.getPageIndex() - 1) * page_list_corInfo.getPageSize());

		query.setMaxResults(page_list_corInfo.getPageSize());

		List<corporation_message> messageList = query.list();

		session.clear();

		return messageList;

	}

	@Override
	public int getMessageTotalRecords(page_list_corInfoVO page_list_corInfo) {
		// TODO Auto-generated method stub
		Session session = getSession();

		String hql = "select count(*) from corporation_message";

		Query query = session.createQuery(hql);

		int count = ((Number) query.uniqueResult()).intValue();

		//
		return count;
	}

	@Override
	public void removeMessageByMessageID(corporation_message message) {
		// TODO Auto-generated method stub
		System.out.println(message);
		Session session = getSession();

		String hql = "delete from corporation_message message where message.message_id='" + message.getMessage_id()
				+ "'";

		Query query = session.createQuery(hql);

		query.executeUpdate();
	}

	@Override
	public corporation_message getMessagePageById(corporation_message message) {
		// TODO Auto-generated method stub
		Session session = getSession();

		String hql = "from corporation_message message where message.message_id='" + message.getMessage_id() + "'";

		Query query = session.createQuery(hql);

		message = (corporation_message) query.uniqueResult();

		return message;
	}

	@Override
	public boolean updateMessage(corporation_message message) {
		// TODO Auto-generated method stub

		Session session = getSession();

		System.out.println(message);
		session.saveOrUpdate(message);

		return true;
	}

	@Override
	public corporation_information corporationNameIsValid(String corporation_name) {
		// TODO Auto-generated method stub

		String hql = "FROM corporation_information corInfo WHERE corInfo.corporation_name = ?";
		Query query = getSession().createQuery(hql).setString(0, corporation_name);
		return (corporation_information) query.uniqueResult();

	}

	@Override
	public void modifiedMessageByMessageid(String message_id, String message_content) {
		// TODO Auto-generated method stub
		System.out.println("hfhfhshfsk" + message_id);
		corporation_message corme = new corporation_message();
		corme.setMessage_gmt_modified(TimeUtil.getStringSecond());
		System.out.println("yyy" + corme.getMessage_gmt_modified());
		String hql = "update corporation_message set message_content='" + message_content + "',message_gmt_modified='"
				+ corme.getMessage_gmt_modified() + "' where message_id='" + message_id + "'";
		getSession().createQuery(hql).executeUpdate();
	}

	@Override
	public corporation_message getMessageByCorporationid(corporation_information corInfo) {
		// TODO Auto-generated method stub
		String hql = "from corporation_message where corporation_id='" + corInfo.getCorporation_id() + "'";

		return (corporation_message) getSession().createQuery(hql).uniqueResult();
	}

	@Override
	public void removeMessageByCorID(corporation_information corInfo) {
		// TODO Auto-generated method stub
		String hql = "delete corporation_message where corporation_id='" + corInfo.getCorporation_id() + "'";
		getSession().createQuery(hql).executeUpdate();
	}

	@Override
	public corporation_information getCorInfoLogoByCorID(String corporation_id) {
		// TODO Auto-generated method stub
		System.out.println("11111111"+corporation_id);
		String hql = "FROM corporation_information corInfo WHERE corInfo.corporation_id =?";	
		Query query = getSession().createQuery(hql).setString(0, corporation_id);
		System.out.println(hql);
		return (corporation_information) query.uniqueResult();
		
	}


	@Override
	public void saveUserIntoCor(user_into_corporation userintocor) {
		// TODO Auto-generated method stub
		System.out.println("daoimpl===="+userintocor);
		Session session=getSession();
		session.save(userintocor);
	}

	@Override
	public user_information getUserInfoByUserId(user_information userInfo) {
		// TODO Auto-generated method stub
	   String hql="from user_information where user_id='"+userInfo.getUser_id()+"'";
	  return  (user_information) getSession().createQuery(hql).uniqueResult();
	 	
	}

	@Override
	public void saveMemberInfo(member_information memberInfo) {
		// TODO Auto-generated method stub
		Session session=getSession();
		session.save(memberInfo);
	}

	@Override
	public user_into_corporation getUserIntoCorByUserId(corporation_information corInfo) {
		// TODO Auto-generated method stub
		String hql="from user_into_corporation where into_man='"+corInfo.getCorporation_apply_man()+"'";
		return (user_into_corporation) getSession().createQuery(hql).uniqueResult();
		
	}

	@Override
	public void removeUserIntoCorByUserID(corporation_information corInfo) {
		// TODO Auto-generated method stub
		String hql = "delete user_into_corporation where into_man='" + corInfo.getCorporation_apply_man() + "'";
		getSession().createQuery(hql).executeUpdate();
	}

	@Override
	public member_information getMemberInfoByUserId(corporation_information corInfo) {
		// TODO Auto-generated method stub
		String hql="from member_information where member_user='"+corInfo.getCorporation_apply_man()+"'";
		return (member_information) getSession().createQuery(hql).uniqueResult();
	}

	@Override
	public void removeMemberInfoByUserId(corporation_information corInfo) {
		// TODO Auto-generated method stub
		String hql = "delete member_information where member_user='" + corInfo.getCorporation_apply_man() + "'";
		getSession().createQuery(hql).executeUpdate();
	}

}
