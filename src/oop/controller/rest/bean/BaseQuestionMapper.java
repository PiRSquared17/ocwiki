package oop.controller.rest.bean;

import oop.data.BaseQuestion;
import oop.data.Topic;

public class BaseQuestionMapper implements Mapper<BaseQuestionBean, BaseQuestion> {

	@Override
	public BaseQuestionBean apply(BaseQuestion value) {
		BaseQuestionBean bean = new BaseQuestionBean();
		bean.setId(value.getId());
		bean.setName(value.getName());
		bean.setNamespace(value.getNamespace());
		bean.setContent(value.getContent());
		bean.setLevel(value.getLevel());
		bean.setAnswers(value.getAnswers());
		ResourceReferenceMapper<Topic> topicMapper = ResourceReferenceMapper.get();
		MapperUtils.getAll(bean.getTopics(), value.getTopics(), topicMapper);
		return bean;
	}

	@Override
	public BaseQuestion get(BaseQuestionBean value) {
		BaseQuestion entity = new BaseQuestion();
		entity.setId(value.getId());
		entity.setName(value.getName());
		entity.setNamespace(value.getNamespace());
		entity.setContent(value.getContent());
		entity.setLevel(value.getLevel());
		entity.setAnswers(value.getAnswers());
		
		return entity;
	}

	private static BaseQuestionMapper DEFAULT_INSTANCE = new BaseQuestionMapper();

	public static BaseQuestionMapper get() {
		return DEFAULT_INSTANCE;
	}
	
}
