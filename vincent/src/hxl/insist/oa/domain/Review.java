package hxl.insist.oa.domain;

import java.io.Serializable;

/**
 * 评审表，记录评审者对作品作出的一些评价
 * @author 韩兴隆
 */
@SuppressWarnings("serial")
public class Review implements Serializable {
	
	/**
	 * 主键
	 */
	private Long id;
	
	/**
	 * 作品
	 */
	private Opus opus;
	
	/**
	 * 评审者
	 */
	private Reviewer reviewer;
	
	/**
	 * 评审为当前作品打的分数
	 */
	private double score;
	
	/**
	 * 评审是否推荐当前作品
	 * 0 -- 不推荐
	 * 1 -- 推荐
	 */
	private int isRecommend = 1;
	
	/**
	 * 所属项目
	 */
	private Project project;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Opus getOpus() {
		return opus;
	}

	public void setOpus(Opus opus) {
		this.opus = opus;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public int getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(int isRecommend) {
		this.isRecommend = isRecommend;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Reviewer getReviewer() {
		return reviewer;
	}

	public void setReviewer(Reviewer reviewer) {
		this.reviewer = reviewer;
	}

}