package oop.controller.rest.bean;

import oop.data.File;
import oop.data.TextArticle;
import oop.data.Topic;

public class TextArticleMapper implements Mapper<TextArticleBean, TextArticle> {

	@Override
	public TextArticleBean toBean(TextArticle value) {
		TextArticleBean bean = new TextArticleBean();
		bean.setId(value.getId());
		bean.setName(value.getName());
		bean.setNamespace(value.getNamespace());
		bean.setContent(value.getContent());
		ResourceReferenceMapper<Topic> topicMapper = ResourceReferenceMapper.get();
		MapperUtils.toBeans(bean.getTopics(), value.getTopics(), topicMapper);
		ResourceReferenceMapper<File> fileMapper = ResourceReferenceMapper.get();
		MapperUtils.toBeans(bean.getAttachments(), value.getAttachments(), fileMapper);
		MapperUtils.toBeans(bean.getEmbeds(), value.getEmbeds(), fileMapper);
		return bean;
	}

	@Override
	public TextArticle toEntity(TextArticleBean value) {
		TextArticle entity = new TextArticle();
		entity.setId(value.getId());
		entity.setName(value.getName());
		entity.setNamespace(value.getNamespace());
		entity.setContent(value.getContent());
		ResourceReferenceMapper<Topic> topicMapper = ResourceReferenceMapper.get();
		MapperUtils.toEntities(value.getTopics(), entity.getTopics(), topicMapper);
		ResourceReferenceMapper<File> fileMapper = ResourceReferenceMapper.get();
		MapperUtils.toEntities(value.getAttachments(), entity.getAttachments(), fileMapper);
		MapperUtils.toEntities(value.getEmbeds(), entity.getEmbeds(), fileMapper);
		return entity;
	}

	private static TextArticleMapper DEFAULT_INSTANCE = new TextArticleMapper();

	public static TextArticleMapper get() {
		return DEFAULT_INSTANCE;
	}
	

	
}
