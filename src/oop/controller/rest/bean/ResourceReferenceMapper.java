package oop.controller.rest.bean;

import oop.data.Article;
import oop.data.Resource;
import oop.persistence.HibernateUtil;

@SuppressWarnings("unchecked")
public class ResourceReferenceMapper<T extends Article> implements
		Mapper<ResourceReferenceBean, Resource<T>> {

	@Override
	public ResourceReferenceBean apply(Resource value) {
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
	public Resource<T> get(ResourceReferenceBean value) {
		if (value == null) {
			return null;
		}
		return HibernateUtil.load(Resource.class, value.getId());
	}

	private static ResourceReferenceMapper DEFAULT_INSTANCE = new ResourceReferenceMapper();

	public static <K extends Article> ResourceReferenceMapper<K> get() {
		return DEFAULT_INSTANCE;
	}


}
