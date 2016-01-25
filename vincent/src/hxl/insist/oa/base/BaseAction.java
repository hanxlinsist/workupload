package hxl.insist.oa.base;

import java.lang.reflect.ParameterizedType;
import java.util.Map;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import hxl.insist.oa.domain.Account;
import hxl.insist.oa.service.AcademyService;
import hxl.insist.oa.service.AccountService;
import hxl.insist.oa.service.OpusService;
import hxl.insist.oa.service.ProjectService;
import hxl.insist.oa.service.RecommendedLevelService;
import hxl.insist.oa.service.ReviewService;
import hxl.insist.oa.service.ReviewerService;
import hxl.insist.oa.service.TeamService;
import hxl.insist.oa.service.WorkTypeService;

@SuppressWarnings({ "unchecked", "serial" })
public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T>{
	
	protected T model;

	public T getModel() {
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
		try {
			model = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException();
		}
		
		return model;
	}
	
	@Resource
	protected AccountService accountService;
	
	@Resource
	protected AcademyService academyService;
	
	@Resource
	protected TeamService teamService;
	
	@Resource
	protected OpusService opusService;
	
	@Resource
	protected ProjectService projectService;
	
	@Resource
	protected WorkTypeService workTypeService;
	
	@Resource
	protected ReviewService reviewService;
	
	@Resource
	protected ReviewerService reviewerService;
	
	@Resource
	protected RecommendedLevelService recommendedLevelService;
	
	protected Account getSessionAccount() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Account account = (Account) session.get("account");
		return account;
	}
}
