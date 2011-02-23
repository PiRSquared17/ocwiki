package org.ocwiki.controller.action.topic;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractAction;
import org.ocwiki.controller.action.AbstractResourceAction;
import org.ocwiki.controller.action.ActionException;
import org.ocwiki.data.Resource;
import org.ocwiki.data.Topic;
import org.ocwiki.db.dao.TopicDAO;

public class ListUncategorizedAction extends AbstractAction {

		private List<Resource<Topic>> uncategorizedTopics;
		private long count;
		private long curStart;

		@Override
		public void performImpl() throws IOException, ServletException {
			int start = getParams().getInt("start", 0);
			int size = 20;
			title("Dang sách các chủ đề chưa được phân loại, trang " + ((start/size)+1));
			uncategorizedTopics = TopicDAO.fetchUncategorized(start,size);
			count = TopicDAO.countUncategorized();
			curStart = start;
		}

		public List<Resource<Topic>> getUncategorizedTopics() {
			return uncategorizedTopics;
		}

		public long getCount() {
			return count;
		}

		public long getCurStart() {
			return curStart;
		}
		
		
}
