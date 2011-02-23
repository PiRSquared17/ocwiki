package org.ocwiki.module.topic;

import java.util.List;

import org.ocwiki.data.Resource;
import org.ocwiki.data.Test;
import org.ocwiki.db.dao.TestDAO;
import org.ocwiki.module.DefaultModule;

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
