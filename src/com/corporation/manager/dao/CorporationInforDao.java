package com.corporation.manager.dao;

import java.util.List;

import com.corporation.manager.domain.corporation_department;
import com.corporation.manager.domain.corporation_information;
import com.corporation.manager.domain.member_information;
import com.corporation.manager.domain.user_information;
import com.corporation.manager.domain.user_into_corporation;

public interface CorporationInforDao {
	public int getTotalSizeByConditionPage(String startTime, String endTime, String name, String member_corporation,
			String queryDepartment, String queryMemberStatus);

	public List<member_information> getMemberByConditionPage(String startTime, String endTime, String name,
			String timeSort, int currPage, int pageSize, String member_corporation, String queryDepartment,
			String queryMemberStatus);

	public String deleteMember(String memberId);

	public List<corporation_department> getDepartmentByCorporationName(String corporation_name);

	public void addNewMember(member_information member_information);

	public List<member_information> getMemberByCondition(String startTime, String endTime, String member_corporation,
			String queryDepartment, String queryMemberStatus, String queryMemberSex);

	public member_information getMemberById(String memberId);

	public void updateMemberInfo(member_information mi);

	public member_information getMemberByUserId(String user_id, String corporationName);

	public List<user_into_corporation> getCorporationByUserId(String user_id);

	public user_information getUserById(String user_id);

	public corporation_information getCorporationByName(String corporationName);

	public int getApplyUserCount(String corporationName);

	public List<user_into_corporation> getApplyUser(String corporationName, int currPage, String timeSort);

	public String rejectApply(String into_id);

	public List<member_information> getMemberByUser(String member_corporation);

	public void setMemberUser(String currUser, String currMember);

	public void setUserApplyStatus(String into_id);

	public String getApplyUserById(String into_id);

	public String validateDepartment(String member_corporation, String department_name);

	public void saveDepartment(corporation_department cd);

	public corporation_department getDepartmentById(String department_id);

	public void updateDepartment(String department_id, String department_introduce);

	public void deleteDepartment(String department_id);

	public void changeMemberRole(String member_id, String member_name);

	public void deleteMemberDepartment(String member_corporation, String department_role);

	public void deleteIntoCor(String memberId, String member_corporation);

	public void updateCorInfo(corporation_information ci);
}
