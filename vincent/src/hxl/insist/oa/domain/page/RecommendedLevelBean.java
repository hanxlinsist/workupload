package hxl.insist.oa.domain.page;

public class RecommendedLevelBean {
	private String id;
	private String text;
	
	public RecommendedLevelBean(String id, String text) {
		super();
		this.id = id;
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
