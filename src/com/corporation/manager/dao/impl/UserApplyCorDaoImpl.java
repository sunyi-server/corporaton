package com.corporation.manager.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.corporation.manager.dao.UserApplyCorDao;
import com.corporation.manager.domain.corporation_information;
import com.corporation.manager.domain.user_information;
import com.corporation.manager.domain.user_into_corporation;

public class UserApplyCorDaoImpl implements UserApplyCorDao {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	// ------------------------------------------获得已申请社团--------------------------------------------------------------
	@Override
	@Transactional
	public List<user_into_corporation> getUserApplyCor(String user_id) {
		// TODO Auto-generated method stub
		String hql = "from user_into_corporation where into_man = '" + user_id + "'";
		List<user_into_corporation> list = getSession().createQuery(hql).list();
		return list;
	}

	// --------------------------------------------获得社团总页数-----------------------------------------------------------
	@Override
	public int getCorporationCount(String queryName) {
		// TODO Auto-generated method stub
		queryName = "%" + queryName + "%";
		String hql = "select count(*) from corporation_information where corporation_name like '" + queryName
				+ "' and corporation_status ='2'";
		Query query = getSession().createQuery(hql);
		int totalSize = ((Number) query.uniqueResult()).intValue();
		return totalSize;
	}
	// ------------------------------------------获得社团信息分页--------------------------------------------------------------------

	@Override
	public List<corporation_information> getCorporationByPage(int currPage, String queryName, int pageSize) {
		// TODO Auto-generated method stub
		queryName = "%" + queryName + "%";
		String hql = "from corporation_information where corporation_name like '" + queryName
				+ "' and corporation_status ='2'";
		Query query = getSession().createQuery(hql).setFirstResult((currPage - 1) * pageSize).setMaxResults(pageSize);
		List<corporation_information> list = query.list();
		return list;
	}
	// ------------------------------------------提交申请--------------------------------------------------------------------

	@Override
	public int judgeIsApply(String user_id, String corporation_name) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from user_into_corporation where into_man = '" + user_id
				+ "' and user_corporation_include ='" + corporation_name + "'";
		int count = ((Number) getSession().createQuery(hql).uniqueResult()).intValue();
		System.out.println("是否存在" + count);
		return count;
	}

	// ------------------------------------------通过id查找社团--------------------------------------------------------------------
	@Override
	public corporation_information getCorporationById(String corporation_id) {
		// TODO Auto-generated method stub
		corporation_information ci = (corporation_information) getSession().get(corporation_information.class,
				corporation_id);
		return ci;
	}

	// --------------------------------------------申请进入社团-------------------------------------------------------
	@Override
	public void applyCorporation(user_into_corporation uic) {
		// TODO Auto-generated method stub
		getSession().save(uic);
	}

	// ------------------------------------------------获得用户信息----------------------------------------------------------

	@Override
	public user_information getUserInfor(String user_id) {
		// TODO Auto-generated method stub

		user_information ui = (user_information) getSession().get(user_information.class, user_id);

		return ui;
	}

	// --------------------------------------------获得用户已经加入的社团-----------------------------------------------------

	@Override
	public List<user_into_corporation> getIntoCorporation(String user_id) {
		// TODO Auto-generated method stub
		String hql = "from user_into_corporation where into_man='" + user_id + "' and into_status = '2'";
		Query query = getSession().createQuery(hql);
		List<user_into_corporation> list = query.list();
		return list;
	}

	@Override
	public void updateUserInfor(user_information ui) {
		// TODO Auto-generated method stub
		getSession().saveOrUpdate(ui);
	}

	@Override
	public String corporatonNameIsExsit(String corporation_name) {
		// TODO Auto-generated method stub\

		String hql = "from corporation_information where corporation_name ='" + corporation_name + "'";
		Query query = getSession().createQuery(hql);
		List<corporation_information> list = query.list();
		return Integer.toString(list.size());
	}

	@Override
	public void submitApplyCorporation(corporation_information ci) {
		// TODO Auto-generated method stub
		getSession().save(ci);
	}

	@Override
	public List<corporation_information> getUserHaveCor(String user_id) {
		// TODO Auto-generated method stub
		String hql = "from corporation_information where corporation_apply_man = '" + user_id
				+ "' order by corporation_gmt_create desc";
		Query query = getSession().createQuery(hql);
		List<corporation_information> list = query.list();
		return list;
	}
}
