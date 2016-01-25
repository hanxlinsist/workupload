package hxl.insist.oa.service.impl;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import hxl.insist.oa.base.DaoSupportImpl;
import hxl.insist.oa.domain.Account;
import hxl.insist.oa.service.AccountService;

@SuppressWarnings("unchecked")
@Service
public class AccountServiceImpl extends DaoSupportImpl<Account> implements AccountService {

	
	public Account findByLoginNameAndPasswordAndRole(String userName,
			String password, int role) {
		String md5Digest = DigestUtils.md5Hex(password);
		return (Account) getSession().createQuery(
				"FROM Account a WHERE a.userName =? AND a.password =? AND a.role =?"
				).setParameter(0, userName).setParameter(1, md5Digest).setParameter(2, role)
				.uniqueResult();
	}

	
	public List<Account> findByRole(int i) {
		return getSession().createQuery(
				"FROM Account a WHERE a.role = ?"
				).setParameter(0, i)
				.list();
	}

	
	public void updateList(List<Account> accountList, int teamSystemStatus) {
		for (Account account : accountList) {
			account.setStatus(teamSystemStatus);
			update(account);
		}
	}

}