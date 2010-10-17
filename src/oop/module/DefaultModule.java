package oop.module;

import oop.data.Article;
import oop.data.Resource;

public class DefaultModule implements Module {

	private String title;
	private String page;
	private int position;
	private Resource<? extends Article> resource;
	
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
	
	@Override
	public Resource<? extends Article> getResource() {
		return resource;
	}
	
	@Override
	public <T extends Article> void setResource(Resource<T> resource) {
		this.resource = resource;
	}

	@Override
	public Article getArticle() {
		return resource == null ? null : resource.getArticle();
	}
	
}
