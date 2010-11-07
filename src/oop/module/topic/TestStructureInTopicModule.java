package oop.module.topic;

import java.util.List;

import oop.data.Resource;
import oop.data.TestStructure;
import oop.db.dao.TestStructureDAO;
import oop.module.DefaultModule;

public class TestStructureInTopicModule extends DefaultModule {

	private List<Resource<TestStructure>> tests;

	@Override
	public void init() throws Exception {
		tests = TestStructureDAO.fetchByTopic(getResource().getId(), 0, 20);
	}
	
	public List<Resource<TestStructure>> getTestStructures() {
		return tests;
	}
	
}
