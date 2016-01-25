package hxl.insist.oa.service;

import hxl.insist.oa.base.DaoSupport;
import hxl.insist.oa.domain.Account;
import hxl.insist.oa.domain.Reviewer;

public interface ReviewerService extends DaoSupport<Reviewer> {
	/**
	 * 通过账号查找评审
	 */
	public Reviewer getByAccount(Account account);
}