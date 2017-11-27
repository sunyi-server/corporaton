package com.corporation.login.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.corporation.manager.domain.member_information;
import com.corporation.manager.service.CorporationInforService;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/*
 * 社团内角色判断
 * 每次进入社团都通过session来判断对应角色，选择哪些侧边栏可以出来
 * 
 */
public class CorporationRoleInterceptor extends AbstractInterceptor {

	CorporationInforService corporationInforService;

	public CorporationInforService getCorporationInforService() {
		return corporationInforService;
	}

	public void setCorporationInforService(CorporationInforService corporationInforService) {
		this.corporationInforService = corporationInforService;
	}

	// ------------------------------------------通过用户id获取，社团名，判断用户在社团中的角色--------------------------------------------
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		String user_id = (String) invocation.getInvocationContext().getSession().get("user_id");
		HttpServletRequest request = ServletActionContext.getRequest();
		String corporationName = request.getParameter("member_corporation");
		System.out.println(corporationName.toString());
		member_information mi = corporationInforService.getMemberByUserId(user_id, corporationName.toString());
		invocation.getInvocationContext().getValueStack().set("currMember", mi);
		return invocation.invoke();
	}

}
