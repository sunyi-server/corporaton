package com.corporation.manager.domain;

import java.util.List;

public class CorporationMemberInforVO {
	private String queryStartTime; // ɸѡ��ʼʱ��
	private String queryEndTime; // ɸѡ����ʱ��
	private String queryName; // ��ѯ����
	private String querySort; // ��ѯ����ʽ desc���� asc����
	private String currCorporation; // ��ǰ����
	private int totalPage; // ��ҳ��
	private int pageSize; // һҳ��ʾ����
	private int currPage; // ��ǰҳ��
	private int totalSize; // ��������
	private List<member_information> list; // ��Ա��Ϣ��
	private List<corporation_department> department; // ���Ų��ű�
	private String currMemberRole; // ��ǰ��Ա��ɫ

	public List<corporation_department> getDepartment() {
		return department;
	}

	public void setDepartment(List<corporation_department> department) {
		this.department = department;
	}

	public String getCurrCorporation() {
		return currCorporation;
	}

	public void setCurrCorporation(String currCorporation) {
		this.currCorporation = currCorporation;
	}

	public String getQuerySort() {
		return querySort;
	}

	public String getCurrMemberRole() {
		return currMemberRole;
	}

	public void setCurrMemberRole(String currMemberRole) {
		this.currMemberRole = currMemberRole;
	}

	public void setQuerySort(String querySort) {
		this.querySort = querySort;
	}

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

	public List<member_information> getList() {
		return list;
	}

	public void setList(List<member_information> list) {
		this.list = list;
	}

}
