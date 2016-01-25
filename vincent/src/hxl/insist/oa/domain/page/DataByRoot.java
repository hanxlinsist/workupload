package hxl.insist.oa.domain.page;

/**
 * Root用户管理的所有学校帐号的页面数据
 * @author 韩兴隆
 */
public class DataByRoot {
	
	private Long id;
	
	/**
	 * 登录名
	 */
	private String userName;
	
	/**
	 * 账户所属项目名称
	 */
	private String projectName;
	
	/**
	 * 账户状态
	 */
	private String status;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
