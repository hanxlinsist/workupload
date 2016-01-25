package hxl.insist.oa.view.action;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Action;

import hxl.insist.oa.base.BaseAction;
import hxl.insist.oa.domain.Opus;
import hxl.insist.oa.domain.Reviewer;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class DataAnalysisAction extends BaseAction<Reviewer> {
	
	private Map<String, Integer> maps = new HashMap<String, Integer>();

	/**
	 * 数据分析界面
	 */
	public String analysisUI() {
		return "analysisUI";
	}

	/**
	 * 将准备的分析结果以Json形式返回
	 */
	public String analysis() {
		Reviewer reviewer = reviewerService.getById(model.getId());
		Set<Opus> works = reviewer.getWorks();
		for (Opus opus : works) {
			String level = opus.getLevel();
			maps.put(level, ( maps.get(level) == null ? 0 : maps.get(level) ) + 1);
		}
		return Action.SUCCESS;
	}

	public Map<String, Integer> getMaps() {
		return maps;
	}

	public void setMaps(Map<String, Integer> maps) {
		this.maps = maps;
	}
}
