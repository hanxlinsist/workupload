package hxl.insist.oa.service.impl;

import org.springframework.stereotype.Service;

import hxl.insist.oa.base.DaoSupportImpl;
import hxl.insist.oa.domain.Account;
import hxl.insist.oa.domain.Reviewer;
import hxl.insist.oa.service.ReviewerService;

@Service
public class ReviewerServiceImpl extends DaoSupportImpl<Reviewer> implements ReviewerService {
	
	public Reviewer getByAccount(Account account) {
		return (Reviewer) getSession().createQuery(
				"FROM Reviewer r WHERE r.account = ?"
				).setParameter(0, account)
				.uniqueResult();
	}
}
