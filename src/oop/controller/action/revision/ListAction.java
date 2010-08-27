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
	public static final int PAGE_LENGTH = 50;
	private List<Revision<Article>> revList;
	private long resourceID;

	@Override
	protected void performImpl() throws Exception {
		// TODO Auto-generated method stub
		int page = getParams().getInt("page", 1);
		resourceID = getParams().getLong("resID");
		Resource<Article> res = ResourceDAO.fetchById(resourceID);
		revList = RevisionDAO.fetchByResource(resourceID, (page - 1) * PAGE_LENGTH, PAGE_LENGTH);
		title("Danh sách phiên bản thuộc tài nguyên: " + res.getName());
	}

	public List<Revision<Article>> getRevisions() {
		return revList;
	}

	public int getPage() {
		return getParams().getInt("page", 1);
	}

	public long getPageCount() {
		return UtilFunctions.ceil(RevisionDAO.countByResource(resourceID) / PAGE_LENGTH);
	}
}
