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
		private long count;
		private long curStart;
		
		@Override
		public void performImpl() throws Exception {
			int start = getParams().getInt("start", 0);
			int size = 20;
			title("Dang sách các bài viết chưa được phân loại, trang " + ((start/size)+1));
			uncategorizedArticles = ArticleDAO.fetchUncategorized(start,size);
			count = ArticleDAO.countUncategorized();
			curStart = start;
		}

		public List<Resource<Article>> getUncategorizedArticles() {
			return uncategorizedArticles;
		}

		public long getCount() {
			return count;
		}

		public long getCurStart() {
			return curStart;
		}
		
		
}
