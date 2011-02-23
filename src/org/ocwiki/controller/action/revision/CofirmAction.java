package org.ocwiki.controller.action.revision;

import org.ocwiki.controller.action.AbstractAction;
import org.ocwiki.data.Article;
import org.ocwiki.data.Revision;
import org.ocwiki.db.dao.RevisionDAO;

public class CofirmAction extends AbstractAction {
	private int revisionId;
	private Revision<? extends Article> currRevision;
	
	
	@Override
	protected void performImpl() {
		revisionId = getParams().getInt("revisionId");
		currRevision = RevisionDAO.fetch(revisionId);
	}
	
	public Revision<? extends Article> getCurrRevision(){
		return currRevision;
	}
	
	public int getRevisionId(){
		return revisionId;
	}
}
