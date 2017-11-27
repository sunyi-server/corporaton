package com.corporation.manager.domain;

import java.util.List;

public class UserApplyCorVO {
	private List<user_into_corporation> userInCorporation;
	private int totalPage; // ��ҳ��
	private int pageSize; // һҳ��ʾ����
	private int currPage; // ��ǰҳ��
	private int totalSize; // ��������
	private String queryName;// ��ѯ����������
	private List<corporation_information> corporationInfo;

	public List<user_into_corporation> getUserInCorporation() {
		return userInCorporation;
	}

	public void setUserInCorporation(List<user_into_corporation> userInCorporation) {
		this.userInCorporation = userInCorporation;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public List<corporation_information> getCorporationInfo() {
		return corporationInfo;
	}

	public void setCorporationInfo(List<corporation_information> corporationInfo) {
		this.corporationInfo = corporationInfo;
	}

}
