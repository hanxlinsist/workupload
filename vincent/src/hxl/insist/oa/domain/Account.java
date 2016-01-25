package hxl.insist.oa.domain;

import java.io.Serializable;

/**
 * 帐户信息
 * @author 韩兴隆
 */
@SuppressWarnings("serial")
public class Account implements Serializable {
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 登录名
	 */
	private String userName;
	/**
	 * 登录密码
	 */
	private String password;
	
	/**
	 * 账户角色 
	 * 0-->学校
	 * 1-->团队
	 * 2-->评审
	 * 3-->root
	 */
	private int role;
	/**
	 * 帐户所属的学院
	 */
	private String academy;

	/**
	 * 账号的状态，默认为开启状态
	 * 0-->关闭
	 * 1-->开启
	 */
	private int status = 1;
	
	/**
	 * 帐户所属的项目
	 */
	private Project project;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getAcademy() {
		return academy;
	}

	public void setAcademy(String academy) {
		this.academy = academy;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
}
