package hxl.insist.oa.service;

import java.util.List;

import hxl.insist.oa.base.DaoSupport;
import hxl.insist.oa.domain.Account;
import hxl.insist.oa.domain.Project;
import hxl.insist.oa.domain.Team;

public interface TeamService extends DaoSupport<Team> {

	/**
	 * 根据账户查询团队
	 * @param account
	 * @return
	 */
	Team getByAccount(Account account);

	/**
	 * 根据作品ID查询作品所属团队
	 * @param id
	 * @return
	 */
	Team findByOpusId(Long id);

	/**
	 * 根据账号ID查询团队
	 * @param accountid
	 * @return
	 */
	Team getTeamByAccountid(Long accountid);

	/**
	 * 根据作品所属的项目查找
	 * @param belongActivity
	 * @return
	 */
	List<Team> findByProject(Project project);

}
