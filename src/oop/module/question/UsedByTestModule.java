package oop.module.question;

import java.util.List;

import oop.data.Resource;
import oop.data.Test;
import oop.db.dao.TestDAO;
import oop.module.DefaultModule;

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
