package oop.controller.rest.bean;

import oop.data.Article;
import oop.data.Resource;

@SuppressWarnings("unchecked")
public class ResourceMapper implements
		Mapper<ResourceBean, Resource<Article>> {

	@Override
	public ResourceBean apply(Resource<Article> value) {
		ResourceBean bean = new ResourceBean();
		bean.setId(value.getId());
		bean.setArticle(ArticleMapper.get().apply(value.getArticle()));
		bean.setType(value.getType());
		bean.setAuthor(UserReferenceMapper.get().apply(value.getAuthor()));
		bean.setCreateDate(value.getCreateDate());
		bean.setStatus(value.getStatus());
		bean.setAccessibility(value.getAccessibility());
		bean.setVersion(value.getVersion());
		bean.setLink(ResourceReferenceMapper.get().apply(
				(Resource<Article>) value.getLink()));
		return bean;
	}

	@Override
	public Resource<Article> get(ResourceBean value) {
		Resource<Article> entity = new Resource<Article>();
		entity.setId(value.getId());
		entity.setArticle(ArticleMapper.get().get(value.getArticle()));
		entity.setType(value.getType());
		entity.setAuthor(UserReferenceMapper.get().get(value.getAuthor()));
		entity.setCreateDate(value.getCreateDate());
		entity.setStatus(value.getStatus());
		entity.setAccessibility(value.getAccessibility());
		entity.setVersion(value.getVersion());
		entity.setLink(ResourceReferenceMapper.get().get(value.getLink()));
		return entity;
	}

	private static ResourceMapper DEFAULT_INSTANCE = new ResourceMapper();

	public static ResourceMapper get() {
		return DEFAULT_INSTANCE;
	}
	


}
