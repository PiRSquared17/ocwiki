package org.ocwiki.controller.action.revision;

import java.io.IOException;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractAction;
import org.ocwiki.controller.action.ActionException;
import org.ocwiki.data.Article;
import org.ocwiki.data.Revision;
import org.ocwiki.db.dao.RevisionDAO;

public class ViewAction extends AbstractAction {
	private Revision<Article> revision;
	private Article article;

	@Override
	protected void performImpl() throws IOException, ServletException {
		long id = getParams().getLong("revID");
		revision = RevisionDAO.fetch(id);
		if (revision == null) {
			throw new ActionException("Không tìm thấy phiên bản" + id);
		}
		article = revision.getArticle();
		if (article == null) {
			throw new ActionException("Không tìm thấy bài viết");
		}
		title("Phiên bản : " + revision.getId() + " của tài nguyên "
				+ revision.getResource().getName());
	}

	public Revision<Article> getRevision() {
		return revision;
	}

	public Article getArticle() {
		return article;
	}

}
