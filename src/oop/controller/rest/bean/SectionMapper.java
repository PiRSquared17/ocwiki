package oop.controller.rest.bean;

import oop.data.Section;

public class SectionMapper implements Mapper<SectionBean, Section> {

	@Override
	public SectionBean apply(Section value) {
		SectionBean bean = new SectionBean();
		bean.setId(value.getId());
		bean.setContent(value.getContent());
		return null;
	}

	@Override
	public Section get(SectionBean value) {
		// TODO Auto-generated method stub
		return null;
	}

	private static SectionMapper DEFAULT_INSTANCE = new SectionMapper();

	public static SectionMapper get() {
		return DEFAULT_INSTANCE;
	}
	
}
