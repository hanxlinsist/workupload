package hxl.insist.oa.service;

import java.util.List;

import hxl.insist.oa.base.DaoSupport;
import hxl.insist.oa.domain.Project;
import hxl.insist.oa.domain.Review;

public interface ReviewService extends DaoSupport<Review> {

	/**
	 * 通过作品ID查询所有的评审表
	 */
	List<Review> takeAllByOpusId(Long id, Project project);

	
}