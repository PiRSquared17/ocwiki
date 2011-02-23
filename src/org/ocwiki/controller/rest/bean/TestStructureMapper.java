package org.ocwiki.controller.rest.bean;

import org.ocwiki.data.File;
import org.ocwiki.data.TestStructure;
import org.ocwiki.data.Topic;

public class TestStructureMapper implements
		Mapper<TestStructureBean, TestStructure> {

	@Override
	public TestStructureBean toBean(TestStructure value) {
		TestStructureBean bean = new TestStructureBean();
		bean.setId(value.getId());
		bean.setName(value.getName());
		bean.setNamespace(value.getNamespace());
		bean.setContent(TextMapper.get().toBean(value.getContent()));
		bean.setTime(value.getTime());
		bean.setType(value.getType());
		ResourceReferenceMapper<Topic> topicMapper = ResourceReferenceMapper.get();
		MapperUtils.toBeans(bean.getTopics(), value.getTopics(), topicMapper);
		ResourceReferenceMapper<File> fileMapper = ResourceReferenceMapper.get();
		MapperUtils.toBeans(bean.getAttachments(), value.getAttachments(), fileMapper);
		MapperUtils.toBeans(bean.getEmbeds(), value.getEmbeds(), fileMapper);
		return bean;
	}

	@Override
	public TestStructure toEntity(TestStructureBean value) {
		TestStructure entity = new TestStructure();
		entity.setId(value.getId());
		entity.setName(value.getName());
		entity.setNamespace(value.getNamespace());
		entity.setContent(TextMapper.get().toEntity(value.getContent()));
		entity.setType(value.getType());
		entity.setTime(value.getTime());
		ResourceReferenceMapper<Topic> topicMapper = ResourceReferenceMapper.get();
		MapperUtils.toEntities(value.getTopics(), entity.getTopics(), topicMapper);
		ResourceReferenceMapper<File> fileMapper = ResourceReferenceMapper.get();
		MapperUtils.toEntities(value.getAttachments(), entity.getAttachments(), fileMapper);
		MapperUtils.toEntities(value.getEmbeds(), entity.getEmbeds(), fileMapper);
		return entity;
	}

	private static TestStructureMapper DEFAULT_INSTANCE = new TestStructureMapper();

	public static TestStructureMapper get() {
		return DEFAULT_INSTANCE;
	}
	

	
}
