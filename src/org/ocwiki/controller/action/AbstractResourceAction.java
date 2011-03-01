package org.ocwiki.controller.action;

import org.ocwiki.data.Article;
import org.ocwiki.data.Resource;

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
