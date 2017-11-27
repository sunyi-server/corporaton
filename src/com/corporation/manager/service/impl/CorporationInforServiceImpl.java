package com.corporation.manager.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.corporation.manager.dao.CorporationInforDao;
import com.corporation.manager.domain.ApplyUserVO;
import com.corporation.manager.domain.CorporationDepartmentManVO;
import com.corporation.manager.domain.CorporationMemberInforVO;
import com.corporation.manager.domain.UserApplyManVO;
import com.corporation.manager.domain.corporation_department;
import com.corporation.manager.domain.corporation_information;
import com.corporation.manager.domain.member_information;
import com.corporation.manager.domain.user_information;
import com.corporation.manager.domain.user_into_corporation;
import com.corporation.manager.service.CorporationInforService;

import util.TimeUtil;
import util.uuid;

public class CorporationInforServiceImpl implements CorporationInforService {
	private CorporationInforDao corporationInforDao;

	public CorporationInforDao getCorporationInforDao() {
		return corporationInforDao;
	}

	public void setCorporationInforDao(CorporationInforDao corporationInforDao) {
		this.corporationInforDao = corporationInforDao;
	}

	// -------------------------------------------展示会员---------------------------------------------------------------
	@Override
	public CorporationMemberInforVO getMemberByCondition(String startTime, String endTime, String name, String timeSort,
			int currPage, String member_corporation, String queryDepartment, String queryMemberStatus) {
		// TODO Auto-generated method stub
		System.out.println("service");
		CorporationMemberInforVO memberVO = new CorporationMemberInforVO();

		int totalPage;
		int countInt = corporationInforDao.getTotalSizeByConditionPage(startTime, endTime, name, member_corporation,
				queryDepartment, queryMemberStatus);

		double count = (double) countInt;
		double pageSize = 10.0;
		totalPage = (int) Math.ceil(count / pageSize);
		memberVO.setCurrPage(currPage);
		memberVO.setTotalPage(totalPage);
		System.out.println("共有:" + totalPage);
		memberVO.setPageSize((int) pageSize);
		memberVO.setQueryName(name);
		memberVO.setQueryStartTime(startTime);
		memberVO.setQueryEndTime(endTime);
		memberVO.setTotalSize(countInt);
		memberVO.setQuerySort(timeSort);
		memberVO.setCurrCorporation(member_corporation);
		List<member_information> list = corporationInforDao.getMemberByConditionPage(startTime, endTime, name, timeSort,
				currPage, (int) pageSize, member_corporation, queryDepartment, queryMemberStatus);

		List<corporation_department> listDepartment = corporationInforDao
				.getDepartmentByCorporationName(member_corporation);
		memberVO.setDepartment(listDepartment);
		memberVO.setList(list);

		return memberVO;
	}

	// -------------------------------------------删除会员---------------------------------------------------------------
	@Override
	public String deleteMember(String memberId, String member_corporation) {
		// TODO Auto-generated method stub
		String result;
		member_information mi = corporationInforDao.getMemberById(memberId);
		// 删除会员的所属社团
		if (mi.getMember_user() != "") {
			corporationInforDao.deleteIntoCor(mi.getMember_user(), member_corporation);
		}

		String getResult = corporationInforDao.deleteMember(memberId);
		if ("1".equals(getResult) || getResult == "1") {
			result = "删除成功";
			return result;
		} else {
			result = "删除失败";
			return result;
		}
	}

	// ---------------------------------------------增加会员----------------------------------------------------------------
	@Override
	public void addNewMember(member_information member_information) {
		// TODO Auto-generated method stub
		corporationInforDao.addNewMember(member_information);
	}

	// ----------------------------------------------按条件获得会员---------------------------------------------------------
	@Override
	public List<member_information> getMemberByCondition(String startTime, String endTime, String member_corporation,
			String queryDepartment, String queryMemberStatus, String queryMemberSex) {
		// TODO Auto-generated method stub
		List<member_information> list = corporationInforDao.getMemberByCondition(startTime, endTime, member_corporation,
				queryDepartment, queryMemberStatus, queryMemberSex);
		return list;
	}

	// ------------------------------------------------通过id获得会员-------------------------------------------------------
	@Override
	public member_information getMemberById(String memberId) {
		// TODO Auto-generated method stub
		member_information mi = corporationInforDao.getMemberById(memberId);
		System.out.println(mi.getMember_user() + "用户名为");
		return mi;
	}

	// --------------------------------------------------修改会员--------------------------------------------------------------
	@Override
	public void updateMemberInfo(member_information mi) {
		// TODO Auto-generated method stub
		corporationInforDao.updateMemberInfo(mi);
	}

	// -------------------------------------------通过用户id获得社团会员-------------------------------------------------------
	@Override
	public member_information getMemberByUserId(String user_id, String corporationName) {
		// TODO Auto-generated method stub
		member_information mi = corporationInforDao.getMemberByUserId(user_id, corporationName);
		return mi;
	}

	// ----------------------------------------------------获得用户所属社团------------------------------------------------------
	@Override
	public List<user_into_corporation> getCorporationByUserId(String user_id) {
		// TODO Auto-generated method stub
		List<user_into_corporation> list = corporationInforDao.getCorporationByUserId(user_id);
		return list;
	}

	// -------------------------------------------------------通过用户id获得用户信息-----------------------------------------
	@Override
	public user_information getUserById(String user_id) {
		// TODO Auto-generated method stub
		user_information ui = corporationInforDao.getUserById(user_id);
		return ui;
	}

	// --------------------------------------------------获得待审核用户--------------------------------------------------
	@Override
	public UserApplyManVO getUserApplyManVO(int currPage, String timeSort, String corporationName) {
		// TODO Aut0o-generated method stub

		// 获得当前社团信息
		corporation_information ci = corporationInforDao.getCorporationByName(corporationName);

		// 获得待审核用户

		// 用户总数
		double pageSize = 10.0;
		int totalCount = corporationInforDao.getApplyUserCount(corporationName);
		double totalSize = (double) totalCount;
		int totalPage = (int) Math.ceil(totalSize / pageSize);

		// 获得用户分页
		List<user_into_corporation> list = corporationInforDao.getApplyUser(corporationName, currPage, timeSort);
		List<ApplyUserVO> listVO = new ArrayList<ApplyUserVO>();
		// 通过用户id创建VO类赋予用户名称
		for (user_into_corporation user_into_corporation : list) {
			ApplyUserVO auv = new ApplyUserVO();
			user_information ui = corporationInforDao.getUserById(user_into_corporation.getInto_man());
			auv.setInto_id(user_into_corporation.getInto_id());
			auv.setInto_status(user_into_corporation.getInto_status());
			auv.setInto_time(user_into_corporation.getInto_time());
			auv.setUser_name(ui.getUser_name());
			listVO.add(auv);
		}
		// 创建整体VO

		UserApplyManVO uam = new UserApplyManVO();
		uam.setCorporationInfo(ci);
		uam.setCurrPage(currPage);
		uam.setListUser(listVO);
		uam.setPageSize((int) pageSize);
		uam.setTotalPage(totalPage);
		uam.setTotalSize(totalCount);
		uam.setTimeSort(timeSort);
		return uam;
	}

	// -----------------------------------------------------驳回申请--------------------------------------------------------
	@Override
	public String rejectApply(String into_id) {
		// TODO Auto-generated method stub
		String currCorporation = corporationInforDao.rejectApply(into_id);
		return currCorporation;
	}

	// ---------------------------------------------------获得没有绑定你的会员-------------------------------------------------------------------
	@Override
	public List<member_information> getMemberByUser(String member_corporation) {
		// TODO Auto-generated method stub
		List<member_information> list = corporationInforDao.getMemberByUser(member_corporation);
		return list;
	}

	// ------------------------------------------------------------同意申请----------------------------------------------

	@Override
	public void agreeApply(String into_id, String userMember) {
		// TODO Auto-generated method stub
		String currUser = corporationInforDao.getApplyUserById(into_id);
		corporationInforDao.setMemberUser(currUser, userMember);
		corporationInforDao.setUserApplyStatus(into_id);
	}

	// ------------------------------------------------获得部门管理VO-------------------------------------------------
	@Override
	public CorporationDepartmentManVO getDepartmentVO(String member_corporation) {
		// TODO Auto-generated method stub
		List<corporation_department> list = corporationInforDao.getDepartmentByCorporationName(member_corporation);
		corporation_information ci = corporationInforDao.getCorporationByName(member_corporation);
		CorporationDepartmentManVO dpv = new CorporationDepartmentManVO();
		dpv.setCorporationDepart(list);
		dpv.setCorporationInfo(ci);
		return dpv;
	}

	// -------------------------------------------------验证部门名---------------------------------------------------------
	@Override
	public String validateDepartment(String member_corporation, String department_name) {
		// TODO Auto-generated method stub
		String result = corporationInforDao.validateDepartment(member_corporation, department_name);

		return result;
	}
	// ----------------------------------------------------新增部门-------------------------------------------------

	@Override
	public void addDepartment(corporation_department cd) {
		// TODO Auto-generated method stub
		cd.setDepartment_create_time(TimeUtil.getStringDay());
		cd.setDepartment_gmt_create(TimeUtil.getStringSecond());
		cd.setDepartment_gmt_modified(TimeUtil.getStringSecond());
		cd.setDepartment_id(uuid.getUuid());
		corporationInforDao.saveDepartment(cd);
	}

	// ----------------------------------------------------通过id获得部门信息----------------------------------------------

	@Override
	public corporation_department getDepartmentById(String department_id) {
		// TODO Auto-generated method stub
		corporation_department cd = corporationInforDao.getDepartmentById(department_id);
		return cd;
	}
	// --------------------------------------------------修改部门信息——-----------------------------------------------------

	@Override
	public void updateDepartment(String department_id, String department_introducce) {
		// TODO Auto-generated method stub
		corporationInforDao.updateDepartment(department_id, department_introducce);
	}

	// -----------------------------------------------------删除部门信息----------------------------------------------------
	@Override
	public void deleteDepartment(String department_id, String member_corporation) {
		// TODO Auto-generated method stub
		corporation_department department = corporationInforDao.getDepartmentById(department_id);
		corporationInforDao.deleteDepartment(department_id);
		corporationInforDao.deleteMemberDepartment(member_corporation, department.getDepartment_name());
	}

	// ------------------------------------------------改变会员角色------------------------------------------
	@Override
	public void changeMemberRole(String member_id, String member_role) {
		// TODO Auto-generated method stub
		corporationInforDao.changeMemberRole(member_id, member_role);
	}

	// ---------------------------------------------获得社团信息-----------------------------------------------
	@Override
	public corporation_information getCorporationInfor(String member_corporation) {
		// TODO Auto-generated method stub
		corporation_information ci = corporationInforDao.getCorporationByName(member_corporation);
		return ci;
	}

	@Override
	public void updateCorInfo(corporation_information ci) {
		// TODO Auto-generated method stub
		corporationInforDao.updateCorInfo(ci);
	}
}
