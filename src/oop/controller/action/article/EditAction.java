package oop.controller.action.article;

import java.io.IOException;

import javax.servlet.ServletException;

import oop.controller.action.AbstractResourceAction;
import oop.data.Article;
import oop.db.dao.ResourceDAO;

public class EditAction<T extends Article> extends AbstractResourceAction<T> {

	@Override
	protected void performImpl() throws IOException, ServletException {
		long id = getParams().getLong("id");
		resource = ResourceDAO.fetchById(id);
	}
	
	public T getArticle() {
		return resource.getArticle();
	}

}