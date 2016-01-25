package hxl.insist.oa.view.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class FunctionListAction extends ActionSupport {
	
	/**
	 * 学校功能列表页面
	 */
	public String schoolList() {
		return "schoolFunctionList";
	}
	
	/**
	 * 团队功能列表页面
	 */
	public String teamList() {
		return "teamFunctionList";

	}
	/**
	 * 评审功能列表页面
	 */
	public String reviewerList() {
		return "reviewerFunctionList";
	}
	
	/**
	 * root用户功能列表页面
	 */
	public String rootList() {
		return "rootFunctionList";
	}
}
