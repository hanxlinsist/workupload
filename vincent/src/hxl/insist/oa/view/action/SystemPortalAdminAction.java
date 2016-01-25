package hxl.insist.oa.view.action;

import java.util.Map;

import hxl.insist.oa.base.BaseAction;
import hxl.insist.oa.domain.Account;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class SystemPortalAdminAction extends BaseAction<Account> {
	
	/** 登录页面 */
	public String loginUI() throws Exception {
		return "loginUI";
	}
	
	/** 登录 */
	public String login() throws Exception {
		Account account = accountService.findByLoginNameAndPasswordAndRole(model.getUserName(), model.getPassword(),model.getRole());
		if (account == null) {
			addFieldError("login", "验证信息不正确！");
			return "loginUI";
		} else if(account.getStatus() == 0) {
			addFieldError("login", "系统暂未开放，请等待管理员开放系统");
			return "loginUI";
		} else {
			HttpServletRequest request = ServletActionContext.getRequest();
			// 登录用户
			Map<String, Object> session = ActionContext.getContext().getSession();
			session.put("account", account);
			if(account.getRole() == 0) {
				request.setAttribute("siteUrl", "school/functionlist_schoolList");
				session.put("functionListURL", "school/functionlist_schoolList");
			} else if(account.getRole() == 1) {
				request.setAttribute("siteUrl", "team/functionlist_teamList");
				session.put("functionListURL", "team/functionlist_teamList");
			} else if(account.getRole() == 2) {
				request.setAttribute("siteUrl", "reviewer/functionlist_reviewerList");
				session.put("functionListURL", "reviewer/functionlist_reviewerList");
			} else if (account.getRole() == 3) {
				request.setAttribute("siteUrl", "root/functionlist_rootList");
				session.put("functionListURL", "root/functionlist_rootList");
			}
			return "siteUrl";
		}
	}
	
	/** 注销 */
	public String logout() throws Exception {
		ActionContext.getContext().getSession().remove("account");
		ServletActionContext.getRequest().setAttribute("siteUrl", "system_loginUI");
		return "siteUrl";
	}
}
