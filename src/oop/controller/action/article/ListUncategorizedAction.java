package oop.controller.action.article;

import java.util.List;

import oop.controller.action.AbstractAction;
import oop.controller.action.AbstractResourceAction;
import oop.controller.action.ActionException;
import oop.data.Article;
import oop.data.Resource;
import oop.data.Topic;
import oop.db.dao.ArticleDAO;
import oop.db.dao.TopicDAO;

public class ListUncategorizedAction extends AbstractAction {

		private List<Resource<Article>> uncategorizedArticles;

		@Override
		public void performImpl() throws Exception {
			int start = getParams().getInt("start", 0);
			int size = 20;
			uncategorizedArticles = ArticleDAO.fetchUncategorized(start,size);
		}

		public List<Resource<Article>> getUncategorizedArticles() {
			return uncategorizedArticles;
		}

}
