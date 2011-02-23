package org.ocwiki.controller.action.article;

import java.io.IOException;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractResourceAction;
import org.ocwiki.data.Article;
import org.ocwiki.db.dao.ResourceDAO;

public class EditAction<T extends Article> extends AbstractResourceAction<T> {

	@Override
	protected void performImpl() throws IOException, ServletException {
		long id = getParams().getLong("id");
		resource = ResourceDAO.fetchById(id);
		title("Sửa bài viết " + resource.getName());
	}
	
	public T getArticle() {
		return resource.getArticle();
	}

}