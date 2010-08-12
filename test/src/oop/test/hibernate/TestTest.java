package oop.test.hibernate;

import java.util.List;
import java.util.Map;

import oop.data.Question;
import oop.data.Section;
import oop.db.dao.QuestionDAO;
import oop.db.dao.SectionDAO;
import oop.db.dao.TestDAO;

import org.junit.Assert;
import org.junit.Test;

public class TestTest extends HibernateTest {

	public TestTest() {
		super("test.xml");
	}
	
	@Test
	public void fetchQuestion() {
		Question question = QuestionDAO.fetchById(1);
		Assert.assertEquals(1, question.getBase().getId());
		Assert.assertEquals(5, question.getAnswers().size());
		Assert.assertEquals(5, question.getAnswerById().size());
		Assert.assertEquals(4, question.getBase().getAnswers().size());
	}
	
	@Test
	public void fetchSection() {
		Section section = SectionDAO.fetchById(1);
		Assert.assertEquals(5, section.getQuestions().size());
		Assert.assertEquals("Part I - Listening", section.getText().getText());
		Map<Long, Question> questionById = section.getQuestionById();
		Assert.assertNotNull(questionById.get(1L));
		Assert.assertNotNull(questionById.get(2L));
		Assert.assertNotNull(questionById.get(3L));
		Assert.assertNotNull(questionById.get(4L));
		Assert.assertNotNull(questionById.get(5L));
	}
	
	@Test
	public void fetchTest() {
		List<oop.data.Test> tests = TestDAO.fetch(0, 5);
		Assert.assertEquals(1, TestDAO.count());
		Assert.assertEquals(1, tests.size());
		Assert.assertEquals(6, tests.get(0).getId());
		Assert.assertTrue(!tests.get(0).isDeleted());
	}
	
}
