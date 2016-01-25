package hxl.insist.oa.domain;

import java.io.Serializable;

/**
 * 学院
 * @author 韩兴隆
 */
@SuppressWarnings("serial")
public class Project implements Serializable {
	
	/**
	 * 主键
	 */
	private Long id;
	
	/**
	 * 项目名称
	 */
	private String projectName;
	
	/**
	 * 指标图片地址
	 */
	private String ironAddress;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getIronAddress() {
		return ironAddress;
	}

	public void setIronAddress(String ironAddress) {
		this.ironAddress = ironAddress;
	}
	
}