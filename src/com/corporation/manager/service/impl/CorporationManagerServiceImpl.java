package com.corporation.manager.service.impl;

import com.corporation.manager.dao.CorporationManagerDao;
import com.corporation.manager.service.CorporationManagerService;

public class CorporationManagerServiceImpl implements CorporationManagerService {
	private CorporationManagerDao corporationManagerDao;

	public CorporationManagerDao getCorporationManagerDao() {
		return corporationManagerDao;
	}

	public void setCorporationManagerDao(CorporationManagerDao corporationManagerDao) {
		this.corporationManagerDao = corporationManagerDao;
	}

}
