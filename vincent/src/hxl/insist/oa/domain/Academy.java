package hxl.insist.oa.domain;

import java.io.Serializable;

/**
 * 学院
 * @author 韩兴隆
 */
@SuppressWarnings("serial")
public class Academy implements Serializable {
	
	/**
	 * 主键
	 */
	private Long id;
	
	/**
	 * 学院名称
	 */
	private String name;

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

}