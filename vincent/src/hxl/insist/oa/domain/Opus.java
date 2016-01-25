package hxl.insist.oa.domain;

import java.io.Serializable;

/**
 * 作品
 * 
 * @author 韩兴隆
 *
 */
@SuppressWarnings("serial")
public class Opus implements Serializable {

	/**
	 * 主键
	 */
	private Long id;
	
	/**
	 * 作品名称
	 */
	private String opusName;
	
	/**
	 * 作品类别
	 */
	private String opusCategory;
	
	/**
	 * 作品的百度云地址
	 */
	private String bdAddress;
	
	/**
	 * 作品简介
	 */
	private String opusSummary;
	
	/**
	 * 作品总分
	 */
	private double totalScore;
	
	/**
	 * 作品被评价过的次数
	 */
	private int appraisedNumber;
	
	/**
	 * 作品平均分
	 * 作品总分 / 作品被评价过的次数
	 */
	private double averageScore;

	/**
	 * 作品推荐级别
	 */
	private String level;

	/**
	 * 作品所属项目
	 */
	private Project project;
	/**
	 * 作品所属的学院
	 */
	private String academy;
	
	/**
	 * 作品评审意见
	 */
	private String appraisedAdvice;
	
	/**
	 * 作品推荐级别更改记录
	 */
	private String levelModifyRecord;
	
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

	public String getBdAddress() {
		return bdAddress;
	}

	public void setBdAddress(String bdAddress) {
		this.bdAddress = bdAddress;
	}

	public String getOpusSummary() {
		return opusSummary;
	}

	public void setOpusSummary(String opusSummary) {
		this.opusSummary = opusSummary;
	}

	public double getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(double averageScore) {
		this.averageScore = averageScore;
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

	public int getAppraisedNumber() {
		return appraisedNumber;
	}

	public void setAppraisedNumber(int appraisedNumber) {
		this.appraisedNumber = appraisedNumber;
	}

	public String getAppraisedAdvice() {
		return appraisedAdvice;
	}

	public void setAppraisedAdvice(String appraisedAdvice) {
		this.appraisedAdvice = appraisedAdvice;
	}

	public String getOpusCategory() {
		return opusCategory;
	}

	public void setOpusCategory(String opusCategory) {
		this.opusCategory = opusCategory;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public double getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(double totalScore) {
		this.totalScore = totalScore;
	}

	public String getLevelModifyRecord() {
		return levelModifyRecord;
	}

	public void setLevelModifyRecord(String levelModifyRecord) {
		this.levelModifyRecord = levelModifyRecord;
	}
}
