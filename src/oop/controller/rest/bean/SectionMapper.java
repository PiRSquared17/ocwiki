package oop.controller.rest.bean;

import oop.data.Section;

public class SectionMapper implements Mapper<SectionBean, Section> {

	@Override
	public SectionBean toBean(Section value) {
		SectionBean bean = new SectionBean();
		bean.setId(value.getId());
		bean.setContent(value.getContent());
		MapperUtils.toBeans(bean.getQuestions(), value.getQuestions(),
				QuestionMapper.get());
		return bean;
	}

	@Override
	public Section toEntity(SectionBean value) {
		Section entity = new Section();
		entity.setId(value.getId());
		entity.setContent(value.getContent());
		MapperUtils.toEntities(value.getQuestions(), entity.getQuestions(),
				QuestionMapper.get());
		return entity;
	}

	private static SectionMapper DEFAULT_INSTANCE = new SectionMapper();

	public static SectionMapper get() {
		return DEFAULT_INSTANCE;
	}
	
}
