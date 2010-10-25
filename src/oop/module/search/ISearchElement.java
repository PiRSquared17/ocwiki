package oop.module.search;

import java.util.List;

public interface ISearchElement {
	List<Class> getSearchAbleClasses();

	void setParameter(String parameter);

	String getParameter();

	String getQueryElement(String classAlias);
}
