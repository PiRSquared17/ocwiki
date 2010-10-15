package oop.controller.rest.bean;

import oop.data.File;
import oop.data.Test;
import oop.data.Topic;

public class TestMapper implements Mapper<TestBean, Test> {

	@Override
	public TestBean toBean(Test value) {
		TestBean bean = new TestBean();
		bean.setId(value.getId());
		bean.setName(value.getName());
		bean.setNamespace(value.getNamespace());
		bean.setContent(value.getContent());
		bean.setType(value.getType());
		bean.setTime(value.getTime());
		MapperUtils.toBeans(bean.getSections(), value.getSections(), SectionMapper.get());
		ResourceReferenceMapper<Topic> topicMapper = ResourceReferenceMapper.get();
		MapperUtils.toBeans(bean.getTopics(), value.getTopics(), topicMapper);
		ResourceReferenceMapper<File> fileMapper = ResourceReferenceMapper.get();
		MapperUtils.toBeans(bean.getAttachments(), value.getAttachments(), fileMapper);
		MapperUtils.toBeans(bean.getEmbeds(), value.getEmbeds(), fileMapper);
		return bean;
	}

	@Override
	public Test toEntity(TestBean value) {
		Test entity = new Test();
		entity.setId(value.getId());
		entity.setName(value.getName());
		entity.setNamespace(value.getNamespace());
		entity.setContent(value.getContent());
		entity.setType(value.getType());
		entity.setTime(value.getTime());
		MapperUtils.toEntities(value.getSections(), entity.getSections(), SectionMapper.get());
		ResourceReferenceMapper<Topic> topicMapper = ResourceReferenceMapper.get();
		MapperUtils.toEntities(value.getTopics(), entity.getTopics(), topicMapper);
		ResourceReferenceMapper<File> fileMapper = ResourceReferenceMapper.get();
		MapperUtils.toEntities(value.getAttachments(), entity.getAttachments(), fileMapper);
		MapperUtils.toEntities(value.getEmbeds(), entity.getEmbeds(), fileMapper);
		return entity;
	}

	private static TestMapper DEFAULT_INSTANCE = new TestMapper();

	public static TestMapper get() {
		return DEFAULT_INSTANCE;
	}
	
}
