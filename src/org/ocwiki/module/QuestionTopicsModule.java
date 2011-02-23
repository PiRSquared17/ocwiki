package org.ocwiki.module;

import java.sql.SQLException;
import java.util.List;

import org.ocwiki.data.Resource;
import org.ocwiki.data.Topic;
import org.ocwiki.db.dao.TopicDAO;

public class QuestionTopicsModule extends DefaultModule {

	private List<Resource<Topic>> topLevels;

	public QuestionTopicsModule() throws SQLException {
		topLevels = TopicDAO.fetchTopLevels();
	}
	
	public List<Resource<Topic>> getTopics() {
		return topLevels;
	}

}
