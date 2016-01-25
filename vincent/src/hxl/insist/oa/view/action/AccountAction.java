package hxl.insist.oa.view.action;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import hxl.insist.oa.base.BaseAction;
import hxl.insist.oa.domain.Academy;
import hxl.insist.oa.domain.Account;
import hxl.insist.oa.domain.Opus;
import hxl.insist.oa.domain.Project;
import hxl.insist.oa.domain.Reviewer;
import hxl.insist.oa.domain.Team;
import hxl.insist.oa.util.Utils;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class AccountAction extends BaseAction<Account> {

	/**
	 * 一次分配的账号数量
	 */
	private int accountNumber;
	
	/**
	 * 团队系统的状态
	 */
	private int teamSystemStatus; 
	
	/**
	 * 评审系统的状态
	 */
	private int reviewerSystemStatus; 
	
	/**
	 * 分配登录账号页面
	 */
	public String assignAccountUI() {
		List<Academy> academyList = academyService.findAll();
		ActionContext.getContext().put("academyList", academyList);
		return "assignAccountUI";
	}
	
	/** 删除 *//*
	public String delete() throws Exception {
		Long accountid = model.getId();
		Account account = accountService.getById(accountid);
		Team team = teamService.getTeamByAccountid(accountid);
		if (account.getRole() == 1 && team != null) {
			Long opusid = team.getOpus().getId();
			teamService.delete(team.getId());
			opusService.delete(opusid);
		}
		accountService.delete(accountid);
		return "toAccountList";
	}*/

	/**
	 * 分配登录账号
	 */
	public String assignAccount() {
		String md5Digest = DigestUtils.md5Hex("1234");
		Project project = getSessionAccount().getProject();
		String academy = model.getAcademy();
		if (accountNumber > 0) {
			for (int i = 0; i < accountNumber; i++) {
				Account account = new Account();
				
				StringBuffer userName = new StringBuffer("");
				if (model.getRole() == 1) {
					//为当前团队生成一个账号
					userName.append("team_" + Utils.consistNameByDate() + i);
					account.setAcademy(academy);
					account.setPassword(md5Digest);
					account.setRole(model.getRole());
					account.setUserName(userName.toString());
					account.setProject(project);
					accountService.save(account);
					
					//为当前团队保存一个作品到数据库中
					Opus opus = new Opus();
					opus.setAcademy(model.getAcademy());
					opus.setProject(project);
					opusService.save(opus);
					
					//为当前账户保存一个团队到数据库中
					Team team = new Team();
					team.setAcademy(model.getAcademy());
					team.setProject(project);
					team.setAccount(account);
					team.setOpus(opus);
					teamService.save(team);
				} else if(model.getRole() == 2) {
					//为当前评审生成一个账号
					userName.append("reviewer_" + Utils.consistNameByDate() + i);
					account.setAcademy(model.getAcademy());
					account.setPassword(md5Digest);
					account.setRole(model.getRole());
					account.setUserName(userName.toString());
					account.setProject(project);
					accountService.save(account);
					
					//为当前账户保存一个评审到数据库中
					Reviewer reviewer = new Reviewer();
					reviewer.setAccount(account);
					reviewer.setProject(project);
					reviewer.setAcademy(academy);
					reviewerService.save(reviewer);
				}
			}
		}
		return "toAccountList";
	}
	
	/**
	 * 设置学生和评审系统和开关的界面
	 * @return
	 * @throws Exception
	 */
	public String modifySystemStatusUI() throws Exception {
		return "modifySystemStatusUI";
	}
	
	/**
	 * 设置学生系统开关
	 * @return
	 * @throws Exception
	 */
	public String modifyTeamSystemStatus() throws Exception {
		//查询出所有团队账户的列表
		List<Account> accountList = accountService.findByRole(1);
		if(accountList != null && accountList.size() > 0) {
			accountService.updateList(accountList,teamSystemStatus);
		}
		return "toFunctionList";
	}
	
	/**
	 * 设置评审系统开关
	 * @return
	 * @throws Exception
	 */
	public String modifyReviewerSystemStatus() throws Exception {
		//查询出所有团队账户的列表
		List<Account> reviewerList = accountService.findByRole(2);
		if(reviewerList != null && reviewerList.size() > 0) {
			accountService.updateList(reviewerList,reviewerSystemStatus);
		}
		return "toFunctionList";
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getTeamSystemStatus() {
		return teamSystemStatus;
	}

	public void setTeamSystemStatus(int teamSystemStatus) {
		this.teamSystemStatus = teamSystemStatus;
	}

	public int getReviewerSystemStatus() {
		return reviewerSystemStatus;
	}

	public void setReviewerSystemStatus(int reviewerSystemStatus) {
		this.reviewerSystemStatus = reviewerSystemStatus;
	}
}