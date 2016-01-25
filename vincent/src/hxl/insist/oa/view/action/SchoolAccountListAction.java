package hxl.insist.oa.view.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Action;

import hxl.insist.oa.base.BaseAction;
import hxl.insist.oa.domain.Academy;
import hxl.insist.oa.domain.Account;
import hxl.insist.oa.domain.notpersistent.QueryResult;
import hxl.insist.oa.domain.page.RecommendedLevelBean;
import hxl.insist.oa.domain.page.SchoolAccountList;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class SchoolAccountListAction extends BaseAction<Account> {
	
	private String rows;//每页显示的记录数
    private String page;//当前第几页   
    private JSONObject resultObj;//返回的json
    
    RecommendedLevelBean[] recommendedLevelBean;
    
    /**
     * 所有学校管理者账号界面
     */
    public String listUI() {
    	return "accountList";
    }
    
	/**
	 * 显示所有评审和学生账号
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
		StringBuffer wherehql = new StringBuffer("o.academy IS NOT NULL");
		List<Object> queryParams = new ArrayList<Object>();
		wherehql.append(" and ").append(" o.project = ? ");
		queryParams.add(getSessionAccount().getProject());
		if (model.getUserName() != null && !"".equals(model.getUserName().trim())) {
			wherehql.append(" and ").append(" o.userName like ? ");
			queryParams.add("%" + model.getUserName() + "%");
		} 
		
		if (model.getAcademy() != null && !"".equals(model.getAcademy().trim())) {
			wherehql.append(" and ").append(" o.academy= ?");
			queryParams.add(model.getAcademy());
		}
		
		QueryResult<Account> queryResult = accountService.getScrollData(Account.class, firstindex, maxresult, wherehql.toString(), queryParams.toArray(), orderby);
		List<SchoolAccountList> list = toPageData(queryResult.getResultlist());
		
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
	private List<SchoolAccountList> toPageData(List<Account> accountList) {
		List<SchoolAccountList> list = new LinkedList<SchoolAccountList>();
		for (Account account : accountList) {
			SchoolAccountList sal = new SchoolAccountList(account.getId(), account.getUserName(), account.getAcademy());
			sal.setStatus(account.getStatus() == 1 ? "启用状态" : "禁用状态");
			sal.setRole(account.getRole() == 1 ? "团队" : "评审");
			list.add(sal);
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
	
	/** 
	 * 初始化密码为1234 
	 */
	public String initPassword() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			// 1，从数据库中取出原对象
			Account account = accountService.getById(model.getId());

			// 2，设置要修改的属性（要使用MD5摘要）
			String md5Digest = DigestUtils.md5Hex("1234");
			account.setPassword(md5Digest);

			// 3，更新到数据库
			accountService.update(account);
			
			jsonMap.put("success", true);
		} catch (Exception e) {
			jsonMap.put("errorMsg", "Some errors occured.");
			e.printStackTrace();
		}
		resultObj = JSONObject.fromObject(jsonMap);
		return Action.SUCCESS;
	}
	
	/**
	 * 获取学院信息
	 */
	public String takeAcademy() {
		List<Academy> academyList = academyService.findAll();
		recommendedLevelBean = new RecommendedLevelBean[academyList.size()];
		for (int i = 0; i < academyList.size(); i++)
			recommendedLevelBean[i] = new RecommendedLevelBean(academyList.get(i).getName(), academyList.get(i).getName());
		return "academy";
	}
	
	public RecommendedLevelBean[] getRecommendedLevelBean() {
		return recommendedLevelBean;
	}

	public void setRecommendedLevelBean(RecommendedLevelBean[] recommendedLevelBean) {
		this.recommendedLevelBean = recommendedLevelBean;
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
	
}