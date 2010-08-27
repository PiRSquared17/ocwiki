package oop.module;

public class DefaultModule implements Module {

	private String title;
	private String page;
	private int position;
	
	@Override
	public void init() throws Exception {
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public String getPage() {
		return page;
	}
	
	@Override
	public void setPage(String page) {
		this.page = page;
	}

	public void setOrder(int position) {
		this.position = position;
	}

	public int getOrder() {
		return position;
	}
	
}
