package hxl.insist.oa.view.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import hxl.insist.oa.base.BaseAction;
import hxl.insist.oa.domain.Team;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class TeamAdminAction extends BaseAction<Team> {
	
	/**
	 * 团队用户资料完善界面
	 */
	public String complementInformationUI() {
		Team team = teamService.getByAccount(getSessionAccount());
		ActionContext.getContext().getValueStack().push(team);
		return "complementInformationUI";
	}
	
	/**
	 * 团队用户资料完善
	 */
	public String complementInformation() {
		Team team = teamService.getByAccount(getSessionAccount());
		team.setTeamCategory(model.getTeamCategory());
		team.setCaptainName(model.getCaptainName());
		team.setCaptainId(model.getCaptainId());
		team.setCaptainPhone(model.getCaptainPhone());
		team.setCaptainMail(model.getCaptainMail());
		team.setTeamMemberName(model.getTeamMemberName());
		team.setConductTeacherName(model.getConductTeacherName());
		teamService.update(team);
		
		return "toFunctionList";
	}
}
