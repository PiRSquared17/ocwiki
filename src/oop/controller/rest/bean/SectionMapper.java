package oop.controller.rest.bean;

import oop.data.Section;
import oop.persistence.HibernateUtil;

public class SectionMapper implements Mapper<SectionBean, Section> {

	@Override
	public SectionBean toBean(Section value) {
		SectionBean bean = new SectionBean();
		bean.setId(value.getId());
		bean.setContent(TextMapper.get().toBean(value.getContent()));
		MapperUtils.toBeans(bean.getQuestions(), value.getQuestions(),
				QuestionMapper.get());
		return bean;
	}

	@Override
	public Section toEntity(SectionBean value) {
		if (value.getId() == 0) {
			Section entity = new Section();
			entity.setId(value.getId());
			entity.setContent(TextMapper.get().toEntity(value.getContent()));
			MapperUtils.toEntities(value.getQuestions(), entity.getQuestions(),
					QuestionMapper.get());
			return entity;
		}
		return (Section) HibernateUtil.getSession().load(Section.class,
				value.getId());
	}

	private static SectionMapper DEFAULT_INSTANCE = new SectionMapper();

	public static SectionMapper get() {
		return DEFAULT_INSTANCE;
	}
	
}
