package oop.controller.action.revision;

import oop.controller.action.AbstractAction;
import oop.controller.action.ActionException;
import oop.data.Article;
import oop.data.Revision;
import oop.db.dao.RevisionDAO;

public class ViewAction extends AbstractAction {
	private Revision<Article> revision;
	private Article article;

	@Override
	protected void performImpl() throws Exception {
		// TODO Auto-generated method stub
		long id = getParams().getLong("id");
		revision = RevisionDAO.fetch(id);
		if (revision == null) {
			throw new ActionException("Không tìm thấy phiên bản"
					+ revision.getId());
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
