package hxl.insist.oa.domain;

import java.io.Serializable;

/**
 * 团队
 * 
 * @author 韩兴隆
 * 
 */
@SuppressWarnings("serial")
public class Team implements Serializable {
	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 作品
	 */
	private Opus opus;
	/**
	 * 账户
	 */
	private Account account;

	/**
	 * 队长姓名
	 */
	private String captainName;
	/**
	 * 队长学号
	 */
	private String captainId;
	/**
	 * 队长手机
	 */
	private String captainPhone;
	/**
	 * 队长邮箱
	 */
	private String captainMail;
	/**
	 * 指导老师姓名
	 */
	private String conductTeacherName;
	/**
	 * 所属学院
	 */
	private String academy;
	/**
	 * 团队类别 0-->本科生 1-->研究生
	 */
	private int teamCategory;
	/**
	 * 团队其他成员姓名
	 */
	private String teamMemberName;

	/**
	 * 团队所属项目
	 */
	private Project project;

	/**
	 * 获取整个团队的人数
	 * 
	 * @return
	 */
	public int takeTotalJoinNum() {
		if (teamMemberName != null) {
			String[] splitResult = teamMemberName.split("、");
			// 所有组员的数量加上队长
			return splitResult.length + 1;
		}
		return 1;
	}

	/**
	 * 获取指导老师的姓名和职称信息
	 * 
	 * @return
	 */
	public StringBuffer[] takeTeacherInfo() {
		StringBuffer teacherPosition = new StringBuffer("");
		StringBuffer teacherName = new StringBuffer("");
		if (conductTeacherName != null && conductTeacherName.length() > 0) {
			// prototype String 小明#讲师、小美#教授---->{小明#讲师,小美#教授}
			String[] splitResult = conductTeacherName.split("、");
			for (int i = 0; i < splitResult.length; i++) {
				// 小明#讲师 ....
				String str = splitResult[i];
				teacherName.append(str.substring(0, str.indexOf("#"))).append("、");
				teacherPosition.append(str.substring(str.indexOf("#") + 1, str.length())).append("、");
			}
			// 删除StringBuffer结尾多余的、
			teacherName.deleteCharAt(teacherName.length() - 1);
			teacherPosition.deleteCharAt(teacherPosition.length() - 1);

			StringBuffer[] conductTeacherInfo = { teacherName, teacherPosition };
			return conductTeacherInfo;
		}
		StringBuffer[] conductTeacherInfo = { teacherName, teacherPosition };
		return conductTeacherInfo;
	}

	public Opus getOpus() {
		return opus;
	}

	public void setOpus(Opus opus) {
		this.opus = opus;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCaptainName() {
		return captainName;
	}

	public void setCaptainName(String captainName) {
		this.captainName = captainName;
	}

	public String getCaptainPhone() {
		return captainPhone;
	}

	public void setCaptainPhone(String captainPhone) {
		this.captainPhone = captainPhone;
	}

	public String getCaptainMail() {
		return captainMail;
	}

	public void setCaptainMail(String captainMail) {
		this.captainMail = captainMail;
	}

	public String getConductTeacherName() {
		return conductTeacherName;
	}

	public void setConductTeacherName(String conductTeacherName) {
		this.conductTeacherName = conductTeacherName;
	}

	public String getAcademy() {
		return academy;
	}

	public void setAcademy(String academy) {
		this.academy = academy;
	}

	public int getTeamCategory() {
		return teamCategory;
	}

	public void setTeamCategory(int teamCategory) {
		this.teamCategory = teamCategory;
	}

	public String getTeamMemberName() {
		return teamMemberName;
	}

	public void setTeamMemberName(String teamMemberName) {
		this.teamMemberName = teamMemberName;
	}

	public String getCaptainId() {
		return captainId;
	}

	public void setCaptainId(String captainId) {
		this.captainId = captainId;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
