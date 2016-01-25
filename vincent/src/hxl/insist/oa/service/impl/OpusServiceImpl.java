package hxl.insist.oa.service.impl;

import java.util.List;

import hxl.insist.oa.base.DaoSupportImpl;
import hxl.insist.oa.domain.Opus;
import hxl.insist.oa.domain.Project;
import hxl.insist.oa.service.OpusService;

import org.springframework.stereotype.Service;

@SuppressWarnings("unchecked")
@Service
public class OpusServiceImpl extends DaoSupportImpl<Opus> implements OpusService {

	
	public int findRanking(double averageScore) {
		Long ranking = (Long) getSession().createQuery(
				"SELECT COUNT(*) FROM Opus o WHERE o.averageScore >= ?"
				).setParameter(0, averageScore)
				.uniqueResult();
		return ranking.intValue();
	}

	public List<Opus> findByProject(Project project) {
		return getSession().createQuery(
				"FROM Opus o WHERE o.project = ?"
				).setParameter(0, project)
				.list();
	}
}
