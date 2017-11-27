package com.corporation.corInfo.domain;

import java.util.List;

public class page_list_userInfoVO {
	
	//当前页
	private int pageIndex = 1;		//当前页
	
	private int totalRecords = 0;	//总记录数

	private int pageSize = 8;		//每个页面显示最多显示的记录数

	private int totalPages = 1;		//总页数

	private boolean HavePrePage = false;   //上一页
	private boolean HaveNextPage = false;  //下一页

	private List<UserInfoDTO> userInfoDTOList;

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public boolean isHavePrePage() {
		return HavePrePage;
	}

	public void setHavePrePage(boolean havePrePage) {
		HavePrePage = havePrePage;
	}

	public boolean isHaveNextPage() {
		return HaveNextPage;
	}

	public void setHaveNextPage(boolean haveNextPage) {
		HaveNextPage = haveNextPage;
	}

	public List<UserInfoDTO> getUserInfoDTOList() {
		return userInfoDTOList;
	}

	public void setUserInfoDTOList(List<UserInfoDTO> userInfoDTOList) {
		this.userInfoDTOList = userInfoDTOList;
	}

	@Override
	public String toString() {
		return "page_list_userInfoVO [pageIndex=" + pageIndex + ", totalRecords=" + totalRecords + ", pageSize="
				+ pageSize + ", totalPages=" + totalPages + ", HavePrePage=" + HavePrePage + ", HaveNextPage="
				+ HaveNextPage + ", userInfoDTOList=" + userInfoDTOList + "]";
	}

	

	
	


	



}
