package oop.controller.rest.bean;

import oop.data.BaseQuestion;
import oop.data.Question;

public class QuestionMapper implements Mapper<QuestionBean, Question> {

	@Override
	public QuestionBean apply(Question value) {
		QuestionBean bean = new QuestionBean();
		bean.setId(value.getId());
		bean.setMark(value.getMark());
		ResourceReferenceMapper<BaseQuestion> resourceMapper = ResourceReferenceMapper.get();
		bean.setBaseResource(resourceMapper.apply(value.getBaseResource()));
		RevisionReferenceMapper<BaseQuestion> revisionMapper = RevisionReferenceMapper.get();
		bean.setBaseRevision(revisionMapper.apply(value.getBaseRevision()));
		return bean;
	}

	@Override
	public Question get(QuestionBean value) {
		Question entity = new Question(); 
		entity.setId(value.getId());
		entity.setMark(value.getMark());
		ResourceReferenceMapper<BaseQuestion> resourceMapper = ResourceReferenceMapper.get();
		entity.setBaseResource(resourceMapper.get(value.getBaseResource()));
		RevisionReferenceMapper<BaseQuestion> revisionMapper = RevisionReferenceMapper.get();
		entity.setBaseRevision(revisionMapper.get(value.getBaseRevision()));
		return entity;
	}

	private static QuestionMapper DEFAULT_INSTANCE = new QuestionMapper();

	public static QuestionMapper get() {
		return DEFAULT_INSTANCE;
	}
	
}
