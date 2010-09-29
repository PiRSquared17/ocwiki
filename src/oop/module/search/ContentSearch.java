package oop.module.search;

import java.util.ArrayList;
import java.util.List;

import oop.data.BaseQuestion;
import oop.data.File;
import oop.data.Test;
import oop.data.TestStructure;
import oop.data.TextArticle;
import oop.data.Topic;

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

}
