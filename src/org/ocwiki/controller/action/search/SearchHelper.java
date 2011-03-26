package org.ocwiki.controller.action.search;

import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.util.Version;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.ocwiki.data.Article;
import org.ocwiki.data.MultichoiceQuestion;
import org.ocwiki.data.Resource;
import org.ocwiki.data.Solution;
import org.ocwiki.data.Status;
import org.ocwiki.data.Test;
import org.ocwiki.data.TestStructure;
import org.ocwiki.data.TextArticle;
import org.ocwiki.data.Topic;
import org.ocwiki.persistence.HibernateUtil;
import org.ocwiki.util.SessionUtils;

public class SearchHelper {
	private static final Pattern ID_PATTERN = Pattern.compile("#\\d+");
	private static final String[] AUTHOR_FIELDS = { "author.name",
			"author.email", "author.firtName", "author.lastName", "author.bio",
			"author.middleName" };
	private static final String[] FULLTEXT_FIELDS = { "article.name",
			"article.content.text", "question.choices", "test.sections",
			"test.questions", "test.choices" };

	private String query;
	private List<Resource<? extends Article>> results;
	private Status status = Status.NORMAL;
	private int start;
	private int size;

	public SearchHelper() {
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public List<Resource<? extends Article>> getResults() {
		return results;
	}

	public void setResults(List<Resource<? extends Article>> results) {
		this.results = results;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<Resource<? extends Article>> search(HttpServletRequest request) {
		BooleanQuery luceneQuery = new BooleanQuery();
		QueryParser parser = new QueryParser(getQuery());
		while (parser.hasNext()) {
			Criteria criteria = parser.next();
			Query subquery = toQuery(request, criteria);
			if (subquery != null) {
				luceneQuery.add(subquery, Occur.MUST);
			}
		}
		luceneQuery.add(new TermQuery(new Term("status", status.name())),
				Occur.MUST);
		FullTextSession fullTextSession = Search
				.getFullTextSession(HibernateUtil.getSession());
		FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(
				luceneQuery, Resource.class);
		fullTextQuery.setFirstResult(getStart());
		fullTextQuery.setMaxResults(getSize());
		results = fullTextQuery.list();
		return results;
	}

	private Query toQuery(HttpServletRequest request, Criteria criteria) {
		switch (criteria.getTag()) {
		case NAME:
			return new TermQuery(
					new Term("article.name", criteria.getContent()));

		case TOPIC:
			return new TermQuery(new Term("article.topics.article.name",
					criteria.getContent()));

		case IS:
			if (SessionUtils.isLoggedIn(request.getSession())) {
				String field = translateCustomization(criteria.getContent());
				return new TermQuery(new Term(field,
						String.valueOf(SessionUtils.getUser(
								request.getSession()).getId())));
			}
			return null;

		case STATUS:
			String statusStr = StringUtils.upperCase(criteria.getContent());
			try {
				setStatus(Status.valueOf(statusStr));
			} catch (IllegalArgumentException e) {
				System.out.println("Wrong status: " + statusStr);
			}
			return null;
		case AUTHOR:
			if (SearchHelper.ID_PATTERN.matcher(criteria.getContent())
					.matches()) {
				String id = criteria.getContent().substring(1);
				return new TermQuery(new Term("author.id", id));
			} else {
				MultiFieldQueryParser authorParser = new MultiFieldQueryParser(
						Version.LUCENE_29, SearchHelper.AUTHOR_FIELDS,
						new StandardAnalyzer(Version.LUCENE_29));
				try {
					return authorParser.parse(criteria.getContent());
				} catch (ParseException e) {
					e.printStackTrace();
					return null;
				}
			}

		case FULLTEXT:
			MultiFieldQueryParser fulltextParser = new MultiFieldQueryParser(
					Version.LUCENE_29, SearchHelper.FULLTEXT_FIELDS,
					new StandardAnalyzer(Version.LUCENE_29));
			try {
				return fulltextParser.parse(criteria.getContent());
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}

		case TYPE:
			String typeName = translateArticleTypeName(criteria.getContent());
			if (typeName == null) {
				return null;
			}
			typeName = typeName.toLowerCase();
			return new TermQuery(new Term("articleType", typeName));
		}
		return null;
	}

	private String translateCustomization(String str) {
		if ("liked".equalsIgnoreCase(str) || "thích".equalsIgnoreCase(str)) {
			return "customization.likes";
		} else if ("todo".equalsIgnoreCase(str)
				|| "cần-làm".equalsIgnoreCase(str)) {
			return "customization.todos";
		} else if ("done".equalsIgnoreCase(str) || "xong".equalsIgnoreCase(str)) {
			return "customization.dones";
		} else if ("hard".equalsIgnoreCase(str) || "khó".equalsIgnoreCase(str)) {
			return "customization.hards";
		} else if ("easy".equalsIgnoreCase(str) || "dễ".equalsIgnoreCase(str)) {
			return "customization.easys";
		}
		return null;
	}

	private String translateArticleTypeName(String str) {
		if ("question".equals(str)) {
			return MultichoiceQuestion.class.getSimpleName();
		} else if ("test".equals(str)) {
			return Test.class.getSimpleName();
		} else if ("text".equals(str)) {
			return TextArticle.class.getSimpleName();
		} else if ("topic".equals(str)) {
			return Topic.class.getSimpleName();
		} else if ("structure".equals(str)) {
			return TestStructure.class.getSimpleName();
		} else if ("solution".equals(str)) {
			return Solution.class.getSimpleName();
		}
		return null;
	}
}