package oop.test.hibernate.search;

import java.util.List;

import oop.data.BaseQuestion;
import oop.data.Resource;
import oop.persistence.HibernateUtil;
import oop.test.rest.servletunit.AbstractResourceTest;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.util.Version;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.junit.Test;

public class FirstSearchTest extends AbstractResourceTest {

	@Test
	public void firstSearch() throws ParseException {
		Session session = HibernateUtil.getSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		Transaction tx = fullTextSession.beginTransaction();
		// create native Lucene query
		String[] fields = new String[] { "article.name", "article.content.text", "author.name", "answers.content.text" };
		MultiFieldQueryParser parser = new MultiFieldQueryParser(
				Version.LUCENE_29, fields, new StandardAnalyzer(Version.LUCENE_29));
		org.apache.lucene.search.Query query = parser.parse("\"would he like\"");
		// wrap Lucene query in a org.hibernate.Query
		org.hibernate.Query hibQuery = fullTextSession.createFullTextQuery(
				query, Resource.class, BaseQuestion.class);
		// execute search
		List result = hibQuery.list();
		System.out.println(result);
		tx.commit();
		session.close();

	}

}
