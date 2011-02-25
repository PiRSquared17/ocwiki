package org.ocwiki.test.hibernate;

import junit.framework.Assert;
import org.ocwiki.data.Choice;
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
		newQuestion.getChoices().add(new Choice(new Text("answer 0"), true));
		newQuestion.getChoices().add(createChoice1());
		newQuestion.getChoices().add(createChoice2());
		ArticleDAO.persist(newQuestion);
		
		Resource<Article> resource = ResourceDAO.fetchById(88);
		ResourceDAO.update(resource, newQuestion, admin, "unit test", true);
		
		Assert.assertSame(newQuestion, resource.getArticle());
		Assert.assertEquals(2, resource.getRevisions().size());
		Assert.assertEquals("unit test", ((Revision<Article>) CollectionUtils.get(resource.getRevisions(), 1)).getSummary());
		Assert.assertEquals(true, ((Revision<Article>) CollectionUtils.get(resource.getRevisions(), 1)).isMinor());
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
	
}
