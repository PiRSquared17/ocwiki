package oop.controller.action.topic;

import java.util.List;

import oop.controller.action.AbstractAction;
import oop.controller.action.AbstractResourceAction;
import oop.controller.action.ActionException;
import oop.data.Resource;
import oop.data.Topic;
import oop.db.dao.TopicDAO;

public class ListUnusedAction extends AbstractAction {

		private List<Resource<Topic>> unusedTopics;

		@Override
		public void performImpl() throws Exception {
			unusedTopics = TopicDAO.fetchUnused();
		}

		public List<Resource<Topic>> getUnusedTopics() {
			return unusedTopics;
		}
		
		

}
