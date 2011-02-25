package org.ocwiki.controller.action.search;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractAction;
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

import org.apache.commons.lang.StringUtils;
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

import com.oreilly.servlet.ParameterNotFoundException;

public class SearchAction extends AbstractAction {

	private static final String[] FULLTEXT_FIELDS = { "article.name",
			"article.content.text", "question.choices", "test.sections",
			"test.questions", "test.choices" };
	private static final String[] AUTHOR_FIELDS = { "author.name",
			"author.email", "author.firtName", "author.lastName", "author.bio",
			"author.middleName" };
	private static final Pattern ID_PATTERN = Pattern.compile("#\\d+");

	private String query;
	private List<Resource<? extends Article>> results;
	private long count;
	private String status = Status.NORMAL.name();
	private int start;
	private int size;

	@SuppressWarnings("unchecked")
	@Override
	protected void performImpl() throws IOException, ServletException {
		try {
			query = getParams().getString("search_query");
			title("Tìm \"" + query + "\"");
			start = getParams().getInt("start", 0);
			size = getParams().getInt("size", 50);

			BooleanQuery luceneQuery = new BooleanQuery();
			QueryParser parser = new QueryParser(query);
			while (parser.hasNext()) {
				Criteria criteria = parser.next();
				Query subquery = toQuery(criteria);
				if (subquery != null) {
					luceneQuery.add(subquery, BooleanClause.Occur.MUST);
				}
			}
			luceneQuery.add(new TermQuery(new Term("status", status)),
					BooleanClause.Occur.MUST);
			FullTextSession fullTextSession = Search
					.getFullTextSession(HibernateUtil.getSession());
			FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(
					luceneQuery, Resource.class);
			fullTextQuery.setFirstResult(start);
			fullTextQuery.setMaxResults(size);
			results = fullTextQuery.list();
			count = fullTextQuery.getResultSize();
		} catch (ParameterNotFoundException e) {
			setNextAction("homepage");
		}
	}

	private Query toQuery(Criteria criteria) {
		switch (criteria.getTag()) {
		case NAME:
			return new TermQuery(
					new Term("article.name", criteria.getContent()));

		case TOPIC:
			return new TermQuery(new Term("article.topics.article.name",
					criteria.getContent()));

		case IS:
			if (isUserLoggedIn()) {
				String field = translateCustomization(criteria.getContent());
				return new TermQuery(new Term(field,
						String.valueOf(getUser().getId())));
			}
			return null;

		case STATUS:
			status = StringUtils.upperCase(criteria.getContent());
			return null;

		case AUTHOR:
			if (ID_PATTERN.matcher(criteria.getContent()).matches()) {
				String id = criteria.getContent().substring(1);
				return new TermQuery(new Term("author.id", id));
			} else {
				MultiFieldQueryParser authorParser = new MultiFieldQueryParser(
						Version.LUCENE_29, AUTHOR_FIELDS, new StandardAnalyzer(
								Version.LUCENE_29));
				try {
					return authorParser.parse(criteria.getContent());
				} catch (ParseException e) {
					e.printStackTrace();
					return null;
				}
			}

		case FULLTEXT:
			MultiFieldQueryParser fulltextParser = new MultiFieldQueryParser(
					Version.LUCENE_29, FULLTEXT_FIELDS, new StandardAnalyzer(
							Version.LUCENE_29));
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

	public String getQuery() {
		return query;
	}

	public long getCount() {
		return count;
	}

	public List<Resource<? extends Article>> getResults() {
		return results;
	}

	public int getStart() {
		return start;
	}
	
	public int getSize() {
		return size;
	}
	
}
