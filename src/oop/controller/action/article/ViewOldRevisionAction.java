package oop.controller.action.article;

import oop.controller.action.AbstractAction;
import oop.data.Article;
import oop.data.Resource;
import oop.data.Revision;
import oop.db.dao.RevisionDAO;

public class ViewOldRevisionAction extends AbstractAction {

	private Resource<? extends Article> resource;
	private Revision<? extends Article> revision;
	private Article article;

	@Override
	protected void performImpl() throws Exception {
		long id = getParams().getLong("id");
		revision = RevisionDAO.fetch(id);
		resource = revision.getResource();
		article = revision.getArticle();
	}

	public Resource<? extends Article> getResource() {
		return resource;
	}

	public Revision<? extends Article> getRevision() {
		return revision;
	}

	public Article getArticle() {
		return article;
	}

}
