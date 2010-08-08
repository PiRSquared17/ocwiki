package oop.controller.action.article;

import java.util.List;

import oop.change.Change;
import oop.controller.action.AbstractAction;
import oop.data.Article;
import oop.db.dao.ArticleDAO;
import oop.db.dao.ChangeDAO;

import com.oreilly.servlet.ParameterNotFoundException;

public class ChangeLogAction extends AbstractAction {

	private List<Change> changes;

	@Override
	protected void performImpl() throws Exception {
		try {
			String name = getParams().getString("article");
			Article article = ArticleDAO.fetchByName(name);
			if (article == null) {
				error("Không tìm thấy bài viết có tên: " + name);
			}
			changes = ChangeDAO.fetchByArticle(article);
		} catch (ParameterNotFoundException ex) {
			error("Thiếu tên bài viết");
		}
	}

	public List<Change> getChanges() {
		return changes;
	}
	
}
