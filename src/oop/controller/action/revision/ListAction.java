package oop.controller.action.revision;

import java.util.List;
import oop.controller.action.AbstractAction;
import oop.data.Article;
import oop.data.Resource;
import oop.data.Revision;
import oop.db.dao.ResourceDAO;
import oop.db.dao.RevisionDAO;
import oop.taglib.UtilFunctions;

public class ListAction extends AbstractAction {
	private List<Revision<? extends Article>> revList;
	private long resourceID;
	private long pageCount;
	private int pageLength;
	private int page;
	private Revision<? extends Article> earliestRevision;
	private Revision<? extends Article> latestRevision;

	@Override
	protected void performImpl() throws Exception {
		pageLength = getParams().getInt("size",10);
		page = getParams().getInt("page", 1);
		resourceID = getParams().getLong("resourceID");
		pageCount = UtilFunctions.ceil(RevisionDAO.countByResource(resourceID)
				/ (double)pageLength);
		
		Resource<Article> res = ResourceDAO.fetchById(resourceID);
		revList = RevisionDAO.fetchByResource(resourceID, (page - 1)
				* pageLength, pageLength);
		earliestRevision = RevisionDAO.fetchEarliestByResource(resourceID);
		latestRevision = RevisionDAO.fetchLatestByResource(resourceID);
		title("Danh sách phiên bản của bài viết: " + res.getName());
	}

	public List<Revision<? extends Article>> getRevisions() {
		return revList;
	}
	
	public Revision<? extends Article> getEarliestRevision() {
		return earliestRevision;
	}
	
	public Revision<? extends Article> getLatestRevision() {
		return latestRevision;
	}
	
	public long getResourceID(){
		return resourceID;
	}

	public int getPage() {
		return page;
	}
	
	public int getSize(){
		return pageLength;
	}

	public long getPageCount(){
		return pageCount;
	}
}
