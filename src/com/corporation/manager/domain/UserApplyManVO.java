package com.corporation.manager.domain;

import java.util.List;

public class UserApplyManVO {
	private List<ApplyUserVO> listUser;
	private int totalPage; // 总页数
	private int pageSize; // 一页显示条数
	private int currPage; // 当前页数
	private int totalSize; // 数据总数
	private String timeSort;
	private corporation_information corporationInfo;

	public String getTimeSort() {
		return timeSort;
	}

	public void setTimeSort(String timeSort) {
		this.timeSort = timeSort;
	}

	public List<ApplyUserVO> getListUser() {
		return listUser;
	}

	public void setListUser(List<ApplyUserVO> listUser) {
		this.listUser = listUser;
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

	public corporation_information getCorporationInfo() {
		return corporationInfo;
	}

	public void setCorporationInfo(corporation_information corporationInfo) {
		this.corporationInfo = corporationInfo;
	}

}
