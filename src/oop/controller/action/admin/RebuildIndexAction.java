package oop.controller.action.admin;

import java.io.IOException;

import javax.servlet.ServletException;

import oop.controller.action.AbstractAction;
import oop.persistence.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;

public class RebuildIndexAction extends AbstractAction {

	@Override
	protected void performImpl() throws IOException, ServletException {
	}

	/**
	 * Call it only once!!!
	 * @return
	 */
	public String getRun() {
		try {
			Session session = HibernateUtil.getSession();
			FullTextSession fullTextSession = Search
					.getFullTextSession(session);
			fullTextSession.createIndexer().startAndWait();
			return ("Done.");
		} catch (InterruptedException ex) {
			return ("Failed.");
		} finally {
			HibernateUtil.closeSession();
		}
	}

}
