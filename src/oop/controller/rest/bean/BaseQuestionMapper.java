package oop.controller.rest.bean;

import oop.data.BaseQuestion;
import oop.data.File;
import oop.data.Topic;

public class BaseQuestionMapper implements Mapper<BaseQuestionBean, BaseQuestion> {

	@Override
	public BaseQuestionBean toBean(BaseQuestion value) {
		BaseQuestionBean bean = new BaseQuestionBean();
		bean.setId(value.getId());
		bean.setName(value.getName());
		bean.setNamespace(value.getNamespace());
		bean.setContent(TextMapper.get().toBean(value.getContent()));
		bean.setLevel(value.getLevel());
		MapperUtils.toBeans(bean.getAnswers(), value.getAnswers(), AnswerMapper.get());
		ResourceReferenceMapper<Topic> topicMapper = ResourceReferenceMapper.get();
		MapperUtils.toBeans(bean.getTopics(), value.getTopics(), topicMapper);
		ResourceReferenceMapper<File> fileMapper = ResourceReferenceMapper.get();
		MapperUtils.toBeans(bean.getAttachments(), value.getAttachments(), fileMapper);
		MapperUtils.toBeans(bean.getEmbeds(), value.getEmbeds(), fileMapper);
		return bean;
	}

	@Override
	public BaseQuestion toEntity(BaseQuestionBean value) {
		BaseQuestion entity = new BaseQuestion();
		entity.setId(value.getId());
		entity.setName(value.getName());
		entity.setNamespace(value.getNamespace());
		entity.setContent(TextMapper.get().toEntity(value.getContent()));
		entity.setLevel(value.getLevel());
		MapperUtils.toEntities(value.getAnswers(), entity.getAnswers(), AnswerMapper.get());
		ResourceReferenceMapper<Topic> topicMapper = ResourceReferenceMapper.get();
		MapperUtils.toEntities(value.getTopics(), entity.getTopics(), topicMapper);
		ResourceReferenceMapper<File> fileMapper = ResourceReferenceMapper.get();
		MapperUtils.toEntities(value.getAttachments(), entity.getAttachments(), fileMapper);
		MapperUtils.toEntities(value.getEmbeds(), entity.getEmbeds(), fileMapper);
		return entity;
	}

	private static BaseQuestionMapper DEFAULT_INSTANCE = new BaseQuestionMapper();

	public static BaseQuestionMapper get() {
		return DEFAULT_INSTANCE;
	}
	
}
