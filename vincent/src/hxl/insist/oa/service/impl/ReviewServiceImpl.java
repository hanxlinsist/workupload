package hxl.insist.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import hxl.insist.oa.base.DaoSupportImpl;
import hxl.insist.oa.domain.Project;
import hxl.insist.oa.domain.Review;
import hxl.insist.oa.service.ReviewService;

@Service
public class ReviewServiceImpl extends DaoSupportImpl<Review> implements ReviewService {

	public List<Review> takeAllByOpusId(Long id, Project project) {
		return getSession().createQuery(
				"FROM Review o WHERE o.project = ? AND o.opus.id = ?"
				).setParameter(0, project).setParameter(1, id)
				.list();
	}

}
