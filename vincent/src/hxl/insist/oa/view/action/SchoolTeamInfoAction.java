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
import hxl.insist.oa.domain.Team;
import hxl.insist.oa.domain.notpersistent.QueryResult;
import hxl.insist.oa.domain.page.SchoolTeamInfo;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class SchoolTeamInfoAction extends BaseAction<Team> {
	
	private String rows;//每页显示的记录数
    private String page;//当前第几页   
    private JSONObject resultObj;//返回的json
    
    private String opusName;

	/**
	 * 团队信息管理界面
	 */
	public String teamInfoAdminListUI() {
		return "teamInfoAdminList";
	}
	
	/**
	 * 团队信息管理
	 */
	public String teamInfoAdminList() {
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
		StringBuffer wherehql = new StringBuffer("o.project = ?");
		List<Object> queryParams = new ArrayList<Object>();
		queryParams.add(getSessionAccount().getProject());
		if (model.getCaptainName() != null && !"".equals(model.getCaptainName().trim())) {
			wherehql.append(" and ").append(" o.captainName like ? ");
			queryParams.add("%" + model.getCaptainName() + "%");
		} 
		
		if (opusName != null && !"".equals(opusName.trim())) {
			wherehql.append(" and ").append(" o.opus.opusName like ?");
			queryParams.add("%" + opusName + "%");
		}
		
		QueryResult<Team> queryResult = teamService.getScrollData(Team.class, firstindex, maxresult, wherehql.toString(), queryParams.toArray(), orderby);
		List<SchoolTeamInfo> list = toPageData(queryResult.getResultlist());
		
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
	private List<SchoolTeamInfo> toPageData(List<Team> teamList) {
		List<SchoolTeamInfo> list = new LinkedList<SchoolTeamInfo>();
		for (Team team : teamList) {
			SchoolTeamInfo st = new SchoolTeamInfo(team.getId(), team.getCaptainName(), team.getOpus().getOpusName(), 
					team.getOpus().getBdAddress(), team.getCaptainPhone(), team.getCaptainMail());
			list.add(st);
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

	public String getOpusName() {
		return opusName;
	}

	public void setOpusName(String opusName) {
		this.opusName = opusName;
	}
	
}
