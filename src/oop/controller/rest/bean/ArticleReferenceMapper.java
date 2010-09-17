package oop.controller.rest.bean;

import oop.data.Article;
import oop.persistence.HibernateUtil;

public class ArticleReferenceMapper implements Mapper<ArticleReferenceBean, Article> {

	@Override
	public ArticleReferenceBean apply(Article value) {
		ArticleReferenceBean bean = new ArticleReferenceBean();
		bean.setId(value.getId());
		bean.setName(value.getName());
		bean.setNamespace(value.getNamespace());
		return bean;
	}

	@Override
	public Article get(ArticleReferenceBean value) {
		if (value == null) {
			return null;
		}
		return HibernateUtil.load(Article.class, value.getId());
	}

	private static ArticleReferenceMapper DEFAULT_INSTANCE = new ArticleReferenceMapper();

	public static ArticleReferenceMapper get() {
		return DEFAULT_INSTANCE;
	}
	
}
