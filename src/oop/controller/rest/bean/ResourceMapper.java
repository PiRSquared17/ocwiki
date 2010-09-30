package oop.controller.rest.bean;

import oop.data.Article;
import oop.data.Resource;

@SuppressWarnings("unchecked")
public class ResourceMapper implements
		Mapper<ResourceBean, Resource<Article>> {

	@Override
	public ResourceBean toBean(Resource<Article> value) {
		ResourceBean bean = new ResourceBean();
		bean.setId(value.getId());
		bean.setArticle(ArticleMapper.get().toBean(value.getArticle()));
		bean.setType(value.getType());
		bean.setAuthor(UserReferenceMapper.get().toBean(value.getAuthor()));
		bean.setCreateDate(value.getCreateDate());
		bean.setStatus(value.getStatus());
		bean.setAccessibility(value.getAccessibility());
		bean.setVersion(value.getVersion());
		bean.setLink(ResourceReferenceMapper.get().toBean(
				(Resource<Article>) value.getLink()));
		return bean;
	}

	@Override
	public Resource<Article> toEntity(ResourceBean value) {
		Resource<Article> entity = new Resource<Article>();
		entity.setId(value.getId());
		entity.setArticle(ArticleMapper.get().toEntity(value.getArticle()));
		entity.setType(value.getType());
		entity.setAuthor(UserReferenceMapper.get().toEntity(value.getAuthor()));
		entity.setCreateDate(value.getCreateDate());
		entity.setStatus(value.getStatus());
		entity.setAccessibility(value.getAccessibility());
		entity.setVersion(value.getVersion());
		entity.setLink(ResourceReferenceMapper.get().toEntity(value.getLink()));
		return entity;
	}

	private static ResourceMapper DEFAULT_INSTANCE = new ResourceMapper();

	public static ResourceMapper get() {
		return DEFAULT_INSTANCE;
	}
	


}
