package com.corporation.login.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.corporation.manager.domain.user_information;

public class loginDaoImpl implements loginDao {
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

	@Override
	public user_information login(String username) {
		// TODO Auto-generated method stub

		String hql = "from user_information where user_username = '" + username + "'";
		Query query = getSession().createQuery(hql);
		List<user_information> list = query.list();
		user_information ui;
		if (list.size() > 0) {
			ui = list.get(0);
		} else {
			ui = new user_information();
		}
		return ui;
	}
}
