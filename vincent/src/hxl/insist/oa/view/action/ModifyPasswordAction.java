package hxl.insist.oa.view.action;

import hxl.insist.oa.base.BaseAction;
import hxl.insist.oa.domain.Account;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class ModifyPasswordAction extends BaseAction<Account> {
	
	/**
	 * 新密码
	 */
	private String newPassword;
	
	/**
	 * 修改密码界面
	 * @return
	 * @throws Exception
	 */
	public String modifyPwdUI() throws Exception {
		return "modifyPwdUI";
	}
	
	/**
	 * 修改密码
	 * @return
	 * @throws Exception
	 */
	public String modifyPwd() throws Exception {
		Account account = getSessionAccount();
		if(model.getPassword() != null) {
			String md5Digest = DigestUtils.md5Hex(model.getPassword());
			if(!account.getPassword().equals(md5Digest)) {
				addFieldError("errMsg", "您输入的原密码不正确");
				return "modifyPwdUI";
			}
			account.setPassword(DigestUtils.md5Hex(newPassword));
			accountService.update(account);
		}
		
		HttpServletRequest request = ServletActionContext.getRequest();
		if(account.getRole() == 0)
			request.setAttribute("siteUrl", "school/functionlist_schoolList");
		else if(account.getRole() == 1)
			request.setAttribute("siteUrl", "team/functionlist_teamList");
		else if(account.getRole() == 2)
			request.setAttribute("siteUrl", "reviewer/functionlist_reviewerList");
		else
			request.setAttribute("siteUrl", "root/functionlist_rootList");
		return "siteUrl";
	}
	
	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
