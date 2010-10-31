package oop.controller.action.topic;

import java.util.List;

import oop.controller.action.AbstractAction;
import oop.controller.action.AbstractResourceAction;
import oop.controller.action.ActionException;
import oop.data.Resource;
import oop.data.Topic;
import oop.db.dao.TopicDAO;

public class ListUncategorizedAction extends AbstractAction {

		private List<Resource<Topic>> uncategorizedTopics;

		@Override
		public void performImpl() throws Exception {
			int start = getParams().getInt("start", 0);
			int size = 20;
			uncategorizedTopics = TopicDAO.fetchUncategorized(start,size);
		}

		public List<Resource<Topic>> getUncategorizedTopics() {
			return uncategorizedTopics;
		}

}
