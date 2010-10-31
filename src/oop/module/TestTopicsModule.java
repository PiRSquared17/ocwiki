package oop.module;

import java.sql.SQLException;
import java.util.List;

import oop.data.Resource;
import oop.data.Topic;
import oop.db.dao.TopicDAO;

public class TestTopicsModule extends DefaultModule {

	private List<Resource<Topic>> topLevels;

	public TestTopicsModule() throws SQLException {
		topLevels = TopicDAO.fetchTopLevels();
	}
	
	public List<Resource<Topic>> getTopics() {
		return topLevels;
	}

}
