package hxl.insist.oa.service;

import java.util.List;

import hxl.insist.oa.base.DaoSupport;
import hxl.insist.oa.domain.Project;
import hxl.insist.oa.domain.WorkType;

public interface WorkTypeService extends DaoSupport<WorkType> {

	List<WorkType> findByProject(Project project);
	
}