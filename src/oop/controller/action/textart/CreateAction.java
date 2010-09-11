package oop.controller.action.textart;

import oop.controller.action.AbstractAction;
import oop.data.Namespace;
import oop.data.Resource;
import oop.data.Text;
import oop.data.TextArticle;
import oop.db.dao.NamespaceDAO;
import oop.db.dao.ResourceDAO;

public class CreateAction extends AbstractAction {

	@Override
	protected void performImpl() throws Exception {
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
