package org.ocwiki.controller.rest.bean;

import org.ocwiki.data.ResourceReport;

public class ResourceReportMapper implements
		Mapper<ResourceReportBean, ResourceReport> {

	@Override
	public ResourceReportBean toBean(ResourceReport value) {
		ResourceReportBean bean = new ResourceReportBean();
		bean.setResource(ResourceReferenceMapper.get().toBean(value.getResource()));
		bean.setUser(UserReferenceMapper.get().toBean(value.getUser()));
		bean.setLevel(value.getLevel());
		bean.setLike(value.getLike());
		bean.setTodo(value.getTodo());
		bean.setLikeCount(value.getLikeCount());
		bean.setAverageLevel(value.getAverageLevel());
		return bean;
	}

	@Override
	public ResourceReport toEntity(ResourceReportBean value) {
		ResourceReport entity = new ResourceReport();
		entity.setResource(ResourceReferenceMapper.get().toEntity(value.getResource()));
		entity.setUser(UserReferenceMapper.get().toEntity(value.getUser()));
		entity.setLevel(value.getLevel());
		entity.setLike(value.getLike());
		entity.setTodo(value.getTodo());
		entity.setLikeCount(value.getLikeCount());
		entity.setAverageLevel(value.getAverageLevel());
		return entity;
	}
	
	private static ResourceReportMapper DEFAULT_INSTANCE = new ResourceReportMapper();

	public static ResourceReportMapper get() {
		return DEFAULT_INSTANCE;
	}
	
}
