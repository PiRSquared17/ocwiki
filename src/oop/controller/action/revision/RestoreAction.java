package oop.controller.action.revision;

import oop.controller.action.AbstractAction;
import oop.data.Article;
import oop.data.Revision;
import oop.db.dao.RevisionDAO;

public class RestoreAction extends AbstractAction {
	private int revisionId;
	private Revision<Article> currRevision;
	private String sumary;
	private String minor;
	
	@Override
	protected void performImpl() throws Exception {
		// TODO Auto-generated method stub
		revisionId = getParams().getInt("revisionId");
		currRevision = RevisionDAO.fetch(revisionId);
		if (!getParams().hasParameter("cofirm"))
			setNextAction("revision.cofirm&revisionId=" + revisionId);
		else{
			sumary = getParams().get("sumary");
			minor = getParams().get("minor");
			//Revision<? extends Article> newRevision = new Revision<Article>(id, resource, article, author, timestamp, summary, minor)
			saveNewRevision(currRevision.getResource(), currRevision.getArticle());
			setNextAction("revision.list");
		}

	}
	
	public Revision<? extends Article> getRevision(){
		return currRevision;
	}

}
