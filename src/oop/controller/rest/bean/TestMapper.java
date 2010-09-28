package oop.controller.rest.bean;

import oop.data.File;
import oop.data.Test;
import oop.data.Topic;

public class TestMapper implements Mapper<TestBean, Test> {

	@Override
	public TestBean apply(Test value) {
		TestBean bean = new TestBean();
		bean.setId(value.getId());
		bean.setName(value.getName());
		bean.setNamespace(value.getNamespace());
		bean.setContent(value.getContent());
		ResourceReferenceMapper<Topic> topicMapper = ResourceReferenceMapper.get();
		MapperUtils.getAll(bean.getTopics(), value.getTopics(), topicMapper);
		ResourceReferenceMapper<File> fileMapper = ResourceReferenceMapper.get();
		MapperUtils.applyAll(bean.getAttachments(), value.getAttachments(), fileMapper);
		MapperUtils.applyAll(bean.getEmbeds(), value.getEmbeds(), fileMapper);
		bean.setType(value.getType());
		bean.setTime(value.getTime());
		return bean;
	}

	@Override
	public Test get(TestBean value) {
		// TODO Auto-generated method stub
		return null;
	}

}
