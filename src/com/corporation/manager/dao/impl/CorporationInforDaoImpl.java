package com.corporation.manager.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.corporation.manager.dao.CorporationInforDao;
import com.corporation.manager.domain.corporation_department;
import com.corporation.manager.domain.corporation_information;
import com.corporation.manager.domain.member_information;
import com.corporation.manager.domain.user_information;
import com.corporation.manager.domain.user_into_corporation;

import util.TimeUtil;

public class CorporationInforDaoImpl implements CorporationInforDao {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	// --------------------------------------------------�������Ŀ��-------------------------------------------------------
	@Override
	@Transactional
	public int getTotalSizeByConditionPage(String startTime, String endTime, String name, String member_coporation,
			String queryDepartment, String queryMemberStatus) {
		// TODO Auto-generated method stub
		String searchName = "%" + name + "%";
		queryDepartment = "%" + queryDepartment + "%";
		System.out.println("��������:" + queryDepartment);
		queryMemberStatus = "%" + queryMemberStatus + "%";
		String hql = "select count(*) from member_information where member_department like '" + queryDepartment
				+ "' and member_status like '" + queryMemberStatus + "' and member_corporation= '" + member_coporation
				+ "' and member_name like '" + searchName + "' and member_join_time >= '" + startTime
				+ "' and member_join_time <='" + endTime + "'";
		Query query = getSession().createQuery(hql);
		int count = ((Number) query.uniqueResult()).intValue();
		System.out.println(count);
		return count;
	}
	// --------------------------------------------------��ҳ�����ʾ��Ա-------------------------------------------------------

	@Override
	@Transactional
	public List<member_information> getMemberByConditionPage(String startTime, String endTime, String name,
			String timeSort, int currPage, int pageSize, String member_corporation, String queryDepartment,
			String queryMemberStatus) {
		// TODO Auto-generated method stub
		String searchName = "%" + name + "%";
		queryDepartment = "%" + queryDepartment + "%";
		queryMemberStatus = "%" + queryMemberStatus + "%";
		String hql = "from member_information where member_department like '" + queryDepartment
				+ "' and member_status like '" + queryMemberStatus + "' and member_corporation = '" + member_corporation
				+ "' and member_name like'" + searchName + "' and member_join_time >= '" + startTime
				+ "' and member_join_time <='" + endTime + "' order by member_join_time " + timeSort;
		System.out.println(hql);
		Query query = getSession().createQuery(hql).setFirstResult((currPage - 1) * pageSize).setMaxResults(pageSize);
		List<member_information> list = query.list();
		return list;
	}

	// --------------------------------------------------��û�Ա-------------------------------------------------------
	@Override
	@Transactional
	public List<member_information> getMemberByCondition(String startTime, String endTime, String member_corporation,
			String queryDepartment, String queryMemberStatus, String queryMemberSex) {
		// TODO Auto-generated method stub
		queryDepartment = "%" + queryDepartment + "%";
		queryMemberStatus = "%" + queryMemberStatus + "%";
		queryMemberSex = "%" + queryMemberSex + "%";
		System.out.println(
				startTime + endTime + member_corporation + queryDepartment + queryMemberSex + queryMemberStatus);
		String hql = "from member_information where member_department like '" + queryDepartment
				+ "' and member_status like '" + queryMemberStatus + "' and member_corporation = '" + member_corporation
				+ "' and member_join_time >= '" + startTime + "' and member_join_time <='" + endTime
				+ "' and member_sex like '" + queryMemberSex + "'";
		Query query = getSession().createQuery(hql);
		List<member_information> list = query.list();
		return list;
	}

	// -------------------------------------------------ɾ����Ա-------------------------------------------------------
	@Override
	@Transactional
	public String deleteMember(String memberId) {
		// TODO Auto-generated method stub
		String hql = "delete from member_information where member_id = '" + memberId + "'";
		Query query = getSession().createQuery(hql);
		String result = Integer.toString(query.executeUpdate()); // ����1ɾ���ɹ�
		return result;
	}

	// -------------------------------------------------ͨ����������ò���-----------------------------------------------
	@Override
	@Transactional
	public List<corporation_department> getDepartmentByCorporationName(String corporation_name) {
		System.out.println(corporation_name);
		String hql = "from corporation_department where department_include= '" + corporation_name + "'";
		Query query = getSession().createQuery(hql);
		List<corporation_department> list = query.list();
		return list;
	}

	// --------------------------------------------------���ӻ�Ա------------------------------------------------------
	@Override
	@Transactional
	public void addNewMember(member_information member_information) {
		// TODO Auto-generated method stub
		this.getSession().save(member_information);
	}

	// ---------------------------------------------------ͨ��id��û�Ա---------------------------------------------------
	@Override
	@Transactional
	public member_information getMemberById(String memberId) {
		// TODO Auto-generated method stub
		member_information mi = (member_information) getSession().get(member_information.class, memberId);
		return mi;
	}

	// -----------------------------------------------------�޸Ļ�Ա--------------------------------------------------------

	@Override
	@Transactional
	public void updateMemberInfo(member_information mi) {
		// TODO Auto-generated method stub
		getSession().saveOrUpdate(mi);
	}

	// ------------------------------------------------ͨ���û�id������Ż�Ա---------------------------------------------------
	@Override
	public member_information getMemberByUserId(String user_id, String corporationName) {
		// TODO Auto-generated method stub
		System.out.println("��ǰ���ص�����Ϊ" + corporationName);
		String hql = "from member_information where member_user = '" + user_id + "' and member_corporation = '"
				+ corporationName + "'";
		Query query = getSession().createQuery(hql);
		member_information mi = (member_information) query.list().get(0);
		return mi;
	}

	// -----------------------------------------------------����û�������-----------------------------------------------------
	@Override
	public List<user_into_corporation> getCorporationByUserId(String user_id) {
		// TODO Auto-generated method stub
		String hql = "from user_into_corporation where into_man ='" + user_id + "' and into_status = '2'";
		Query query = getSession().createQuery(hql);
		List<user_into_corporation> list = query.list();
		return list;
	}

	// ------------------------------------------------------------ͨ��id����û�---------------------------------------------
	@Override
	public user_information getUserById(String user_id) {
		// TODO Auto-generated method stub

		user_information ui = (user_information) getSession().get(user_information.class, user_id);

		return ui;
	}

	// ----------------------------------------------------------ͨ���������������������Ϣ------------------------------------------------
	@Override
	public corporation_information getCorporationByName(String corporationName) {
		// TODO Auto-generated method stub
		String hql = "from corporation_information where corporation_name = '" + corporationName + "'";
		Query query = getSession().createQuery(hql);
		corporation_information ci = (corporation_information) query.uniqueResult();
		return ci;
	}

	// ----------------------------------------------ͨ����������ô������Ա��----------------------------------------------------------------------
	@Override
	public int getApplyUserCount(String corporationName) {
		// TODO Auto-generated method stub

		String hql = "select count(*) from user_into_corporation where user_corporation_include ='" + corporationName
				+ "' and into_status='0'";
		Query query = getSession().createQuery(hql);
		int count = ((Number) query.uniqueResult()).intValue();
		return count;
	}

	// ==================================================��ҳ��ô������Ա--------------------------------------------------------------

	@Override
	public List<user_into_corporation> getApplyUser(String corporationName, int currPage, String timeSort) {
		// TODO Auto-generated method stub
		String hql = "from user_into_corporation where user_corporation_include = '" + corporationName
				+ "' and into_status='0' order by into_time " + timeSort + "";
		Query query = getSession().createQuery(hql).setFirstResult((currPage - 1) * 10).setMaxResults(10);
		List<user_into_corporation> list = query.list();
		return list;
	}

	// -----------------------------------------------------------��������--------------------------------------------------
	@Override
	public String rejectApply(String into_id) {
		// TODO Auto-generated method stub
		String hql = "update user_into_corporation set into_status='1' where into_id='" + into_id + "'";
		getSession().createQuery(hql).executeUpdate();
		String hql1 = "from user_into_corporation where into_id='" + into_id + "'";
		List<user_into_corporation> list = getSession().createQuery(hql1).list();
		return list.get(0).getUser_corporation_include();

	}
	// ---------------------------------------------------���û�а󶨵Ļ�Ա----------------------------------------------------------

	@Override
	public List<member_information> getMemberByUser(String member_corporation) {
		// TODO Auto-generated method stub
		String hql = "from member_information where member_corporation = '" + member_corporation
				+ "' and member_user =''";
		Query query = getSession().createQuery(hql);
		List<member_information> list = query.list();
		return list;
	}
	// -----------------------------------------------���û�Ա��Ϣ-------------------------------------------------------------

	@Override
	public void setMemberUser(String currUser, String currMember) {
		// TODO Auto-generated method stub
		String hql = "update member_information set member_user = '" + currUser + "' where member_id='" + currMember
				+ "'";
		getSession().createQuery(hql).executeUpdate();
	}

	// -------------------------------------------����������״̬--------------------------------------------------------------
	@Override
	public void setUserApplyStatus(String into_id) {
		// TODO Auto-generated method stub
		String hql = "update user_into_corporation set into_status ='" + 2 + "' where into_id='" + into_id + "'";
		getSession().createQuery(hql).executeUpdate();
	}

	@Override
	public String getApplyUserById(String into_id) {
		// TODO Auto-generated method stub
		user_into_corporation uic = (user_into_corporation) getSession().get(user_into_corporation.class, into_id);

		return uic.getInto_man();
	}

	// --------------------------------------------------��֤���Ŵ���--------------------------------------------------------------------
	@Override
	public String validateDepartment(String member_corporation, String department_name) {
		// TODO Auto-generated method stub
		String hql = "from corporation_department where department_include='" + member_corporation
				+ "' and department_name = '" + department_name + "'";
		List<corporation_department> list = getSession().createQuery(hql).list();
		return Integer.toString(list.size());
	}

	// ------------------------------------------------------���沿��-------------------------------------------------------
	@Override
	public void saveDepartment(corporation_department cd) {
		// TODO Auto-generated method stub
		getSession().save(cd);
	}
	// ---------------------------------------------------ͨ��id��ò�����Ϣ--------------------------------------------------

	@Override
	public corporation_department getDepartmentById(String department_id) {
		// TODO Auto-generated method stub

		corporation_department cd = (corporation_department) getSession().get(corporation_department.class,
				department_id);
		return cd;
	}
	// ---------------------------------------------------------------�޸Ĳ�����Ϣ----------------------------------------------------

	@Override
	public void updateDepartment(String department_id, String department_introduce) {
		// TODO Auto-generated method stub
		String hql = "update corporation_department set department_introduce = '" + department_introduce
				+ "' , department_gmt_modified ='" + TimeUtil.getStringSecond() + "' where department_id='"
				+ department_id + "'";
		getSession().createQuery(hql).executeUpdate();
	}

	// ------------------------------------------------ɾ������----------------------------------------------------
	@Override
	public void deleteDepartment(String department_id) {
		// TODO Auto-generated method stub
		String hql = "delete corporation_department where department_id = '" + department_id + "'";
		getSession().createQuery(hql).executeUpdate();
	}

	// --------------------------------------------------ɾ����Ա�Ĳ���----------------------------------------------------
	@Override
	public void deleteMemberDepartment(String member_corporation, String department_name) {
		// TODO Auto-generated method stub
		String hql = "update member_information set member_department  ='' where member_corporation = '"
				+ member_corporation + "' and member_department = '" + department_name + "'";
		getSession().createQuery(hql).executeUpdate();
	}

	// -------------------------------------------------�ı��Ա��ɫ------------------------------------------------------
	@Override
	public void changeMemberRole(String member_id, String member_role) {
		// TODO Auto-generated method stub
		String hql = "update member_information set member_role ='" + member_role + "' where member_id ='" + member_id
				+ "'";
		getSession().createQuery(hql).executeUpdate();
	}

	// -------------------------------------------ɾ����Ա��������--------------------------------------------------
	@Override
	public void deleteIntoCor(String memberId, String member_corporation) {
		// TODO Auto-generated method stub
		String hql = "delete user_into_corporation where into_man ='" + memberId + "' and user_corporation_include='"
				+ member_corporation + "'";
		getSession().createQuery(hql).executeUpdate();
	}

	@Override
	public void updateCorInfo(corporation_information ci) {
		// TODO Auto-generated method stub
		getSession().saveOrUpdate(ci);
	}

}
