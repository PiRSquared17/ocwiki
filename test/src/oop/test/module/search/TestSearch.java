package oop.test.module.search;

import junit.framework.Assert;
import oop.data.BaseQuestion;
import oop.module.search.ContentSearch;
import oop.module.search.MockSearch;
import oop.module.search.Search;
import oop.module.search.Search.SearchElement;
import oop.module.search.Search.SearchOperator;

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
		Assert.assertEquals("from oop.data.BaseQuestion as basequestion where  ( basequestion.content like '%test%' ) and (  true  ) ", searchInstance.buildSearchQueryForClass(BaseQuestion.class));
	}
}
