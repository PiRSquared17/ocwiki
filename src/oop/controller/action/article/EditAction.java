package oop.controller.action.article;

import oop.controller.action.AbstractAction;
import oop.data.Article;
import oop.data.Resource;
import oop.db.dao.ResourceDAO;

public class EditAction extends AbstractAction {

	private Resource<? extends Article> resource;
	private Article article;

	@Override
	protected void performImpl() throws Exception {
		long id = getParams().getLong("id");
		resource = ResourceDAO.fetchById(id);
		article = resource.getArticle();
	}
	
	public Resource<? extends Article> getResource() {
		return resource;
	}

	public Article getArticle() {
		return article;
	}

}