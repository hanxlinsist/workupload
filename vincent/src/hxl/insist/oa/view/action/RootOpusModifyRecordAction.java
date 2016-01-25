package hxl.insist.oa.view.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Action;

import hxl.insist.oa.base.BaseAction;
import hxl.insist.oa.domain.Account;
import hxl.insist.oa.domain.Opus;
import hxl.insist.oa.domain.RecommendedLevel;
import hxl.insist.oa.domain.notpersistent.QueryResult;
import hxl.insist.oa.domain.page.RecommendedLevelBean;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class RootOpusModifyRecordAction extends BaseAction<Opus> {
	
	private String rows;//每页显示的记录数
    private String page;//当前第几页   
    private JSONObject resultObj;//返回的json
    
    RecommendedLevelBean[] recommendedLevelBean;
    
    /**
	 * 显示作品级别的修改记录
	 */
	public String showModifyRecord() {
		// Hibernate lazy loading problem
		JsonConfig cfg = new JsonConfig();
		cfg.setExcludes(new String[] { "handler", "hibernateLazyInitializer" });

		// 当前页
		int currentPage = Integer.parseInt((page == null || page == "0") ? "1" : page);
		// 每页显示条数
		int maxresult = Integer.parseInt((rows == null || rows == "0") ? "10" : rows);
		// 每页的开始记录
		int firstindex = (currentPage - 1) * maxresult;
		
		Account account = accountService.getById(model.getId());

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		StringBuffer wherehql = new StringBuffer("o.project = ?");
		List<Object> queryParams = new ArrayList<Object>();
		queryParams.add(account.getProject());
		if (model.getOpusName() != null && !"".equals(model.getOpusName().trim())) {
			wherehql.append(" and ").append(" o.opusName like ? ");
			queryParams.add("%" + model.getOpusName() + "%");
		}

		if (model.getTotalScore() > 0) {
			wherehql.append(" and ").append(" o.totalScore = ? ");
			queryParams.add(model.getTotalScore());
		}
		
		if (model.getAverageScore() > 0) {
			wherehql.append(" and ").append(" o.averageScore = ? ");
			queryParams.add(model.getAverageScore());
		}
		
		if (model.getLevel() != null && !"".equals(model.getLevel().trim())) {
			wherehql.append(" and ").append(" o.level like ? ");
			queryParams.add("%" + model.getLevel() + "%");
		}

		QueryResult<Opus> queryResult = opusService.getScrollData(Opus.class, firstindex, maxresult,
				wherehql.toString(), queryParams.toArray(), orderby);

		try {
			// total键存放总记录数,为了EasyUI
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("total", queryResult.getTotalrecord());
			// rows键存放每页记录,为了EasyUI
			jsonMap.put("rows", queryResult.getResultlist());
			resultObj = JSONObject.fromObject(jsonMap, cfg);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Action.SUCCESS;
	}
	
	/**
	 * 显示作品级别的修改记录界面
	 */
	public String showModifyRecordUI() {
		return "levelModifyRecord";
	}
	
	/**
	 * 获取指定工程下的所有推荐级别
	 */
	public String takeRecommendedLevel() {
		Account account = accountService.getById(model.getId());
		List<RecommendedLevel> list = recommendedLevelService.findByProject(account.getProject());
		recommendedLevelBean = new RecommendedLevelBean[list.size()];
		for (int i = 0; i < list.size(); i++)
			recommendedLevelBean[i] = new RecommendedLevelBean(list.get(i).getName(), list.get(i).getName());
		return "recommendedLevel";
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

	public RecommendedLevelBean[] getRecommendedLevelBean() {
		return recommendedLevelBean;
	}

	public void setRecommendedLevelBean(RecommendedLevelBean[] recommendedLevelBean) {
		this.recommendedLevelBean = recommendedLevelBean;
	}
}