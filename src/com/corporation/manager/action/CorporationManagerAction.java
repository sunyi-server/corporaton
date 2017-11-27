package com.corporation.manager.action;

import com.corporation.manager.service.CorporationManagerService;
import com.opensymphony.xwork2.ActionSupport;

/*
 * 社团信息管理类
 */

public class CorporationManagerAction extends ActionSupport {
	private CorporationManagerService corporationManagerService;

	public CorporationManagerService getCorporationManagerService() {
		return corporationManagerService;
	}

	public void setCorporationManagerService(CorporationManagerService corporationManagerService) {
		this.corporationManagerService = corporationManagerService;
	}

}
