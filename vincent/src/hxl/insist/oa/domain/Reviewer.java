package hxl.insist.oa.domain;

import java.io.Serializable;
import java.util.Set;

/**
 * 评审者
 * 
 * @author 韩兴隆
 *
 */
@SuppressWarnings("serial")
public class Reviewer implements Serializable {

	/**
	 * 主键
	 */
	private Long id;
	
	/**
	 * 评审者姓名
	 */
	private String name;
	
	/**
	 * 评审者工号
	 */
	private String jobNumber;
	
	/**
	 * 账户
	 */
	private Account account;
	
	/**
	 * 所属项目
	 */
	private Project project;
	
	/**
	 * 所属学院
	 */
	private String academy;
	
	/**
	 * 评审过的作品
	 */
	private Set<Opus> works;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getAcademy() {
		return academy;
	}

	public void setAcademy(String academy) {
		this.academy = academy;
	}

	public Set<Opus> getWorks() {
		return works;
	}

	public void setWorks(Set<Opus> works) {
		this.works = works;
	}
}
