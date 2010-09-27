package oop.module.topic;

import java.util.List;

import oop.data.Resource;
import oop.data.Test;
import oop.db.dao.TestDAO;
import oop.module.DefaultModule;

public class TestInTopicModule extends DefaultModule {

	private List<Resource<Test>> tests;

	@Override
	public void init() throws Exception {
		tests = TestDAO.fetchByTopic(getResource().getId(), 0, 20);
	}
	
	public List<Resource<Test>> getTests() {
		return tests;
	}
	
}
