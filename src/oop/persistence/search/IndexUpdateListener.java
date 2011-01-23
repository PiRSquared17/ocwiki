package oop.persistence.search;

import oop.data.ResourceCustomization;

import org.hibernate.Session;
import org.hibernate.event.PostCollectionRecreateEvent;
import org.hibernate.event.PostCollectionRecreateEventListener;
import org.hibernate.event.PostCollectionRemoveEvent;
import org.hibernate.event.PostCollectionRemoveEventListener;
import org.hibernate.event.PostCollectionUpdateEvent;
import org.hibernate.event.PostCollectionUpdateEventListener;
import org.hibernate.event.PostDeleteEvent;
import org.hibernate.event.PostDeleteEventListener;
import org.hibernate.event.PostInsertEvent;
import org.hibernate.event.PostInsertEventListener;
import org.hibernate.event.PostUpdateEvent;
import org.hibernate.event.PostUpdateEventListener;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;

public class IndexUpdateListener implements PostUpdateEventListener,
		PostInsertEventListener, PostDeleteEventListener,
		PostCollectionUpdateEventListener, PostCollectionRecreateEventListener,
		PostCollectionRemoveEventListener {

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
			fullTextSession.index(customization.getResource());
		}
	}

	@Override
	public void onPostUpdateCollection(PostCollectionUpdateEvent arg0) { }

	@Override
	public void onPostRemoveCollection(PostCollectionRemoveEvent arg0) { }

	@Override
	public void onPostRecreateCollection(PostCollectionRecreateEvent arg0) { }

}
