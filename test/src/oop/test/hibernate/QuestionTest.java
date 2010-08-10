package oop.test.hibernate;

import java.util.List;
import java.util.Set;

import oop.data.Answer;
import oop.data.Article;
import oop.data.BaseQuestion;
import oop.db.dao.AnswerDAO;
import oop.db.dao.BaseQuestionDAO;
import oop.persistence.HibernateUtil;

import org.junit.Assert;
import org.junit.Test;

public class QuestionTest extends HibernateTest {

	public QuestionTest() {
		super("question.xml");
	}
	
	@Test
	public void fetchById() {
		BaseQuestion question = BaseQuestionDAO.fetchById(1);
		Assert.assertEquals(3, question.getContent().getId());
		Assert.assertEquals(3, question.getLevel());
		//XXX test topics
		Assert.assertEquals(1, question.getAuthor().getId());
		Assert.assertEquals(4, question.getAnswers().size());
		Assert.assertEquals("has walked", question.getAnswerById().get(4L)
				.getContent().getText());
	}
	
	@Test
	public void fetch() {
		List<BaseQuestion> questions = BaseQuestionDAO.fetch(0, 10);
		Assert.assertEquals(2, questions.size());
		Assert.assertEquals(2, BaseQuestionDAO.count());
	}
	
	@Test
	public void create() {
		Article newQuestion = BaseQuestionDAO.create("câu hỏi mới", 1, 1);
		Assert.assertTrue(newQuestion.getId() > 0);
	}
	
	@Test
	public void createAnswer() {
		BaseQuestion question = BaseQuestionDAO.fetchById(1);
		Answer answer = AnswerDAO.create(1, "câu trả lời 1", true);

		Assert.assertTrue(answer.getId() > 0);
		Assert.assertEquals(answer.getQuestion().getId(), 1);
		Assert.assertEquals(5, question.getAnswerById().size());
		Assert.assertEquals(5, question.getAnswers().size());
	}
	
	@Test
	public void findContent() {
		List<BaseQuestion> questions = BaseQuestionDAO.fetchByContent(
				"%Neil Armstrong%", 2);
		Assert.assertEquals(1, questions.size());
		Assert.assertTrue(questions.get(0).getContent().getText().startsWith(
				"Neil Armstrong"));
	}
	
	@Test
	public void fetchRandomly() {
		List<BaseQuestion> questions = BaseQuestionDAO.fetchRandomly(0, 2, 2);
		Assert.assertEquals(1, questions.size());
		Assert.assertEquals(1, questions.get(0).getId());
	}
	
	@Test
	public void fetchByTopic() {
		List<BaseQuestion> questions = BaseQuestionDAO.fetchByTopic(2, 0, 2);
		Assert.assertEquals(1, questions.size());
		Assert.assertEquals(1, BaseQuestionDAO.countByTopic(2));
		Assert.assertEquals(1, questions.get(0).getId());
	}
	
	@Test
	public void fetchByAuthor() {
		List<BaseQuestion> questions = BaseQuestionDAO.fetchByAuthor(1, 0, 3);
		Assert.assertEquals(2, questions.size());
		Assert.assertEquals(2, BaseQuestionDAO.countByAuthor(1));
		Assert.assertEquals(2, questions.get(0).getId());
		Assert.assertEquals(1, questions.get(1).getId());
	}
	
//	@Test
	public void deleteAnswer2() {
		BaseQuestion question = BaseQuestionDAO.fetchById(1);
		Assert.assertEquals(4, question.getAnswers().size());
		Answer answer = question.getAnswers().iterator().next();
		AnswerDAO.delete(answer.getId());
		Assert.assertEquals(3, question.getAnswers().size());
	}
	
	@Test
	public void deleteAnswer() {
		BaseQuestion question = BaseQuestionDAO.fetchById(1);
		HibernateUtil.getSession().refresh(question);
		Set<Answer> answers = question.getAnswers();
		int size = answers.size(); // get it initialized
		Assert.assertEquals(4, size);
		Answer answer = answers.iterator().next();
		AnswerDAO.delete(answer.getId());
		Assert.assertEquals(3, size);
	}
	
}
