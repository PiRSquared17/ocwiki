package org.ocwiki.controller.action.article;

import java.io.IOException;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractResourceAction;
import org.ocwiki.data.Article;
import org.ocwiki.data.Revision;
import org.ocwiki.db.dao.RevisionDAO;

public class ViewOldRevisionAction extends AbstractResourceAction<Article> {

	private Revision<Article> revision;
	private Revision<Article> previousRevision;
	private Revision<Article> nextRevision;
	private Article article;

	@Override
	protected void performImpl() throws IOException, ServletException {
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
