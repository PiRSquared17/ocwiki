package oop.controller.action.article;

import oop.controller.action.AbstractAction;
import oop.data.Article;
import oop.db.dao.ResourceDAO;

public class EditAction<T extends Article> extends AbstractAction<T> {

	@Override
	protected void performImpl() throws Exception {
		long id = getParams().getLong("id");
		resource = ResourceDAO.fetchById(id);
	}
	
	public T getArticle() {
		return resource.getArticle();
	}

}