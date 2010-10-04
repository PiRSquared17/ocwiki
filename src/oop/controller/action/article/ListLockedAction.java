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

public class ListLockedAction extends AbstractAction {

		private List<Resource<Article>> lockedArticles;

		@Override
		public void performImpl() throws Exception {
			int start = getParams().getInt("start", 0);
			int size = 20;
			lockedArticles = ArticleDAO.fetchLocked(start,size);
		}

		public List<Resource<Article>> getLockedArticles() {
			return lockedArticles;
		}

}
