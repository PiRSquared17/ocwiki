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
	private List<Revision<Article>> revList;
	private long resourceID;
	private long pageCount;

	@Override
	protected void performImpl() throws Exception {
		// TODO Auto-generated method stub
		int pageLength = getParams().getInt("size",50);
		int page = getParams().getInt("page", 1);
		resourceID = getParams().getLong("resourceID");
		pageCount = UtilFunctions.ceil(RevisionDAO.countByResource(resourceID)
				/ (double)pageLength);
		
		Resource<Article> res = ResourceDAO.fetchById(resourceID);
		revList = RevisionDAO.fetchByResource(resourceID, (page - 1)
				* pageLength, pageLength);
		title("Danh sách phiên bản thuộc tài nguyên: " + res.getName());
	}

	public List<Revision<Article>> getRevisions() {
		return revList;
	}

	public int getPage() {
		return getParams().getInt("page", 1);
	}

	public long getPageCount(){
		return pageCount;
	}
}
