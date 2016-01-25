package hxl.insist.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import hxl.insist.oa.base.DaoSupportImpl;
import hxl.insist.oa.domain.Project;
import hxl.insist.oa.domain.RecommendedLevel;
import hxl.insist.oa.service.RecommendedLevelService;

@Service
public class RecommendedLevelServiceImpl extends DaoSupportImpl<RecommendedLevel> implements RecommendedLevelService {

	public List<RecommendedLevel> findByProject(Project project) {
		return getSession().createQuery(
				"FROM RecommendedLevel r WHERE r.project = ?"
				).setParameter(0, project)
				.list();
	}
}
