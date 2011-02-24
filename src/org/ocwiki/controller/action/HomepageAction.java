package org.ocwiki.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.ocwiki.data.MultichoiceQuestion;
import org.ocwiki.data.Resource;
import org.ocwiki.data.Test;
import org.ocwiki.data.TestStructure;
import org.ocwiki.data.TextArticle;
import org.ocwiki.db.dao.MultichoiceQuestionDAO;
import org.ocwiki.db.dao.TestDAO;
import org.ocwiki.db.dao.TestStructureDAO;
import org.ocwiki.db.dao.TextArticleDAO;
import org.ocwiki.db.dao.UserDAO;

public class HomepageAction extends AbstractAction {

	private List<Resource<Test>> tests;
	private List<Resource<TestStructure>> testStructures;
	private List<Resource<MultichoiceQuestion>> questions;
	private List<Resource<TextArticle>> textArticles;
	private long testCount;
	private long questionCount;
	private long structCount;
	private long textArticleCount;
	private long userCount;

	@Override
	public void performImpl() throws IOException, ServletException {
		title("Trang chủ");
		tests = TestDAO.fetchLatest(0, 10);
		textArticles = TextArticleDAO.fetchNewest(0, 10);
		testStructures = TestStructureDAO.fetch(0, 10);
		questions = MultichoiceQuestionDAO.fetch(0, 10);
		testCount = TestDAO.count();
		textArticleCount = TextArticleDAO.count();
		questionCount = MultichoiceQuestionDAO.count();
		structCount = TestStructureDAO.count();
		userCount = UserDAO.count();
	}
	
	public List<Resource<Test>> getTests() {
		return tests;
	}
	
	public List<Resource<TestStructure>> getTestStructures() {
		return testStructures;
	}
	
	public List<Resource<MultichoiceQuestion>> getQuestions() {
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

	public List<Resource<TextArticle>> getTextArticles() {
		return textArticles;
	}

	public long getTextArticleCount() {
		return textArticleCount;
	}

}
