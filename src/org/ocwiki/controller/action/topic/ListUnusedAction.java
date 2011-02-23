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

public class ListUnusedAction extends AbstractAction {

		private List<Resource<Topic>> unusedTopics;
		private long count;
		private long curStart;

		@Override
		public void performImpl() throws IOException, ServletException {
			int start = getParams().getInt("start", 0);
			int size = 20;
			title("Dang sách các chủ đề chưa được sử dụng, trang " + ((start/size)+1));
			unusedTopics = TopicDAO.fetchUnused(start,size);
			count = TopicDAO.countUnused();
			curStart = start;
		}

		public List<Resource<Topic>> getUnusedTopics() {
			return unusedTopics;
		}

		public long getCount() {
			return count;
		}

		public long getCurStart() {
			return curStart;
		}

}
