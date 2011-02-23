package org.ocwiki.controller.action.revision;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractListAction;
import org.ocwiki.data.Article;
import org.ocwiki.data.Resource;
import org.ocwiki.data.Revision;
import org.ocwiki.db.dao.ResourceDAO;
import org.ocwiki.db.dao.RevisionDAO;

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
