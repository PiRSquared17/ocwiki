package org.ocwiki.controller.action.revision;

import org.ocwiki.controller.action.AbstractAction;
import org.ocwiki.data.Article;
import org.ocwiki.data.Revision;
import org.ocwiki.db.dao.RevisionDAO;

public class RestoreAction extends AbstractAction {
	private int revisionId;
	private Revision<Article> oldRevision;
	private String sumary;
	private String minor;
	
	@Override
	protected void performImpl() {
		revisionId = getParams().getInt("revisionId");
		oldRevision = RevisionDAO.fetch(revisionId);
		if (getParams().hasParameter("cofirm")){
			sumary = getParams().get("sumary");
			minor = getParams().get("minor");
			//Revision<? extends Article> newRevision = new Revision<Article>(id, resource, article, author, timestamp, summary, minor)
			saveNewRevision(oldRevision.getResource(), oldRevision.getArticle());
			setNextAction("revision.list?resourceID=" + oldRevision.getResource().getId());
		}

	}
	
	public Revision<Article> getOldRevision(){
		return oldRevision;
	}

}
