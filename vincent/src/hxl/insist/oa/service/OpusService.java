package hxl.insist.oa.service;

import java.util.List;

import hxl.insist.oa.base.DaoSupport;
import hxl.insist.oa.domain.Opus;
import hxl.insist.oa.domain.Project;

public interface OpusService extends DaoSupport<Opus> {

	/**
	 * 查询当前作品的名次
	 * @param averageScore 当前作品的平均分
	 * @return
	 */
	int findRanking(double averageScore);

	/**
	 * 查找指定工程下的作品
	 * @param project
	 * @return
	 */
	List<Opus> findByProject(Project project);

}
