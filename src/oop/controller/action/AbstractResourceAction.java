package oop.controller.action;

import oop.data.Article;
import oop.data.Resource;

public abstract class AbstractResourceAction<T extends Article> extends AbstractAction {

	protected Resource<T> resource;
	

	@Override
	public Resource<T> getResource() {
		return resource;
	}
	
	protected void setResource(Resource<T> resource) {
		this.resource = resource;
	}
	
	@Override
	public Article getArticle() {
		return resource == null ? null : resource.getArticle();
	}
	
}
