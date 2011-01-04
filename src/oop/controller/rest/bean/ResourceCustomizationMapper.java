package oop.controller.rest.bean;

import oop.data.ResourceCustomization;

public class ResourceCustomizationMapper implements
		Mapper<ResourceCustomizationBean, ResourceCustomization> {

	@Override
	public ResourceCustomizationBean toBean(ResourceCustomization value) {
		ResourceCustomizationBean bean = new ResourceCustomizationBean();
		bean.setResource(ResourceReferenceMapper.get().toBean(value.getResource()));
		bean.setUser(UserReferenceMapper.get().toBean(value.getUser()));
		bean.setLevel(value.getLevel());
		bean.setLike(value.getLike());
		bean.setTodo(value.getTodo());
		bean.setDone(value.isDone());
		return bean;
	}

	@Override
	public ResourceCustomization toEntity(ResourceCustomizationBean value) {
		ResourceCustomization entity = new ResourceCustomization();
		entity.setResource(ResourceReferenceMapper.get().toEntity(value.getResource()));
		entity.setUser(UserReferenceMapper.get().toEntity(value.getUser()));
		entity.setLevel(value.getLevel());
		entity.setLike(value.getLike());
		entity.setTodo(value.getTodo());
		entity.setDone(value.isDone());
		return entity;
	}

	private static ResourceCustomizationMapper DEFAULT_INSTANCE = new ResourceCustomizationMapper();

	public static ResourceCustomizationMapper get() {
		return DEFAULT_INSTANCE;
	}
	
}
