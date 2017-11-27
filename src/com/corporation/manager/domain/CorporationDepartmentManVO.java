package com.corporation.manager.domain;

import java.util.List;

public class CorporationDepartmentManVO {
	private List<corporation_department> corporationDepart;
	private corporation_information corporationInfo;

	public List<corporation_department> getCorporationDepart() {
		return corporationDepart;
	}

	public void setCorporationDepart(List<corporation_department> corporationDepart) {
		this.corporationDepart = corporationDepart;
	}

	public corporation_information getCorporationInfo() {
		return corporationInfo;
	}

	public void setCorporationInfo(corporation_information corporationInfo) {
		this.corporationInfo = corporationInfo;
	}

}
