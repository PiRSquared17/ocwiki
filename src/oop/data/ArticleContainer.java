package oop.data;

public interface ArticleContainer<T extends Article> {

	public T getArticle();

}