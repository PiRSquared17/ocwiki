package oop.controller.action.search;

import java.util.List;

import oop.controller.action.AbstractAction;
import oop.data.Article;
import oop.data.Resource;
import oop.persistence.HibernateUtil;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.util.Version;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;

public class SearchAction extends AbstractAction {

	private String query;
	private List<Resource<? extends Article>> results;
	private long count;

	@SuppressWarnings("unchecked")
	@Override
	protected void performImpl() throws Exception {
		query = getParams().getString("search_query");
		int start = getParams().getInt("start", 0);
		int size = getParams().getInt("size", 50);
		
		BooleanQuery luceneQuery = new BooleanQuery();
		QueryParser parser = new QueryParser(query);
		while (parser.hasNext()) {
			Criteria criteria = parser.next();
			Query subquery = toQuery(criteria);
			if (subquery != null) {
				luceneQuery.add(subquery, BooleanClause.Occur.MUST);
			}
		}
		FullTextSession fullTextSession = Search.getFullTextSession(HibernateUtil.getSession());
		FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery, Resource.class);
		fullTextQuery.setFirstResult(start);
		fullTextQuery.setMaxResults(size);
		results = fullTextQuery.list();
		count = fullTextQuery.getResultSize();
	}
	
	
	private Query toQuery(Criteria criteria) {
		switch (criteria.getTag()) {
		case NAME:
			return new TermQuery(new Term("article.name", criteria.getContent()));

		case TOPIC:
			return new TermQuery(new Term("article.topics.article.name",
					criteria.getContent()));
			
		case IS:
			if ("liked".equalsIgnoreCase(criteria.getContent()) ||
					"thích".equalsIgnoreCase(criteria.getContent())) {
				if (isUserLoggedIn()) {
					return new TermQuery(new Term("customization.likes",
							String.valueOf(getUser().getId())));
				}
			} else if ("todo".equalsIgnoreCase(criteria.getContent())
					|| "cần-làm".equalsIgnoreCase(criteria.getContent())) {
				if (isUserLoggedIn()) {
					return new TermQuery(new Term("customization.likes",
							String.valueOf(getUser().getId())));
				}
			} else if ("done".equalsIgnoreCase(criteria.getContent())
					|| "xong".equalsIgnoreCase(criteria.getContent())) {
				if (isUserLoggedIn()) {
					return new TermQuery(new Term("customization.likes",
							String.valueOf(getUser().getId())));
				}
			}
		case FULLTEXT:
			String[] fields = new String[] { "article.name",
					"article.content.text", "author.name", "answers.content.text" };
			MultiFieldQueryParser parser = new MultiFieldQueryParser(
					Version.LUCENE_29, fields, new StandardAnalyzer(Version.LUCENE_29));
			try {
				return parser.parse(criteria.getContent());
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			} 
		}
		return null;
	}

//	private boolean processSingleCriteria(int start, int size, QueryParser parser) {
//		Criteria criteria = parser.next();
//		switch (criteria.getTag()) {
//		case NAME:
//			results = ResourceDAO.fetchByNameLike("%"
//					+ criteria.getContent() + "%", start, size);
//			return true;
//
//		case TOPIC:
//			results = ResourceDAO.fetchByTopicLike("%"
//					+ criteria.getContent() + "%", start, size);
//			return true;
//			
//		case IS:
//			if ("liked".equalsIgnoreCase(criteria.getContent()) ||
//					"thích".equalsIgnoreCase(criteria.getContent())) {
//				if (isUserLoggedIn()) {
//					results = ResourceDAO.fetchLike(getUser().getId(),
//							ResourceLike.LIKE, start, size);
//				}
//			} else if ("todo".equalsIgnoreCase(criteria.getContent())
//					|| "cần-làm".equalsIgnoreCase(criteria.getContent())) {
//				if (isUserLoggedIn()) {
//					results = ResourceDAO.fetchTodo(getUser().getId(),
//							ResourceTodo.TODO, start, size);
//				}
//			} else if ("done".equalsIgnoreCase(criteria.getContent())
//					|| "xong".equalsIgnoreCase(criteria.getContent())) {
//				if (isUserLoggedIn()) {
//					results = ResourceDAO.fetchDone(getUser().getId(),
//							true, start, size);
//				}
//			}
//			return true;
//		}
//		return false;
//	}

	public String getQuery() {
		return query;
	}
	
	public long getCount() {
		return count;
	}

	public List<Resource<? extends Article>> getResults() {
		return results;
	}

}
