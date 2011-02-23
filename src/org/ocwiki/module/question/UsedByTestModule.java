package org.ocwiki.module.question;

import java.util.List;

import org.ocwiki.data.Resource;
import org.ocwiki.data.Test;
import org.ocwiki.db.dao.TestDAO;
import org.ocwiki.module.DefaultModule;

public class UsedByTestModule extends DefaultModule {

	private List<Resource<Test>> tests;

	@Override
	public void init() throws Exception {
		tests = TestDAO.fetchByContainedQuestion(getResource().getId(), 0, 20);
	}
	
	public List<Resource<Test>> getTests() {
		return tests;
	}
	
}
