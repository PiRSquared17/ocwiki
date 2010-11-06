package oop.module;

import oop.data.Article;
import oop.data.Resource;

public interface Module {

	void init() throws Exception;

	String getTitle();

	void setTitle(String title);

	String getPage();

	void setPage(String page);

	void setOrder(int position);

	int getOrder();

	Resource<? extends Article> getResource();
	
	<T extends Article> void setResource(Resource<T> resource);
	
	Article getArticle();
}
