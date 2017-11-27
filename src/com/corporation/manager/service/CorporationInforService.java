package com.corporation.manager.service;

import java.util.List;

import com.corporation.manager.domain.CorporationDepartmentManVO;
import com.corporation.manager.domain.CorporationMemberInforVO;
import com.corporation.manager.domain.UserApplyManVO;
import com.corporation.manager.domain.corporation_department;
import com.corporation.manager.domain.corporation_information;
import com.corporation.manager.domain.member_information;
import com.corporation.manager.domain.user_information;
import com.corporation.manager.domain.user_into_corporation;

public interface CorporationInforService {
	public CorporationMemberInforVO getMemberByCondition(String startTime, String endTime, String name, String timeSort,
			int currPage, String member_corporation, String queryDepartment, String queryMemberStatus);

	public String deleteMember(String memberId, String member_corporation);

	public void addNewMember(member_information member_information);

	public List<member_information> getMemberByCondition(String startTime, String endTime, String member_corporation,
			String queryDepartment, String queryMemberSex, String queryMemberStatus);

	public member_information getMemberById(String memberId);

	public void updateMemberInfo(member_information mi);

	public member_information getMemberByUserId(String user_id, String corporationName);

	public List<user_into_corporation> getCorporationByUserId(String user_id);

	public user_information getUserById(String user_id);

	public UserApplyManVO getUserApplyManVO(int currPage, String timeSort, String corporatioName);

	public String rejectApply(String into_id);

	public List<member_information> getMemberByUser(String member_corporation);

	public void agreeApply(String currUser, String userMember);

	public CorporationDepartmentManVO getDepartmentVO(String member_corporation);

	public String validateDepartment(String member_corporation, String department_name);

	public void addDepartment(corporation_department cd);

	public corporation_department getDepartmentById(String department_id);

	public void updateDepartment(String department_id, String department_introducce);

	public void deleteDepartment(String department_id, String member_corporation);

	public void changeMemberRole(String member_id, String member_role);

	public corporation_information getCorporationInfor(String member_corporation);

	public void updateCorInfo(corporation_information ci);
}
