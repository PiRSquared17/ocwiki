package oop.test.hibernate;

import oop.data.Answer;
import oop.data.Text;
import oop.db.dao.AnswerDAO;
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
	
	public void modify() {
		Answer answer = AnswerDAO.fetch(1);
		answer.setContent(new Text("no longer walk"));
		HibernateUtil.getSession().flush();
		Assert.assertEquals("no longer walk", answer.getContent().getText());
	}
	
}
