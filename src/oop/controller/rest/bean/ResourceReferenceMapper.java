package oop.controller.rest.bean;

import oop.data.Article;
import oop.data.Resource;
import oop.persistence.HibernateUtil;

public class ResourceReferenceMapper implements
		Mapper<ResourceReferenceBean, Resource<? extends Article>> {

	@Override
	public ResourceReferenceBean apply(Resource<? extends Article> value) {
		if (value == null) {
			return null;
		}
		ResourceReferenceBean bean = new ResourceReferenceBean();
		bean.setId(value.getId());
		bean.setName(value.getName());
		bean.setNamespace(value.getNamespace());
		return bean;
	}

	@Override
	public Resource<? extends Article> get(ResourceReferenceBean value) {
		if (value == null) {
			return null;
		}
		return HibernateUtil.load(Resource.class, value.getId());
	}

	private static ResourceReferenceMapper DEFAULT_INSTANCE = new ResourceReferenceMapper();

	public static ResourceReferenceMapper get() {
		return DEFAULT_INSTANCE;
	}


}
