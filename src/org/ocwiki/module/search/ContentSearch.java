package org.ocwiki.module.search;

import java.util.ArrayList;
import java.util.List;

import org.ocwiki.data.BaseQuestion;
import org.ocwiki.data.File;
import org.ocwiki.data.Test;
import org.ocwiki.data.TestStructure;
import org.ocwiki.data.TextArticle;
import org.ocwiki.data.Topic;

public class ContentSearch implements ISearchElement {

	private String parameter;
	private static ArrayList<Class> searchAbleClass;
	static {
		searchAbleClass = new ArrayList<Class>();
		searchAbleClass.add(Topic.class);
		searchAbleClass.add(File.class);
		searchAbleClass.add(BaseQuestion.class);
		searchAbleClass.add(Test.class);
		searchAbleClass.add(TestStructure.class);
		searchAbleClass.add(TextArticle.class);
	}

	@Override
	public String getParameter() {
		return this.parameter;
	}

	@Override
	public String getQueryElement(String classAlias) {
		return classAlias + ".content like '%" + parameter + "%'";
	}

	@Override
	public List<Class> getSearchAbleClasses() {
		return searchAbleClass;
	}

	@Override
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public ContentSearch(String parameter) {
		super();
		this.parameter = parameter;
	}
	
	

}
