package oop.test.hibernate;

import oop.data.Answer;
import oop.data.BaseQuestion;
import oop.data.Status;
import oop.db.dao.AnswerDAO;
import oop.db.dao.BaseQuestionDAO;
import oop.db.dao.TextDAO;
import oop.persistence.HibernateUtil;

import org.junit.Assert;
import org.junit.Test;

public class AnswerTest extends HibernateTest {

	public AnswerTest() {
		super("answer.xml");
	}
	
	@Test
	public void fetch() {
		Answer answer = AnswerDAO.fetch(1);
		Assert.assertEquals("to walk", answer.getContent().getText());
	}
	
	@Test
	public void update() {
		Answer answer = AnswerDAO.fetch(1);
		answer.setContent(TextDAO.create("no longer walk"));
		HibernateUtil.getSession().flush();
		Assert.assertEquals("no longer walk", answer.getContent().getText());
	}
	
	@Test
	public void create() {
		BaseQuestion question = BaseQuestionDAO.create("xyz", 2, 1);
		Answer answer = AnswerDAO.create(question.getId(), "abc", false);
		Assert.assertEquals(Status.NORMAL, answer.getStatus());
	}
	
}
