package oop.controller.action.revision;

import oop.controller.action.AbstractAction;
import oop.data.Article;
import oop.data.Revision;
import oop.db.dao.RevisionDAO;

public class RestoreAction extends AbstractAction {
	private int revisionId;
	private Revision<Article> oldRevision;
	private String sumary;
	private String minor;
	
	@Override
	protected void performImpl() {
		// TODO Auto-generated method stub
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
