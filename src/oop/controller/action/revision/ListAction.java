package oop.controller.action.revision;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import oop.controller.action.AbstractListAction;
import oop.data.Article;
import oop.data.Resource;
import oop.data.Revision;
import oop.db.dao.ResourceDAO;
import oop.db.dao.RevisionDAO;

public class ListAction extends AbstractListAction<Revision<Article>> {
	private long resourceId;
	private Revision<? extends Article> earliestRevision;
	private Revision<? extends Article> latestRevision;

	@Override
	protected void performImpl() throws IOException, ServletException {
		resourceId = getParams().getLong("r");
		Resource<Article> res = ResourceDAO.fetchById(resourceId);
		earliestRevision = RevisionDAO.fetchEarliestByResource(resourceId);
		latestRevision = RevisionDAO.fetchLatestByResource(resourceId);
		title("Danh sách phiên bản của bài viết: " + res.getName());
	}

	@Override
	protected long getCountImpl() {
		return RevisionDAO.countByResource(resourceId);
	}
	
	@Override
	protected List<Revision<Article>> getItemsImpl() {
		return RevisionDAO.fetchByResource(resourceId, getStart(), getSize());
	}
	
	public List<Revision<Article>> getRevisions() {
		return getItems();
	}
	
	public Revision<? extends Article> getEarliestRevision() {
		return earliestRevision;
	}
	
	public Revision<? extends Article> getLatestRevision() {
		return latestRevision;
	}
	
	public long getResourceId(){
		return resourceId;
	}

}
