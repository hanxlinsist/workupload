package hxl.insist.oa.domain.page;

/**
 * 学校帐户管理评审和学生帐户的前台页面
 * @author 韩兴隆
 */
public class SchoolAccountList {
	
	private Long id;
	
	/**
	 * 登录名
	 */
	private String userName;
	
	/**
	 * 账户角色
	 */
	private String role;
	
	/**
	 * 帐户所属的学院
	 */
	private String academy;
	
	/**
	 * 账户状态
	 */
	private String status;
	
	public SchoolAccountList(Long id, String userName, String academy) {
		this.id = id;
		this.userName = userName;
		this.academy = academy;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAcademy() {
		return academy;
	}

	public void setAcademy(String academy) {
		this.academy = academy;
	}
	
}
