package hxl.insist.oa.util;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import hxl.insist.oa.domain.Account;

@SuppressWarnings("serial")
public class CheckPrivilegeInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// 获取信息
		Account account = (Account) ActionContext.getContext().getSession().get("account"); // 当前登录用户
		String namespace = invocation.getProxy().getNamespace();
		String actionName = invocation.getProxy().getActionName();
		String privUrl = namespace + "/" +actionName; // 对应的权限URL
		
		//如果是安装系统就放行
		if ("//init".startsWith(privUrl)) {
			return invocation.invoke();
		}
		
		// 如果未登录
		if (account == null) {
			if (privUrl.startsWith("//system_login")) { 
				// 如果是去登录，就放行
				return invocation.invoke();
			} else {
				// 如果不是去登录，就转到登录页面
				return "loginUI";
			}
		}
		
		//如果是登出系统就放行
		if(privUrl.startsWith("//system_logout")) {
			return invocation.invoke();
		}
		
		//判断登入的账号是否与自己的权限相匹配
		if(account.getRole() == 0 && privUrl.startsWith("/school")) {
			return invocation.invoke();
		} else if(account.getRole() == 1 && privUrl.startsWith("/team")) {
			return invocation.invoke();
		} else if(account.getRole() == 2 && privUrl.startsWith("/reviewer")) {
			return invocation.invoke();
		} else if(account.getRole() == 3 && privUrl.startsWith("/root")) {
			return invocation.invoke();
		}
		ServletActionContext.getRequest().setAttribute("errMsg", "您没有权限访问，如有问题，请联系管理员！！！");
		return "errMsg";
	}

}
