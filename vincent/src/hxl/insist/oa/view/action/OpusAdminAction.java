package hxl.insist.oa.view.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import hxl.insist.oa.base.BaseAction;
import hxl.insist.oa.domain.Opus;
import hxl.insist.oa.domain.Team;
import hxl.insist.oa.domain.WorkType;
import hxl.insist.oa.service.OpusService;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class OpusAdminAction extends BaseAction<Opus> {
	
	@Resource
	protected OpusService opusService;
	
	/**
	 * 上传作品信息界面
	 * @return
	 * @throws Exception
	 */
	public String complementOpusInfoUI() throws Exception {
		Team team = teamService.getByAccount(getSessionAccount());
		List<WorkType> worktypes = workTypeService.findByProject(team.getProject());
		ActionContext.getContext().put("worktypes", worktypes);
		Opus opus = team.getOpus();
		if(opus != null)
			ActionContext.getContext().getValueStack().push(opus);
		
		return "complementOpusInfoUI";
	}
	
	/**
	 * 上传作品信息
	 * @return
	 * @throws Exception
	 */
	public String complementOpusInfo() throws Exception {
		Opus opus = teamService.getByAccount(getSessionAccount()).getOpus();
		opus.setOpusCategory(model.getOpusCategory());
		opus.setOpusName(model.getOpusName());
		opus.setOpusSummary(model.getOpusSummary());
		opus.setBdAddress(model.getBdAddress());
		opusService.update(opus);
		return "toFunctionList";
	}
	
	/**
	 * 显示作品状态界面
	 */
	public String opusStatusUI() throws Exception {
		Team team = teamService.getByAccount(getSessionAccount());
		ActionContext.getContext().getValueStack().push(team);
		Opus opus = team.getOpus();
		//获取评委对作品的建议
		String appraisedAdvice = opus.getAppraisedAdvice();
		if (appraisedAdvice != null) {
			String[] appraisedAdviceList = appraisedAdvice.split("@@@");
			ActionContext.getContext().put("appraisedAdviceList",appraisedAdviceList);
		}
		return "opusStatusUI";
	}
	
}
