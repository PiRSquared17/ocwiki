package org.ocwiki.test;

import org.ocwiki.controller.action.search.Criteria;
import org.ocwiki.controller.action.search.QueryParser;
import org.ocwiki.controller.action.search.Criteria.Tag;

import org.junit.Assert;
import org.junit.Test;

public class QueryParserTest {

	@Test
	public void simpleQuotedFultext() {
		QueryParser parser = new QueryParser("\"old man\"");
		Criteria criteria = parser.next();
		Assert.assertEquals("old man", criteria.getContent());
		Assert.assertEquals(Tag.FULLTEXT, criteria.getTag());
		Assert.assertEquals(false, parser.hasNext());
	}
	
	@Test
	public void simpleUnicodeQuotedFultext() {
		QueryParser parser = new QueryParser("\"Việt Nam\"");
		Criteria criteria = parser.next();
		Assert.assertEquals("Việt Nam", criteria.getContent());
		Assert.assertEquals(Tag.FULLTEXT, criteria.getTag());
		Assert.assertEquals(false, parser.hasNext());
	}
	
	@Test
	public void simpleQuotedTag() {
		QueryParser parser = new QueryParser("topic:\"man\"");
		Criteria criteria = parser.next();
		Assert.assertEquals(Tag.TOPIC, criteria.getTag());
		Assert.assertEquals("man", criteria.getContent());
		Assert.assertEquals(false, parser.hasNext());
	}
	
	@Test
	public void simpleUnicodeQuotedTag() {
		QueryParser parser = new QueryParser("\"chủ-đề\":\"Toán học\"");
		Criteria criteria = parser.next();
		Assert.assertEquals(Tag.TOPIC, criteria.getTag());
		Assert.assertEquals("Toán học", criteria.getContent());
		Assert.assertEquals(false, parser.hasNext());
	}
	
	@Test
	public void simpleUnquotedFulltext() {
		QueryParser parser = new QueryParser("old");
		Criteria criteria = parser.next();
		Assert.assertEquals("old", criteria.getContent());
		Assert.assertEquals(Tag.FULLTEXT, criteria.getTag());
		Assert.assertEquals(false, parser.hasNext());
	}
	
	@Test
	public void multiCriteriaUnquotedFulltext() {
		QueryParser parser = new QueryParser(" old    man");
		Criteria criteria = parser.next();
		Assert.assertEquals("old", criteria.getContent());
		Assert.assertEquals(Tag.FULLTEXT, criteria.getTag());
		
		criteria = parser.next();
		Assert.assertEquals("man", criteria.getContent());
		Assert.assertEquals(Tag.FULLTEXT, criteria.getTag());
		
		Assert.assertEquals(false, parser.hasNext());
	}
	
	@Test
	public void multiCriteriaUnicodeUnquotedFulltext() {
		QueryParser parser = new QueryParser("  tiếng    Việt  ");
		Criteria criteria = parser.next();
		Assert.assertEquals("tiếng", criteria.getContent());
		Assert.assertEquals(Tag.FULLTEXT, criteria.getTag());
		
		criteria = parser.next();
		Assert.assertEquals("Việt", criteria.getContent());
		Assert.assertEquals(Tag.FULLTEXT, criteria.getTag());
		
		Assert.assertEquals(false, parser.hasNext());
	}
	
	@Test
	public void multiCriteriaUnicodeQuotedTag() {
		QueryParser parser = new QueryParser("\"chủ-đề\":\"Thiên văn học\" is:liked");
		Criteria criteria = parser.next();
		Assert.assertEquals(Tag.TOPIC, criteria.getTag());
		Assert.assertEquals("Thiên văn học", criteria.getContent());

		criteria = parser.next();
		Assert.assertEquals(Tag.IS, criteria.getTag());
		Assert.assertEquals("liked", criteria.getContent());
		
		Assert.assertEquals(false, parser.hasNext());
	}
	
}
