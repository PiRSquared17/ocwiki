package org.ocwiki.controller.rest.bean;

import org.ocwiki.data.Choice;
import org.ocwiki.persistence.HibernateUtil;

public class ChoiceMapper implements Mapper<ChoiceBean, Choice> {

	@Override
	public ChoiceBean toBean(Choice value) {
		ChoiceBean bean = new ChoiceBean();
		bean.setId(value.getId());
		bean.setContent(TextMapper.get().toBean(value.getContent()));
		bean.setCorrect(value.isCorrect());
		return bean;
	}

	@Override
	public Choice toEntity(ChoiceBean value) {
		if (value.getId() == 0) {
			Choice entity = new Choice();
			entity.setContent(TextMapper.get().toEntity(value.getContent()));
			entity.setCorrect(value.isCorrect());
			return entity;
		}
		return (Choice) HibernateUtil.getSession().load(Choice.class,
				value.getId());
	}

	private static ChoiceMapper DEFAULT_INSTANCE = new ChoiceMapper();

	public static ChoiceMapper get() {
		return DEFAULT_INSTANCE;
	}
	
}
