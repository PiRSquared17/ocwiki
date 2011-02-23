package org.ocwiki.module.topic;

import java.util.List;

import org.ocwiki.data.Resource;
import org.ocwiki.data.Topic;
import org.ocwiki.db.dao.TopicDAO;
import org.ocwiki.module.DefaultModule;

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
