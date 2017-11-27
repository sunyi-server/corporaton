package com.corporation.login.action;

import java.util.Map;

import com.corporation.manager.domain.user_information;
import com.corporation.manager.service.CorporationInforService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/*
 * ��¼��֤������
 */

public class LoginInterceptor extends AbstractInterceptor {

	private CorporationInforService corporationInforService;

	public CorporationInforService getCorporationInforService() {
		return corporationInforService;
	}

	public void setCorporationInforService(CorporationInforService corporationInforService) {
		this.corporationInforService = corporationInforService;
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		// ͨ��ActionInvocaion�õ�ActionContextͨ�������Ļ��session�ȶ���
		ActionContext ac = invocation.getInvocationContext();
		Map session = ac.getSession();
		String user_id = (String) session.get("user_id");
		if (user_id != null) {
			System.out.println("��½��֤ͨ��");
			user_information ui = corporationInforService.getUserById(user_id);
			ActionContext.getContext().getValueStack().set("userInVO", ui);
			return invocation.invoke();
		}
		System.out.println("û���û������뵽��½");
		return "login";
	}

}
