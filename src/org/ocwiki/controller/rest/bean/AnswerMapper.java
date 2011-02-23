package org.ocwiki.controller.rest.bean;

import org.ocwiki.data.Answer;
import org.ocwiki.persistence.HibernateUtil;

public class AnswerMapper implements Mapper<AnswerBean, Answer> {

	@Override
	public AnswerBean toBean(Answer value) {
		AnswerBean bean = new AnswerBean();
		bean.setId(value.getId());
		bean.setContent(TextMapper.get().toBean(value.getContent()));
		bean.setCorrect(value.isCorrect());
		return bean;
	}

	@Override
	public Answer toEntity(AnswerBean value) {
		if (value.getId() == 0) {
			Answer entity = new Answer();
			entity.setContent(TextMapper.get().toEntity(value.getContent()));
			entity.setCorrect(value.isCorrect());
			return entity;
		}
		return (Answer) HibernateUtil.getSession().load(Answer.class,
				value.getId());
	}

	private static AnswerMapper DEFAULT_INSTANCE = new AnswerMapper();

	public static AnswerMapper get() {
		return DEFAULT_INSTANCE;
	}
	
}
