package hxl.insist.oa.domain.page;

/**
 * 评审数据分析功能的前端页面Bean
 * @author 韩兴隆
 */
public class ReviewerDataAnalysis {
	
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
	 * 工程名
	 */
	private String projectName;

	public ReviewerDataAnalysis(Long id, String name, String jobNumber, String projectName) {
		super();
		this.id = id;
		this.name = name;
		this.jobNumber = jobNumber;
		this.projectName = projectName;
	}

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

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
}
