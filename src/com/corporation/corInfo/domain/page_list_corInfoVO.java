package com.corporation.corInfo.domain;

import java.util.List;

public class page_list_corInfoVO {

	private int pageIndex = 1;

	private int totalRecords = 0;

	private int pageSize = 10;

	private int totalPages = 1;

	private boolean HavePrePage = false;
	private boolean HaveNextPage = false;
	private searchCorInfoListDTO search;

	private List<CorInfoDTO> corInfoDTOList;
private List<MessageDTO> messageDTOList;
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

	public searchCorInfoListDTO getSearch() {
		return search;
	}

	public void setSearch(searchCorInfoListDTO search) {
		this.search = search;
	}

	public List<CorInfoDTO> getCorInfoDTOList() {
		return corInfoDTOList;
	}

	public void setCorInfoDTOList(List<CorInfoDTO> corInfoDTOList) {
		this.corInfoDTOList = corInfoDTOList;
	}

	public List<MessageDTO> getMessageDTOList() {
		return messageDTOList;
	}

	public void setMessageDTOList(List<MessageDTO> messageDTOList) {
		this.messageDTOList = messageDTOList;
	}

@Override
	public String toString() {
		return "page_list_corInfoVO [pageIndex=" + pageIndex + ", totalRecords=" + totalRecords + ", pageSize="
				+ pageSize + ", totalPages=" + totalPages + ", HavePrePage=" + HavePrePage + ", HaveNextPage="
				+ HaveNextPage + ", search=" + search + ", corInfoDTOList=" + corInfoDTOList + ", messageDTOList="
				+ messageDTOList + "]";
	}



}
