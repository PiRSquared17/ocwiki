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
			uncategorizedTopics = TopicDAO.fetchUncategorized();
		}

		public List<Resource<Topic>> getUncategorizedTopics() {
			return uncategorizedTopics;
		}

}
