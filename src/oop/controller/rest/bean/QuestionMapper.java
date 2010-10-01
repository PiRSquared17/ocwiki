package oop.controller.rest.bean;

import oop.data.BaseQuestion;
import oop.data.Question;

public class QuestionMapper implements Mapper<QuestionBean, Question> {

	@Override
	public QuestionBean toBean(Question value) {
		QuestionBean bean = new QuestionBean();
		bean.setId(value.getId());
		bean.setMark(value.getMark());
		ResourceReferenceMapper<BaseQuestion> resourceMapper = ResourceReferenceMapper.get();
		bean.setBaseResource(resourceMapper.toBean(value.getBaseResource()));
		RevisionReferenceMapper<BaseQuestion> revisionMapper = RevisionReferenceMapper.get();
		bean.setBaseRevision(revisionMapper.toBean(value.getBaseRevision()));
		return bean;
	}

	@Override
	public Question toEntity(QuestionBean value) {
		Question entity = new Question(); 
		entity.setId(value.getId());
		entity.setMark(value.getMark());
		ResourceReferenceMapper<BaseQuestion> resourceMapper = ResourceReferenceMapper.get();
		entity.setBaseResource(resourceMapper.toEntity(value.getBaseResource()));
		RevisionReferenceMapper<BaseQuestion> revisionMapper = RevisionReferenceMapper.get();
		entity.setBaseRevision(revisionMapper.toEntity(value.getBaseRevision()));
		return entity;
	}

	private static QuestionMapper DEFAULT_INSTANCE = new QuestionMapper();

	public static QuestionMapper get() {
		return DEFAULT_INSTANCE;
	}
	
}
