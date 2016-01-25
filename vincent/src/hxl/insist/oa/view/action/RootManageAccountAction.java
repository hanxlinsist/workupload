package hxl.insist.oa.view.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Action;

import hxl.insist.oa.Constants;
import hxl.insist.oa.base.BaseAction;
import hxl.insist.oa.domain.Account;
import hxl.insist.oa.domain.notpersistent.QueryResult;
import hxl.insist.oa.domain.page.DataByRoot;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class RootManageAccountAction extends BaseAction<Account> {
	
	private String rows;//每页显示的记录数
    private String page;//当前第几页   
    private JSONObject resultObj;//返回的json
    
    private String projectName;
    
    /**
     * 所有学校管理者账号界面
     */
    public String listUI() {
    	return "accountList";
    }
	/**
	 * 显示所有学校管理者账号
	 */
	public String list() {
		//Hibernate lazy loading problem
		JsonConfig cfg = new JsonConfig();
		cfg.setExcludes(new String[]{"handler","hibernateLazyInitializer"});
		
		//当前页
		int currentPage = Integer.parseInt((page == null || page == "0") ? "1":page);
		//每页显示条数
		int maxresult = Integer.parseInt((rows == null || rows == "0") ? "10":rows);
		//每页的开始记录
		int firstindex = (currentPage - 1) * maxresult; 
		
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		StringBuffer wherehql = new StringBuffer("o.role = ?");
		List<Object> queryParams = new ArrayList<Object>();
		queryParams.add(Constants.adminRole);
		if (model.getUserName() != null && !"".equals(model.getUserName().trim())) {
			wherehql.append(" and ").append(" o.userName like ? ");
			queryParams.add("%" + model.getUserName() + "%");
		} 
		
		if (projectName != null && !"".equals(projectName.trim())) {
			wherehql.append(" and ").append(" o.project.projectName like ? ");
			queryParams.add("%" + projectName + "%");
		}
		
		QueryResult<Account> queryResult = accountService.getScrollData(Account.class, firstindex, maxresult, wherehql.toString(), queryParams.toArray(), orderby);
		List<DataByRoot> list = toPageData(queryResult.getResultlist());
		
		try {
			//total键存放总记录数,为了EasyUI
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("total", queryResult.getTotalrecord());
			//rows键存放每页记录,为了EasyUI
			jsonMap.put("rows", list);
			resultObj = JSONObject.fromObject(jsonMap, cfg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
		return Action.SUCCESS;
	}
	
	/**
	 * 把数据库中取得的数据封装成页面需要展示的数据
	 */
	private List<DataByRoot> toPageData(List<Account> accountList) {
		List<DataByRoot> list = new LinkedList<DataByRoot>();
		for (Account account : accountList) {
			DataByRoot dbr = new DataByRoot();
			dbr.setId(account.getId());
			dbr.setUserName(account.getUserName());
			dbr.setProjectName(account.getProject().getProjectName());
			dbr.setStatus(account.getStatus() == 1 ? "启用状态" : "禁用状态");
			list.add(dbr);
		}
		return list;
	}

	/**
	 * 停用账户
	 */
	public String disabled() {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			Account account = accountService.getById(model.getId());
			account.setStatus(0);
			accountService.update(account);
			jsonMap.put("success", true);
		} catch (Exception e) {
			jsonMap.put("errorMsg", "Some errors occured.");
			e.printStackTrace();
		}
		resultObj = JSONObject.fromObject(jsonMap);
		return Action.SUCCESS;
	}
	
	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public JSONObject getResultObj() {
		return resultObj;
	}

	public void setResultObj(JSONObject resultObj) {
		this.resultObj = resultObj;
	}
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

}