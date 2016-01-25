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
import com.opensymphony.xwork2.ActionContext;

import hxl.insist.oa.base.BaseAction;
import hxl.insist.oa.domain.Opus;
import hxl.insist.oa.domain.Project;
import hxl.insist.oa.domain.RecommendedLevel;
import hxl.insist.oa.domain.Review;
import hxl.insist.oa.domain.Team;
import hxl.insist.oa.domain.notpersistent.QueryResult;
import hxl.insist.oa.domain.page.RecommendedLevelBean;
import hxl.insist.oa.domain.page.SchoolOpusOrder;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class SchoolOpusInfoAction extends BaseAction<Team> {
	
	private String rows;//每页显示的记录数
    private String page;//当前第几页   
    private JSONObject resultObj;//返回的json
    
    private String opusName;
    RecommendedLevelBean[] recommendedLevelBean;
    private String level;

	/**
	 * 作品信息管理界面
	 */
	public String opusInfoAdminListUI() {
		return "opusInfoAdminList";
	}
	
	/**
	 * 显示作品评审细节
	 */
	public String showReviewDetails() {
		Project project = getSessionAccount().getProject();
		List<Review> reviewList = reviewService.takeAllByOpusId(model.getId(), project);
		ActionContext.getContext().put("reviewList", reviewList);
		return "reviewDetails";
	}
	
	/**
	 * 作品信息管理
	 */
	public String opusInfoAdminList() {
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
		orderby.put("opus.averageScore", "desc");
		StringBuffer wherehql = new StringBuffer("o.project = ?");
		List<Object> queryParams = new ArrayList<Object>();
		queryParams.add(getSessionAccount().getProject());
		if (opusName != null && !"".equals(opusName.trim())) {
			wherehql.append(" and ").append(" o.opus.opusName like ? ");
			queryParams.add("%" + opusName + "%");
		} 
		
		if (model.getCaptainName() != null && !"".equals(model.getCaptainName().trim())) {
			wherehql.append(" and ").append(" o.captainName like ? ");
			queryParams.add("%" + model.getCaptainName() + "%");
		}
		
		if (model.getConductTeacherName() != null && !"".equals(model.getConductTeacherName().trim())) {
			wherehql.append(" and ").append(" o.conductTeacherName like ? ");
			queryParams.add("%" + model.getConductTeacherName() + "%");
		}
		
		QueryResult<Team> queryResult = teamService.getScrollData(Team.class, firstindex, maxresult, wherehql.toString(), queryParams.toArray(), orderby);
		List<SchoolOpusOrder> list = toPageData(queryResult.getResultlist());
		
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
	 * 设置作品的推荐级别
	 */
	public String modifyOpusLevel() {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			Opus opus = opusService.getById(model.getId());
			opus.setLevel(level);
			String levelModifyRecord = opus.getLevelModifyRecord();
			if ( levelModifyRecord == null )
				opus.setLevelModifyRecord(level);
			else
				opus.setLevelModifyRecord(levelModifyRecord + "-->" + level);
			opusService.update(opus);
			jsonMap.put("success", true);
		} catch (Exception e) {
			jsonMap.put("errorMsg", "Some errors occured.");
			e.printStackTrace();
		}
		resultObj = JSONObject.fromObject(jsonMap);
		return Action.SUCCESS;
	}
	
	/**
	 * 把数据库中取得的数据封装成页面需要展示的数据
	 */
	private List<SchoolOpusOrder> toPageData(List<Team> teamList) {
		List<SchoolOpusOrder> list = new LinkedList<SchoolOpusOrder>();
		for (Team team : teamList) {
			SchoolOpusOrder soo = new SchoolOpusOrder();
			Opus opus = team.getOpus();
			soo.setId(opus.getId());
			soo.setOpusName(opus.getOpusName());
			soo.setLevel(opus.getLevel());
			soo.setAverageScore(opus.getAverageScore());
			soo.setCaptainName(team.getCaptainName());
			soo.setCaptainPhone(team.getCaptainPhone());
			soo.setConductTeacherName(team.getConductTeacherName());
			list.add(soo);
		}
		return list;
	}
	
	/**
	 * 获取指定工程下的所有推荐级别
	 */
	public String takeRecommendedLevel() {
		List<RecommendedLevel> list = recommendedLevelService.findByProject(getSessionAccount().getProject());
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

	public String getOpusName() {
		return opusName;
	}

	public void setOpusName(String opusName) {
		this.opusName = opusName;
	}

	public RecommendedLevelBean[] getRecommendedLevelBean() {
		return recommendedLevelBean;
	}

	public void setRecommendedLevelBean(RecommendedLevelBean[] recommendedLevelBean) {
		this.recommendedLevelBean = recommendedLevelBean;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
}
