package hxl.insist.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import hxl.insist.oa.base.DaoSupportImpl;
import hxl.insist.oa.domain.Account;
import hxl.insist.oa.domain.Project;
import hxl.insist.oa.domain.Team;
import hxl.insist.oa.service.TeamService;

@SuppressWarnings("unchecked")
@Service
public class TeamServiceImpl extends DaoSupportImpl<Team> implements TeamService {

	
	public Team getByAccount(Account account) {
		return (Team) getSession().createQuery(
				"FROM Team t WHERE t.account = ?"
				).setParameter(0, account)
				.uniqueResult();
	}

	
	public List<Team> findAll() {
		return getSession().createQuery(
				"FROM Team t ORDER BY t.opus.averageScore DESC,t.captainName ASC"
				).list();
	}

	
	public Team findByOpusId(Long id) {
		return (Team) getSession().createQuery(
				"FROM Team t WHERE t.opus.id = ?"
				).setParameter(0, id)
				.uniqueResult();
	}

	
	public Team getTeamByAccountid(Long accountid) {
		return (Team) getSession().createQuery(
				"FROM Team t WHERE t.account.id = ?"
				).setParameter(0, accountid)
				.uniqueResult();
	}

	public List<Team> findByProject(Project project) {
		return getSession().createQuery(
				"FROM Team t WHERE t.project = ?"
				).setParameter(0, project)
				.list();
	}
}
