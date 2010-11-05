package oop.controller.action.article;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

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
		private long count;
		private long curStart;

		@Override
		public void performImpl() throws IOException, ServletException {
			int start = getParams().getInt("start", 0);
			int size = 20;
			title("Dang sách các bài viết bị khóa, trang " + ((start/size)+1));
			lockedArticles = ArticleDAO.fetchLocked(start,size);
			count = ArticleDAO.countLocked();
			curStart = start;
		}

		public List<Resource<Article>> getLockedArticles() {
			return lockedArticles;
		}

		public long getCount() {
			return count;
		}

		public long getCurStart() {
			return curStart;
		}
}
