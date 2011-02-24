package org.ocwiki.controller.rest.bean;

import org.ocwiki.data.MultichoiceQuestion;
import org.ocwiki.data.File;
import org.ocwiki.data.Solution;
import org.ocwiki.data.Topic;

public class SolutionMapper implements Mapper<SolutionBean, Solution> {

	@Override
	public SolutionBean toBean(Solution value) {
		SolutionBean bean = new SolutionBean();
		bean.setId(value.getId());
		bean.setName(value.getName());
		bean.setNamespace(value.getNamespace());
		bean.setContent(TextMapper.get().toBean(value.getContent()));
		ResourceReferenceMapper<Topic> topicMapper = ResourceReferenceMapper.get();
		MapperUtils.toBeans(bean.getTopics(), value.getTopics(), topicMapper);
		ResourceReferenceMapper<File> fileMapper = ResourceReferenceMapper.get();
		MapperUtils.toBeans(bean.getAttachments(), value.getAttachments(), fileMapper);
		MapperUtils.toBeans(bean.getEmbeds(), value.getEmbeds(), fileMapper);
		ResourceReferenceMapper<MultichoiceQuestion> questionMapper = ResourceReferenceMapper.get();
		bean.setQuestion(questionMapper.toBean(value.getQuestion()));
		return bean;
	}

	@Override
	public Solution toEntity(SolutionBean value) {
		Solution entity = new Solution();
		entity.setId(value.getId());
		entity.setName(value.getName());
		entity.setNamespace(value.getNamespace());
		entity.setContent(TextMapper.get().toEntity(value.getContent()));
		ResourceReferenceMapper<Topic> topicMapper = ResourceReferenceMapper.get();
		MapperUtils.toEntities(value.getTopics(), entity.getTopics(), topicMapper);
		ResourceReferenceMapper<File> fileMapper = ResourceReferenceMapper.get();
		MapperUtils.toEntities(value.getAttachments(), entity.getAttachments(), fileMapper);
		MapperUtils.toEntities(value.getEmbeds(), entity.getEmbeds(), fileMapper);
		ResourceReferenceMapper<MultichoiceQuestion> questionMapper = ResourceReferenceMapper.get();
		entity.setQuestion(questionMapper.toEntity(value.getQuestion()));
		return entity;
	}
	
	private static SolutionMapper DEFAULT_INSTANCE = new SolutionMapper();

	public static SolutionMapper get() {
		return DEFAULT_INSTANCE;
	}

}
