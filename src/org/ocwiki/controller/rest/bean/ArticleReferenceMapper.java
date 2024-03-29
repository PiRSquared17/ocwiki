package org.ocwiki.controller.rest.bean;

import org.ocwiki.data.Article;
import org.ocwiki.persistence.HibernateUtil;

public class ArticleReferenceMapper implements Mapper<ArticleReferenceBean, Article> {

	@Override
	public ArticleReferenceBean toBean(Article value) {
		ArticleReferenceBean bean = new ArticleReferenceBean();
		bean.setId(value.getId());
		bean.setName(value.getName());
		bean.setNamespace(value.getNamespace());
		return bean;
	}

	@Override
	public Article toEntity(ArticleReferenceBean value) {
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
