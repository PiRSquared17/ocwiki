package org.ocwiki.controller.rest.bean;

import org.ocwiki.data.MultichoiceQuestion;
import org.ocwiki.data.File;
import org.ocwiki.data.Topic;

public class MultichoiceQuestionMapper implements Mapper<MultichoiceQuestionBean, MultichoiceQuestion> {

	@Override
	public MultichoiceQuestionBean toBean(MultichoiceQuestion value) {
		MultichoiceQuestionBean bean = new MultichoiceQuestionBean();
		bean.setId(value.getId());
		bean.setName(value.getName());
		bean.setNamespace(value.getNamespace());
		bean.setContent(TextMapper.get().toBean(value.getContent()));
		bean.setLevel(value.getLevel());
		MapperUtils.toBeans(bean.getChoices(), value.getChoices(), ChoiceMapper.get());
		ResourceReferenceMapper<Topic> topicMapper = ResourceReferenceMapper.get();
		MapperUtils.toBeans(bean.getTopics(), value.getTopics(), topicMapper);
		ResourceReferenceMapper<File> fileMapper = ResourceReferenceMapper.get();
		MapperUtils.toBeans(bean.getAttachments(), value.getAttachments(), fileMapper);
		MapperUtils.toBeans(bean.getEmbeds(), value.getEmbeds(), fileMapper);
		return bean;
	}

	@Override
	public MultichoiceQuestion toEntity(MultichoiceQuestionBean value) {
		MultichoiceQuestion entity = new MultichoiceQuestion();
		entity.setId(value.getId());
		entity.setName(value.getName());
		entity.setNamespace(value.getNamespace());
		entity.setContent(TextMapper.get().toEntity(value.getContent()));
		entity.setLevel(value.getLevel());
		MapperUtils.toEntities(value.getChoices(), entity.getChoices(), ChoiceMapper.get());
		ResourceReferenceMapper<Topic> topicMapper = ResourceReferenceMapper.get();
		MapperUtils.toEntities(value.getTopics(), entity.getTopics(), topicMapper);
		ResourceReferenceMapper<File> fileMapper = ResourceReferenceMapper.get();
		MapperUtils.toEntities(value.getAttachments(), entity.getAttachments(), fileMapper);
		MapperUtils.toEntities(value.getEmbeds(), entity.getEmbeds(), fileMapper);
		return entity;
	}

	private static MultichoiceQuestionMapper DEFAULT_INSTANCE = new MultichoiceQuestionMapper();

	public static MultichoiceQuestionMapper get() {
		return DEFAULT_INSTANCE;
	}
	
}
