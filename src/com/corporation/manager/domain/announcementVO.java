package com.corporation.manager.domain;

import java.util.List;

public class announcementVO {
	private String queryStartTime; // ɸѡ��ʼʱ��
	private String queryEndTime; // ɸѡ����ʱ��
	private String queryName; // ��ѯ����
	private String querySort; // ��ѯ����ʽ desc���� asc����
	private String currCorporation; // ��ǰ����
	private int totalPage; // ��ҳ��
	private int pageSize; // һҳ��ʾ����
	private int currPage; // ��ǰҳ��
	private int totalSize; // ��������
	private List<corporation_announcement> announcement;

	public String getQueryStartTime() {
		return queryStartTime;
	}

	public void setQueryStartTime(String queryStartTime) {
		this.queryStartTime = queryStartTime;
	}

	public String getQueryEndTime() {
		return queryEndTime;
	}

	public void setQueryEndTime(String queryEndTime) {
		this.queryEndTime = queryEndTime;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public String getQuerySort() {
		return querySort;
	}

	public void setQuerySort(String querySort) {
		this.querySort = querySort;
	}

	public String getCurrCorporation() {
		return currCorporation;
	}

	public void setCurrCorporation(String currCorporation) {
		this.currCorporation = currCorporation;
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

	public List<corporation_announcement> getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(List<corporation_announcement> announcement) {
		this.announcement = announcement;
	}

}
