package org.ocwiki.controller.rest.bean;

import org.ocwiki.data.MultichoiceQuestion;
import org.ocwiki.data.TestQuestion;
import org.ocwiki.persistence.HibernateUtil;

public class TestQuestionMapper implements Mapper<TestQuestionBean, TestQuestion> {

	@Override
	public TestQuestionBean toBean(TestQuestion value) {
		TestQuestionBean bean = new TestQuestionBean();
		bean.setId(value.getId());
		bean.setMark(value.getMark());
		bean.setBase(MultichoiceQuestionMapper.get().toBean(value.getBase()));
		ResourceReferenceMapper<MultichoiceQuestion> resourceMapper = ResourceReferenceMapper.get();
		bean.setBaseResource(resourceMapper.toBean(value.getBaseResource()));
		RevisionReferenceMapper<MultichoiceQuestion> revisionMapper = RevisionReferenceMapper.get();
		bean.setBaseRevision(revisionMapper.toBean(value.getBaseRevision()));
		return bean;
	}

	@Override
	public TestQuestion toEntity(TestQuestionBean value) {
		if (value.getId() == 0) {
			TestQuestion entity = new TestQuestion(); 
			entity.setId(value.getId());
			entity.setMark(value.getMark());
			ResourceReferenceMapper<MultichoiceQuestion> resourceMapper = ResourceReferenceMapper.get();
			entity.setBaseResource(resourceMapper.toEntity(value.getBaseResource()));
			RevisionReferenceMapper<MultichoiceQuestion> revisionMapper = RevisionReferenceMapper.get();
			entity.setBaseRevision(revisionMapper.toEntity(value.getBaseRevision()));
			return entity;
		}
		return (TestQuestion) HibernateUtil.getSession().load(TestQuestion.class, value.getId());
	}

	private static TestQuestionMapper DEFAULT_INSTANCE = new TestQuestionMapper();

	public static TestQuestionMapper get() {
		return DEFAULT_INSTANCE;
	}
	
}
