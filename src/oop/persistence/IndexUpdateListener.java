package oop.persistence;

import oop.data.ResourceCustomization;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.event.PostDeleteEvent;
import org.hibernate.event.PostDeleteEventListener;
import org.hibernate.event.PostInsertEvent;
import org.hibernate.event.PostInsertEventListener;
import org.hibernate.event.PostUpdateEvent;
import org.hibernate.event.PostUpdateEventListener;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;

public class IndexUpdateListener implements PostUpdateEventListener,
		PostInsertEventListener, PostDeleteEventListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void onPostInsert(PostInsertEvent event) {
		updateIndex(event.getEntity(), event.getSession());
	}

	@Override
	public void onPostUpdate(PostUpdateEvent event) {
		updateIndex(event.getEntity(), event.getSession());
	}

	@Override
	public void onPostDelete(PostDeleteEvent event) {
		updateIndex(event.getEntity(), event.getSession());
	}

	private void updateIndex(Object entity, Session session) {
		if (entity instanceof ResourceCustomization) {
			ResourceCustomization customization = (ResourceCustomization) entity;
			FullTextSession fullTextSession = Search
					.getFullTextSession(session);
			Transaction tx = fullTextSession.beginTransaction();
			fullTextSession.index(customization.getResource());
			tx.commit();
		}
	}

}
