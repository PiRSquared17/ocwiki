package oop.controller.action;

import java.util.List;

import oop.data.BaseQuestion;
import oop.data.Test;
import oop.data.TestStructure;
import oop.db.dao.BaseQuestionDAO;
import oop.db.dao.TestDAO;
import oop.db.dao.TestStructureDAO;
import oop.db.dao.UserDAO;

public class HomepageAction extends AbstractAction {

	private List<TestStructure> testStructures;
	private List<Test> tests;
	private List<BaseQuestion> questions;
	private long testCount;
	private long questionCount;
	private long structCount;
	private long userCount;
	
	@Override
	public void performImpl() throws Exception {
		title("Trang chủ website trắc nghiệm :: Powered by CSForce");
		testStructures = TestStructureDAO.fetch(0, 10);
		tests = TestDAO.fetchCreateDateOrder(0, 10);
		questions = BaseQuestionDAO.fetch(0, 10);
		testCount = TestDAO.count();
		questionCount = BaseQuestionDAO.count();
		structCount = TestStructureDAO.count();
		userCount = UserDAO.count();
	}
	
	public List<TestStructure> getTestStructures() {
		return testStructures;
	}
	
	public List<Test> getTests() {
		return tests;
	}
	
	public List<BaseQuestion> getQuestions() {
		return questions;
	}
	
	public long getTestCount() {
		return testCount;
	}
	
	public long getQuestionCount() {
		return questionCount;
	}
	
	public long getStructCount() {
		return structCount;
	}
	
	public long getUserCount() {
		return userCount;
	}

}
