package hxl.insist.oa.view.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

import hxl.insist.oa.base.BaseAction;
import hxl.insist.oa.domain.Account;
import hxl.insist.oa.domain.Opus;
import hxl.insist.oa.domain.Reviewer;
import hxl.insist.oa.domain.Team;
import hxl.insist.oa.domain.notpersistent.QueryResult;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class ReviewerAdminAction extends BaseAction<Opus> {
	
	private String rows;//每页显示的记录数
    private String page;//当前第几页   
    private JSONObject resultObj;//返回的json
	
	/**
	 * 评审者姓名
	 */
	private String name;
	
	/**
	 * 评审者工号
	 */
	private String jobNumber;
	
	/**
	 * 列表界面
	 */
	public String listUI() throws Exception {
		return "list";
	}
	
	/**
	 * 列表 
	 */
	public String list() throws Exception {
		// Hibernate lazy loading problem
		JsonConfig cfg = new JsonConfig();
		cfg.setExcludes(new String[] { "handler", "hibernateLazyInitializer" });
				
		//当前页
		int currentPage = Integer.parseInt((page == null || page == "0") ? "1":page);
		//每页显示条数
		int maxresult = Integer.parseInt((rows == null || rows == "0") ? "10":rows);
		//每页的开始记录
		int firstindex = (currentPage - 1) * maxresult; 
		
		Account account = getSessionAccount();
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		StringBuffer wherehql = new StringBuffer("o.project = ?");
		List<Object> queryParams = new ArrayList<Object>();
		queryParams.add(account.getProject());
		wherehql.append(" and ").append(" o.academy = ? ");
		queryParams.add(account.getAcademy());
		
		//查询指定项目和指定学院下的作品
		QueryResult<Opus> queryResult = opusService.getScrollData(Opus.class, firstindex, maxresult, wherehql.toString(), queryParams.toArray(), orderby);
		List<Opus> opusList = queryResult.getResultlist();
		Reviewer reviewer = reviewerService.getByAccount(account);  //获取当前评审
		Set<Opus> works = reviewer.getWorks(); //查询当前评审人评过的作品
		int count = 0;  //记录有多少个作品已经被当前评审评价过
		for (Opus opus : works)
			if( opusList.contains(opus) ) { //如果作品列表中包含此位评审人已经评价过的作品，那么就将其从列表中移除
				opusList.remove(opus);
				count++;
			}
		
		try {
			//total键存放总记录数,为了EasyUI
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("total", queryResult.getTotalrecord() - count);
			//rows键存放每页记录,为了EasyUI
			jsonMap.put("rows", opusList);
			resultObj = JSONObject.fromObject(jsonMap, cfg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
		return Action.SUCCESS;
	}
	
	/**
	 * 评分界面
	 * @return
	 * @throws Exception
	 */
	public String remarkScoreUI() throws Exception {
		Team team = teamService.findByOpusId(model.getId());
		ActionContext.getContext().getValueStack().push(team);
		return "remarkScoreUI";
	}
	
	/**
	 * 评审用户资料完善界面
	 */
	public String complementInformationUI() {
		Reviewer reviewer = reviewerService.getByAccount(getSessionAccount());
		ActionContext.getContext().getValueStack().push(reviewer);
		return "complementInformationUI";
	}
	
	/**
	 * 评审用户资料完善
	 */
	public String complementInformation() {
		Reviewer reviewer = reviewerService.getByAccount(getSessionAccount());
		reviewer.setName(name);
		reviewer.setJobNumber(jobNumber);
		reviewerService.update(reviewer);
		
		return "toFunctionList";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
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
