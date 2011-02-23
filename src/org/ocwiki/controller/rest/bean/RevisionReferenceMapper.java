package org.ocwiki.controller.rest.bean;

import org.ocwiki.data.Article;
import org.ocwiki.data.Revision;
import org.ocwiki.persistence.HibernateUtil;

@SuppressWarnings("unchecked")
public class RevisionReferenceMapper<T extends Article> implements
		Mapper<RevisionReferenceBean, Revision<T>> {

	@Override
	public RevisionReferenceBean toBean(Revision value) {
		if (value == null) {
			return null;
		}
		RevisionReferenceBean bean = new RevisionReferenceBean();
		bean.setId(value.getId());
		return bean;
	}

	@Override
	public Revision<T> toEntity(RevisionReferenceBean value) {
		if (value == null || value.getId() <= 0) {
			return null;
		}
		return HibernateUtil.load(Revision.class, value.getId());
	}

	private static RevisionReferenceMapper DEFAULT_INSTANCE = new RevisionReferenceMapper();

	public static <K extends Article> RevisionReferenceMapper<K> get() {
		return DEFAULT_INSTANCE;
	}

}
