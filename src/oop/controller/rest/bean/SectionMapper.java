package oop.controller.rest.bean;

import oop.data.Section;

public class SectionMapper implements Mapper<SectionBean, Section> {

	@Override
	public SectionBean apply(Section value) {
		SectionBean bean = new SectionBean();
		bean.setId(value.getId());
		bean.setContent(value.getContent());
		MapperUtils.applyAll(bean.getQuestions(), value.getQuestions(),
				QuestionMapper.get());
		return bean;
	}

	@Override
	public Section get(SectionBean value) {
		Section entity = new Section();
		entity.setId(value.getId());
		entity.setContent(value.getContent());
		MapperUtils.getAll(value.getQuestions(), entity.getQuestions(),
				QuestionMapper.get());
		return entity;
	}

	private static SectionMapper DEFAULT_INSTANCE = new SectionMapper();

	public static SectionMapper get() {
		return DEFAULT_INSTANCE;
	}
	
}
