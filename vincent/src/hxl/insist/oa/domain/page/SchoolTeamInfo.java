package hxl.insist.oa.domain.page;

/**
 * 学校用户管理所有团队信息的页面数据
 * @author 韩兴隆
 */
public class SchoolTeamInfo {
	
	private Long id;
	
	/**
	 * 队长名
	 */
	private String captainName;
	
	/**
	 * 作品名称
	 */
	private String opusName;
	
	/**
	 * 作品百度云地址
	 */
	private String bdAddress;
	
	/**
	 * 队长手机
	 */
	private String captainPhone;
	
	/**
	 * 队长邮箱
	 */
	private String captainMail;

	public SchoolTeamInfo(Long id, String captainName, String opusName, String bdAddress, String captainPhone,
			String captainMail) {
		this.id = id;
		this.captainName = captainName;
		this.opusName = opusName;
		this.bdAddress = bdAddress;
		this.captainPhone = captainPhone;
		this.captainMail = captainMail;
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

	public String getOpusName() {
		return opusName;
	}

	public void setOpusName(String opusName) {
		this.opusName = opusName;
	}

	public String getBdAddress() {
		return bdAddress;
	}

	public void setBdAddress(String bdAddress) {
		this.bdAddress = bdAddress;
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
	
}
