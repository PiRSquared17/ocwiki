package org.ocwiki.controller.action.search;

import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ocwiki.controller.action.search.Criteria.Tag;

public class QueryParser {

	private static final Pattern QUOTED_CRITERIA = Pattern.compile(
			"(?<=\\s|^)(?:\"?([^\\s\"]+?)\"?:)?\"(.+?)\"(?=\\s|$)");
	private static final Pattern UNQUOTED_CRITERIA = Pattern.compile(
			"(?<=\\s|^)(?:\"?([^\\s\"]+?)\"?:)?(\\S+)(?=\\s|$)");

	private String query;
	private SortedMap<Integer, Criteria> criterias = new TreeMap<Integer, Criteria>();
	private Iterator<Criteria> iterator;

	public QueryParser(String query) {
		super();
		this.query = query;
		parse();
		iterator = criterias.values().iterator();
	}

	private void parse() {
		StringBuffer buffer = new StringBuffer(query);
		findCriterias(buffer, QUOTED_CRITERIA);
		findCriterias(buffer, UNQUOTED_CRITERIA);
	}

	private void findCriterias(StringBuffer buffer, Pattern pattern) {
		Matcher matcher = pattern.matcher(buffer);
		while (matcher.find()) {
			Tag tag = findTag(matcher.group(1));
			if (tag != null) {
				Criteria criteria = new Criteria(tag, matcher.group(2));
				criterias.put(matcher.start(), criteria); // add to map
			}
			// remove in buffer
			for (int i = matcher.start(); i < matcher.end(); i++) {
				buffer.setCharAt(i, ' ');
			}
		}
	}

	private Tag findTag(String code) {
		if (code == null) {
			return Tag.FULLTEXT;
		}
		for (Tag tag : Tag.values()) {
			for (String tagCode : tag.getCodes()) {
				if (tagCode.equalsIgnoreCase(code)) {
					return tag;
				}
			}
		}
		return null;
	}

	public Criteria next() {
		return iterator.next();
	}
	
	public boolean hasNext() {
		return iterator.hasNext();
	}
	
	public int criteriaCount() {
		return criterias.size();
	}
	
	public String getQuery() {
		return query;
	}
	
}
