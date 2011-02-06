package oop.module;

import oop.data.Article;
import oop.data.Resource;
import oop.db.dao.ResourceDAO;

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
