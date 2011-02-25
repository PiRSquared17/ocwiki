package org.ocwiki.test.hibernate;

import java.util.List;

import org.ocwiki.data.Choice;
import org.ocwiki.data.MultichoiceQuestion;
import org.ocwiki.data.Namespace;
import org.ocwiki.data.ResourceSearchReport;
import org.ocwiki.data.Text;
import org.ocwiki.db.dao.ArticleDAO;
import org.ocwiki.persistence.HibernateUtil;

import org.junit.Assert;
import org.junit.Test;

public class ArticleTest extends HibernateTest {

	@Test
	public void testPersist() {
		MultichoiceQuestion question = ArticleDAO.fetchById(88);
		MultichoiceQuestion newQuestion = question.copy();
		ArticleDAO.persist(newQuestion);
		
		Assert.assertTrue("Chưa lưu được câu hỏi", newQuestion.getId() > 0);
		Assert.assertTrue("Ghi đè vào câu hỏi cũ",
				newQuestion.getId() != question.getId());
	}

	@Test
	public void testDetached() {
		MultichoiceQuestion newQuestion = new MultichoiceQuestion(new Namespace(3, ""),
				new Text("xyz"), 3);
		newQuestion.getChoices().add(new Choice(new Text("answer 0"), true));
		newQuestion.getChoices().add(createChoice1());
		newQuestion.getChoices().add(createChoice2());
		ArticleDAO.persist(newQuestion);
		
		Assert.assertTrue(newQuestion.getId() != 0);
		Assert.assertTrue(newQuestion.getChoices().get(0).getId() != 0);
		Assert.assertTrue(newQuestion.getChoices().get(1).getId() == 321);
		Assert.assertTrue(newQuestion.getChoices().get(2).getId() == 322);
		Assert.assertTrue(newQuestion.getChoices().get(2).getContent().getId() == 57);
		
		// assert immutation
		long id = newQuestion.getId();
		HibernateUtil.closeSession(); // close session to fetch data again
		newQuestion = ArticleDAO.fetchById(id);
		Assert.assertEquals("will be carrying", newQuestion.getChoices().get(2)
				.getContent().getText());
	}

	private Choice createChoice2() {
		Text text2 = new Text("linh tinh");
		text2.setId(57);
		Choice choice2 = new Choice(text2, false);
		choice2.setId(322);
		return choice2;
	}

	private Choice createChoice1() {
		Text text1 = new Text("carry");
		text1.setId(56);
		Choice choice1 = new Choice(text1, false);
		choice1.setId(321);
		return choice1;
	}
	
	@Test
	public void testFetchByTopics() {
		List<ResourceSearchReport<org.ocwiki.data.Test>> result = ArticleDAO
				.fetchRelated(org.ocwiki.data.Test.class, 62, 0, 3);
		Assert.assertEquals(3, result.size());
	}
	
}
