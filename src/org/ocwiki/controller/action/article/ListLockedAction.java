package org.ocwiki.controller.action.article;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractAction;
import org.ocwiki.controller.action.AbstractResourceAction;
import org.ocwiki.controller.action.ActionException;
import org.ocwiki.data.Article;
import org.ocwiki.data.Resource;
import org.ocwiki.data.Topic;
import org.ocwiki.db.dao.ArticleDAO;
import org.ocwiki.db.dao.TopicDAO;

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
