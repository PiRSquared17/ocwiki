package oop.controller.rest.bean;

import oop.data.Topic;

public class TopicMapper implements Mapper<TopicBean, Topic> {

	@Override
	public TopicBean apply(Topic value) {
		TopicBean bean = new TopicBean();
		bean.setId(value.getId());
		bean.setName(value.getName());
		bean.setNamespace(value.getNamespace());
		bean.setContent(value.getContent());
		ResourceReferenceMapper<Topic> topicMapper = ResourceReferenceMapper.get();
		bean.setParent(topicMapper.apply(value.getParent()));
		return bean;
	}

	@Override
	public Topic get(TopicBean value) {
		Topic entity = new Topic();
		entity.setId(value.getId());
		entity.setName(value.getName());
		entity.setNamespace(value.getNamespace());
		entity.setContent(value.getContent());
		ResourceReferenceMapper<Topic> topicMapper = ResourceReferenceMapper.get();
		entity.setParent(topicMapper.get(value.getParent()));
		return entity;
	}

	private static TopicMapper DEFAULT_INSTANCE = new TopicMapper();

	public static TopicMapper get() {
		return DEFAULT_INSTANCE;
	}

}
