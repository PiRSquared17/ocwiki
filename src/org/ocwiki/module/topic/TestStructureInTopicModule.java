package org.ocwiki.module.topic;

import java.util.List;

import org.ocwiki.data.Resource;
import org.ocwiki.data.TestStructure;
import org.ocwiki.db.dao.TestStructureDAO;
import org.ocwiki.module.DefaultModule;

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
