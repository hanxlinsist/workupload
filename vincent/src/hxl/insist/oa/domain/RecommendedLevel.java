package hxl.insist.oa.domain;

/**
 * 作品推荐级别
 * @author xlinsist
 *
 */
public class RecommendedLevel {
	/**
	 * 主键
	 */
	private Long id;
	
	/**
	 * 推荐级别名称
	 */
	private String name;
	
	/**
	 * 推荐级别所属项目
	 */
	private Project project;
	

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

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	
}
