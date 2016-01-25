package hxl.insist.oa.domain.page;

/**
 * 学校用户用于管理作品排序列表的页面
 * 
 * @author 韩兴隆
 *
 */
public class SchoolOpusOrder {
	
	/**
	 * 作品ID
	 */
	private Long id;
	
	/**
	 * 作品名称
	 */
	private String opusName;
	
	/**
	 * 作品推荐级别
	 */
	private String level;
	
	/**
	 * 作品平均分
	 * 作品总分 / 作品被评价过的次数
	 */
	private double averageScore;
	
	/**
	 * 队长姓名
	 */
	private String captainName;
	
	/**
	 * 队长手机
	 */
	private String captainPhone;
	
	/**
	 * 指导老师姓名
	 */
	private String conductTeacherName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOpusName() {
		return opusName;
	}

	public void setOpusName(String opusName) {
		this.opusName = opusName;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public double getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(double averageScore) {
		this.averageScore = averageScore;
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

	public String getConductTeacherName() {
		return conductTeacherName;
	}

	public void setConductTeacherName(String conductTeacherName) {
		this.conductTeacherName = conductTeacherName;
	}

}
