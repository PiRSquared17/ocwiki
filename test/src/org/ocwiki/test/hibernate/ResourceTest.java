package org.ocwiki.test.hibernate;

import junit.framework.Assert;
import org.ocwiki.data.Answer;
import org.ocwiki.data.Article;
import org.ocwiki.data.MultichoiceQuestion;
import org.ocwiki.data.Namespace;
import org.ocwiki.data.Resource;
import org.ocwiki.data.Revision;
import org.ocwiki.data.Text;
import org.ocwiki.data.User;
import org.ocwiki.db.dao.ArticleDAO;
import org.ocwiki.db.dao.ResourceDAO;
import org.ocwiki.db.dao.UserDAO;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

@SuppressWarnings("unchecked")
public class ResourceTest extends HibernateTest {

	@Test
	public void testSaveDetached() {
		User admin = UserDAO.fetchById(1);
		MultichoiceQuestion newQuestion = new MultichoiceQuestion(new Namespace(3, ""),
				new Text("xyz"), 3);
		newQuestion.getAnswers().add(new Answer(new Text("answer 0"), true));
		newQuestion.getAnswers().add(createAnswer1());
		newQuestion.getAnswers().add(createAnswer2());
		ArticleDAO.persist(newQuestion);
		
		Resource<Article> resource = ResourceDAO.fetchById(88);
		ResourceDAO.update(resource, newQuestion, admin, "unit test", true);
		
		Assert.assertSame(newQuestion, resource.getArticle());
		Assert.assertEquals(2, resource.getRevisions().size());
		Assert.assertEquals("unit test", ((Revision<Article>) CollectionUtils.get(resource.getRevisions(), 1)).getSummary());
		Assert.assertEquals(true, ((Revision<Article>) CollectionUtils.get(resource.getRevisions(), 1)).isMinor());
	}

	private Answer createAnswer2() {
		Text text2 = new Text("linh tinh");
		text2.setId(57);
		Answer answer2 = new Answer(text2, false);
		answer2.setId(322);
		return answer2;
	}

	private Answer createAnswer1() {
		Text text1 = new Text("carry");
		text1.setId(56);
		Answer answer1 = new Answer(text1, false);
		answer1.setId(321);
		return answer1;
	}
	
}
