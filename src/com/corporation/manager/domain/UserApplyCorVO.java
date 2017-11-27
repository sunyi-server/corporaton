package com.corporation.manager.domain;

import java.util.List;

public class UserApplyCorVO {
	private List<user_into_corporation> userInCorporation;
	private int totalPage; // 总页数
	private int pageSize; // 一页显示条数
	private int currPage; // 当前页数
	private int totalSize; // 数据总数
	private String queryName;// 查询的社团名字
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
