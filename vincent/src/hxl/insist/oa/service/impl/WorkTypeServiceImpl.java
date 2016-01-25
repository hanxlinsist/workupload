package hxl.insist.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import hxl.insist.oa.base.DaoSupportImpl;
import hxl.insist.oa.domain.Project;
import hxl.insist.oa.domain.WorkType;
import hxl.insist.oa.service.WorkTypeService;

@Service
public class WorkTypeServiceImpl extends DaoSupportImpl<WorkType> implements WorkTypeService {

	public List<WorkType> findByProject(Project project) {
		return getSession().createQuery(
				"FROM WorkType w WHERE w.project = ?"
				).setParameter(0, project)
				.list();
	}

}
