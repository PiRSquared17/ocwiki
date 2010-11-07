package oop.controller.rest.bean;

import oop.data.Topic;

public class TopicMapper implements Mapper<TopicBean, Topic> {

	@Override
	public TopicBean toBean(Topic value) {
		TopicBean bean = new TopicBean();
		bean.setId(value.getId());
		bean.setName(value.getName());
		bean.setNamespace(value.getNamespace());
		bean.setContent(TextMapper.get().toBean(value.getContent()));
		ResourceReferenceMapper<Topic> topicMapper = ResourceReferenceMapper.get();
		bean.setParent(topicMapper.toBean(value.getParent()));
		return bean;
	}

	@Override
	public Topic toEntity(TopicBean value) {
		Topic entity = new Topic();
		entity.setId(value.getId());
		entity.setName(value.getName());
		entity.setNamespace(value.getNamespace());
		entity.setContent(TextMapper.get().toEntity(value.getContent()));
		ResourceReferenceMapper<Topic> topicMapper = ResourceReferenceMapper.get();
		entity.setParent(topicMapper.toEntity(value.getParent()));
		return entity;
	}

	private static TopicMapper DEFAULT_INSTANCE = new TopicMapper();

	public static TopicMapper get() {
		return DEFAULT_INSTANCE;
	}

}
