package org.ocwiki.controller.action.textart;

import java.io.IOException;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractAction;
import org.ocwiki.data.Namespace;
import org.ocwiki.data.Resource;
import org.ocwiki.data.Text;
import org.ocwiki.data.TextArticle;
import org.ocwiki.db.dao.NamespaceDAO;
import org.ocwiki.db.dao.ResourceDAO;

public class CreateAction extends AbstractAction {

	@Override
	protected void performImpl() throws IOException, ServletException {
		if (getParams().hasParameter("submit")) {
			String name = getParams().getString("name");
			Namespace namespace = NamespaceDAO.fetch(getParams().getLong("namespace"));
			Text content = new Text(getParams().getString("content"));
			TextArticle article = new TextArticle(name, namespace, content);
			Resource<TextArticle> resource = ResourceDAO.create(getUser(), TextArticle.class, article);
			setNextAction("article.view?id=" + resource.getId());
		}
	}

}
