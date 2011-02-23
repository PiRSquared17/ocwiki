package org.ocwiki.module;

import org.ocwiki.data.Article;
import org.ocwiki.data.Resource;
import org.ocwiki.db.dao.ResourceDAO;

public class ArticleModule extends DefaultModule {

	private Resource<? extends Article> displayedResource;
	
	@Override
	public void init() throws Exception {
		long id = Long.parseLong(getParam("resource-id"));
		displayedResource = ResourceDAO.fetchById(id);
	}

	public Resource<? extends Article> getDisplayedResource() {
		return displayedResource;
	}
	
}
