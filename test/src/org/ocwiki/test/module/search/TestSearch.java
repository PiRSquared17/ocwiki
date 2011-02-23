package org.ocwiki.test.module.search;

import junit.framework.Assert;
import org.ocwiki.data.BaseQuestion;
import org.ocwiki.module.search.ContentSearch;
import org.ocwiki.module.search.MockSearch;
import org.ocwiki.module.search.Search;
import org.ocwiki.module.search.Search.SearchElement;
import org.ocwiki.module.search.Search.SearchOperator;

import org.junit.After;
import org.junit.Test;
import org.junit.Before;

public class TestSearch {
	
	private Search searchInstance = null;
	
	@Before
	public void setUp(){
		searchInstance = new Search();
	}
	
	@After
	public void tearDown(){
		searchInstance = null;
	}
	
	@Test
	public void testBuildSearchQueryForClass(){
		SearchElement se = new SearchElement(null, new ContentSearch("test"));
		searchInstance.addSearchElement(se);
		searchInstance.addSearchElement( SearchOperator.AND, new MockSearch() );
		System.out.println(searchInstance.buildSearchQueryForClass(BaseQuestion.class));
		Assert.assertEquals("from org.ocwiki.data.BaseQuestion as basequestion where  ( basequestion.content like '%test%' ) and (  true  ) ", searchInstance.buildSearchQueryForClass(BaseQuestion.class));
	}
}
