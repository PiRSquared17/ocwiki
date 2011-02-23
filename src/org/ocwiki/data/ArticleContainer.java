package org.ocwiki.data;

public interface ArticleContainer<T extends Article> {

	public T getArticle();

}