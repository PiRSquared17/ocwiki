package oop.module.topic;

import java.util.List;

import oop.data.Resource;
import oop.data.Topic;
import oop.db.dao.TopicDAO;
import oop.module.DefaultModule;

public class ChildrenModule extends DefaultModule {

	private List<Resource<Topic>> children;

	@Override
	public void init() throws Exception {
		children = TopicDAO.fetchChildren(getResource().getId());
	}
	
	public List<Resource<Topic>> getChildren() {
		return children;
	}
	
}
