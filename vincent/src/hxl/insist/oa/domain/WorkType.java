package hxl.insist.oa.domain;

/**
 * 作品类型
 * @author xlinsist
 *
 */
public class WorkType {
	/**
	 * 主键
	 */
	private Long id;
	
	/**
	 * 作品类型名称
	 */
	private String name;
	
	/**
	 * 作品类型所属项目
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
