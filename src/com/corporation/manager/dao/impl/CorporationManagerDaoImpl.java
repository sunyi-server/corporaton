package com.corporation.manager.dao.impl;

import org.hibernate.SessionFactory;

import com.corporation.manager.dao.CorporationManagerDao;

public class CorporationManagerDaoImpl implements CorporationManagerDao {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
