package oop.controller.action.revision;

import oop.controller.action.AbstractAction;
import oop.data.Article;
import oop.data.Revision;
import oop.db.dao.RevisionDAO;

public class CofirmAction extends AbstractAction {
	private int revisionId;
	private Revision<? extends Article> currRevision;
	
	
	@Override
	protected void performImpl() {
		// TODO Auto-generated method stub
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
