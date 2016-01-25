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

import hxl.insist.oa.base.BaseAction;
import hxl.insist.oa.domain.Reviewer;
import hxl.insist.oa.domain.notpersistent.QueryResult;
import hxl.insist.oa.domain.page.ReviewerDataAnalysis;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class RootReviewerDataAnalysisAction extends BaseAction<Reviewer> {
	
	private String rows;//每页显示的记录数
    private String page;//当前第几页   
    private JSONObject resultObj;//返回的json
    
    private String projectName;
    
    /**
	 * 显示所有评审者界面
	 */
	public String reviewerListUI() {
		return "reviewerList";
	}
	
	/**
	 * 显示所有评审者
	 */
	public String reviewerList() {
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
		StringBuffer wherehql = new StringBuffer("1=1");
		List<Object> queryParams = new ArrayList<Object>();
		if (model.getName() != null && !"".equals(model.getName().trim())) {
			wherehql.append(" and ").append(" o.name like ? ");
			queryParams.add("%" + model.getName() + "%");
		} 
		
		if (model.getJobNumber() != null && !"".equals(model.getJobNumber().trim())) {
			wherehql.append(" and ").append(" o.jobNumber like ? ");
			queryParams.add("%" + model.getJobNumber() + "%");
		}
		
		if (projectName != null && !"".equals(projectName.trim())) {
			wherehql.append(" and ").append(" o.project.projectName like ? ");
			queryParams.add("%" + projectName + "%");
		}
		
		QueryResult<Reviewer> queryResult = reviewerService.getScrollData(Reviewer.class, firstindex, maxresult, wherehql.toString(), queryParams.toArray(), orderby);
		List<ReviewerDataAnalysis> list = toPageData(queryResult.getResultlist());
		
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
	private List<ReviewerDataAnalysis> toPageData(List<Reviewer> reviewerList) {
		List<ReviewerDataAnalysis> list = new LinkedList<ReviewerDataAnalysis>();
		for (Reviewer reviewer : reviewerList) {
			ReviewerDataAnalysis rda = new ReviewerDataAnalysis(reviewer.getId(), reviewer.getName(), 
					reviewer.getJobNumber(), reviewer.getProject().getProjectName());
			list.add(rda);
		}
		return list;
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