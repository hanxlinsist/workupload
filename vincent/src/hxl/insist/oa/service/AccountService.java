package hxl.insist.oa.service;

import java.util.List;

import hxl.insist.oa.base.DaoSupport;
import hxl.insist.oa.domain.Account;

public interface AccountService extends DaoSupport<Account> {

	/**
	 * 根据登录名、密码、和角色类型查询用户
	 * @param userName
	 * @param password
	 * @param role
	 * @return
	 */
	Account findByLoginNameAndPasswordAndRole(String userName, String password,
			int role);

	/**
	 * 通过账户的角色查找所有的账户
	 * @param i
	 * @return
	 */
	List<Account> findByRole(int i);

	/**
	 * 更新集合中所有账户的开关状态
	 * @param accountList
	 * @param teamSystemStatus 给定的状态  0-->关   1-->开
	 */
	void updateList(List<Account> accountList, int teamSystemStatus);

}