package oop.controller.rest.bean;

import oop.data.Article;
import oop.data.Resource;
import oop.data.ResourceSearchReport;

public class ResourceSearchReportMapper implements
		Mapper<ResourceSearchReportBean, ResourceSearchReport<? extends Article>> {

	@Override
	public ResourceSearchReportBean apply(ResourceSearchReport<? extends Article> value) {
		ResourceSearchReportBean bean = new ResourceSearchReportBean();
		bean.setResource(ResourceReferenceMapper.get().apply(
				(Resource<Article>) value.getResource()));
		bean.setScore(value.getScore());
		return bean;
	}

	@Override
	public ResourceSearchReport<Article> get(ResourceSearchReportBean value) {
		ResourceSearchReport<Article> entity = new ResourceSearchReport<Article>();
		entity.setResource(ResourceReferenceMapper.get().get(
				value.getResource()));
		entity.setScore(value.getScore());
		return entity;
	}

	private static ResourceSearchReportMapper DEFAULT_INSTANCE = new ResourceSearchReportMapper();

	public static ResourceSearchReportMapper get() {
		return DEFAULT_INSTANCE;
	}

}
