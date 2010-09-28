package oop.controller.rest.bean;

import oop.data.File;
import oop.data.TestStructure;
import oop.data.Topic;

public class TestStructureMapper implements
		Mapper<TestStructureBean, TestStructure> {

	@Override
	public TestStructureBean apply(TestStructure value) {
		TestStructureBean bean = new TestStructureBean();
		bean.setId(value.getId());
		bean.setName(value.getName());
		bean.setNamespace(value.getNamespace());
		bean.setContent(value.getContent());
		bean.setTime(value.getTime());
		bean.setType(value.getType());
		ResourceReferenceMapper<Topic> topicMapper = ResourceReferenceMapper.get();
		MapperUtils.applyAll(bean.getTopics(), value.getTopics(), topicMapper);
		ResourceReferenceMapper<File> fileMapper = ResourceReferenceMapper.get();
		MapperUtils.applyAll(bean.getAttachments(), value.getAttachments(), fileMapper);
		MapperUtils.applyAll(bean.getEmbeds(), value.getEmbeds(), fileMapper);
		return bean;
	}

	@Override
	public TestStructure get(TestStructureBean value) {
		TestStructure entity = new TestStructure();
		entity.setId(value.getId());
		entity.setName(value.getName());
		entity.setNamespace(value.getNamespace());
		entity.setContent(value.getContent());
		entity.setType(value.getType());
		entity.setTime(value.getTime());
		ResourceReferenceMapper<Topic> topicMapper = ResourceReferenceMapper.get();
		MapperUtils.getAll(value.getTopics(), entity.getTopics(), topicMapper);
		ResourceReferenceMapper<File> fileMapper = ResourceReferenceMapper.get();
		MapperUtils.getAll(value.getAttachments(), entity.getAttachments(), fileMapper);
		MapperUtils.getAll(value.getEmbeds(), entity.getEmbeds(), fileMapper);
		return entity;
	}

	private static TestStructureMapper DEFAULT_INSTANCE = new TestStructureMapper();

	public static TestStructureMapper get() {
		return DEFAULT_INSTANCE;
	}
	

	
}
