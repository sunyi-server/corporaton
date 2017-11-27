package com.corporation.login.action;

import java.util.Map;

import com.corporation.manager.domain.user_information;
import com.corporation.manager.service.CorporationInforService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/*
 * 登录验证拦截器
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
		// 通过ActionInvocaion得到ActionContext通过上下文获得session等对象
		ActionContext ac = invocation.getInvocationContext();
		Map session = ac.getSession();
		String user_id = (String) session.get("user_id");
		if (user_id != null) {
			System.out.println("登陆验证通过");
			user_information ui = corporationInforService.getUserById(user_id);
			ActionContext.getContext().getValueStack().set("userInVO", ui);
			return invocation.invoke();
		}
		System.out.println("没有用户，进入到登陆");
		return "login";
	}

}
