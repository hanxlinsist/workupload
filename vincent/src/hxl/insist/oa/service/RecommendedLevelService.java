package hxl.insist.oa.service;

import java.util.List;

import hxl.insist.oa.base.DaoSupport;
import hxl.insist.oa.domain.Project;
import hxl.insist.oa.domain.RecommendedLevel;

public interface RecommendedLevelService extends DaoSupport<RecommendedLevel> {

	/**
	 * 查询指定项目下的推荐级别
	 */
	List<RecommendedLevel> findByProject(Project project);
}
