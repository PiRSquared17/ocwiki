package oop.controller.action.article;

import oop.controller.action.AbstractAction;
import oop.data.Article;
import oop.data.Revision;
import oop.db.dao.RevisionDAO;

public class ViewOldRevisionAction extends AbstractAction<Article> {

	private Revision<Article> revision;
	private Revision<Article> previousRevision;
	private Revision<Article> nextRevision;
	private Article article;

	@Override
	protected void performImpl() throws Exception {
		long id = getParams().getLong("id");
		revision = RevisionDAO.fetch(id);
		previousRevision = RevisionDAO.fetchPreviousRevision(id);
		nextRevision = RevisionDAO.fetchNextRevision(id);
		resource = revision.getResource();
		article = revision.getArticle();
	}

	public Revision<? extends Article> getPreviousRevision() {
		return previousRevision;
	}

	public Revision<? extends Article> getNextRevision() {
		return nextRevision;
	}

	public Revision<Article> getRevision() {
		return revision;
	}

	public Article getArticle() {
		return article;
	}

}
