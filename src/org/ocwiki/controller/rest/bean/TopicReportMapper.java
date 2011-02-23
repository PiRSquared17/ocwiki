package org.ocwiki.controller.rest.bean;

import org.ocwiki.data.TopicReport;

public class TopicReportMapper implements Mapper<TopicReportBean, TopicReport> {

	@Override
	public TopicReportBean toBean(TopicReport value) {
		TopicReportBean bean = new TopicReportBean();
		bean.setResource(ResourceReferenceMapper.get().toBean(value.getResource()));
		bean.setArticleCount(value.getArticleCount());
		bean.setChildrenCount(value.getChildrenCount());
		return bean;
	}

	@Override
	public TopicReport toEntity(TopicReportBean value) {
		throw new UnsupportedOperationException("readonly entity");
	}

	private static TopicReportMapper DEFAULT_INSTANCE = new TopicReportMapper();

	public static TopicReportMapper get() {
		return DEFAULT_INSTANCE;
	}
	
}
