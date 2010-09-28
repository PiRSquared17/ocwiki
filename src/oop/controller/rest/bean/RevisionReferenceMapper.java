package oop.controller.rest.bean;

import oop.data.Article;
import oop.data.Revision;
import oop.persistence.HibernateUtil;

@SuppressWarnings("unchecked")
public class RevisionReferenceMapper<T extends Article> implements
		Mapper<RevisionReferenceBean, Revision<T>> {

	@Override
	public RevisionReferenceBean apply(Revision value) {
		RevisionReferenceBean bean = new RevisionReferenceBean();
		bean.setId(value.getId());
		return bean;
	}

	@Override
	public Revision<T> get(RevisionReferenceBean value) {
		return HibernateUtil.load(Revision.class, value.getId());
	}

	private static RevisionReferenceMapper DEFAULT_INSTANCE = new RevisionReferenceMapper();

	public static <K extends Article> RevisionReferenceMapper<K> get() {
		return DEFAULT_INSTANCE;
	}

}
