package com.corporation.corInfo.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.corporation.corInfo.dao.UserInfoDao;
import com.corporation.corInfo.domain.UserInfo;
import com.corporation.corInfo.domain.page_list_userInfoVO;

public class UserInfoDaoImpl implements UserInfoDao{
private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	//用户登录
	@Override
	public UserInfo Userlogin(String user_username) {
		  String hql = "from UserInfo where user_username=?";
		  Query query = getSession().createQuery(hql);
		  query.setString(0, user_username);
		  UserInfo user;
		  List<UserInfo> list = query.list();
		  System.out.println(list);
		  if(list.size()>0){
			  user=list.get(0);
			  return user;
		  }else {
			return null;
		}
	}
	
	//不分页的查询
	@Override
	public List<UserInfo> getAllUser() {
		// TODO Auto-generated method stub
		String hql = "FROM UserInfo a";
		return getSession().createQuery(hql).list();
	}
	
	//修改
	@Override
	public void saveOrUpdate(UserInfo userInfo) {
		// TODO Auto-generated method stub
		getSession().saveOrUpdate(userInfo);
	}
	
	//删除
	@Override
	public void delete_User(String user_id) {
		// TODO Auto-generated method stub
		String hql="DELETE FROM UserInfo u WHERE u.user_id=?";
		getSession().createQuery(hql).setString(0, user_id).executeUpdate();
	}


	//回显的时候查询
	@Override
	public UserInfo getId(String user_id) {
		// TODO Auto-generated method stub
		return (UserInfo) getSession().get(UserInfo.class,user_id);
	}

	
	
	
	//分页版本的查询
	@Override
	public List<UserInfo> listUserInfoByPage(page_list_userInfoVO page_list_userInfo) {
		// TODO Auto-generated method stub
		System.out.println(page_list_userInfo);
		Session session = getSession();
		String hql = "FROM UserInfo";
		System.out.println("hql:" + hql);
		Query query = session.createQuery(hql);
		System.out.println(page_list_userInfo.getPageIndex());
		System.out.println(page_list_userInfo.getPageSize());
		
		query.setFirstResult((page_list_userInfo.getPageIndex()-1)*page_list_userInfo.getPageSize());
		query.setMaxResults(page_list_userInfo.getPageSize());
		List<UserInfo> userInfoList = query.list();
		System.out.println(userInfoList);

		session.clear();
		return userInfoList;
	}
	
	/*
	 * 获取总的记录数
	 */
	@Override
	public int getUserInfoTotalRecords(page_list_userInfoVO page_list_userInfo) {
		// TODO Auto-generated method stub
		Session session = getSession();
		String hql = "select count(*) from UserInfo";
		Query query = session.createQuery(hql);

		int count = ((Number) query.uniqueResult()).intValue();
		return count;
	}
	
	
	

}
