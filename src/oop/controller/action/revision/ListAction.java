package oop.controller.action.revision;

import java.util.List;

import oop.controller.action.AbstractAction;
import oop.data.Article;
import oop.data.Resource;
import oop.data.Revision;
import oop.db.dao.ResourceDAO;
import oop.db.dao.RevisionDAO;
import oop.taglib.UtilFunctions;

public class ListAction extends AbstractAction{
	public static final int PAGE_LENGTH = 50;

	@Override
	protected void performImpl() throws Exception {
		// TODO Auto-generated method stub
	}
	
	public List<Revision<Article>> getRevisions(){
		int page = getParams().getInt("page", 1);
		String resourceID = getParams().get("resID");
		Resource<Article> res = ResourceDAO.fetchById(Long.parseLong(resourceID));
		List<Revision<Article>> revList = RevisionDAO.fetchByResource(Long.parseLong(resourceID),(page-1)*PAGE_LENGTH, PAGE_LENGTH);
		title("Danh sách phiên bản thuộc tài nguyên: " + res.getName());
		return revList;
	}
	
	public int getPage(){
		return getParams().getInt("page", 1);
	}
	
	public long getPageCount(){
		return UtilFunctions.ceil(getRevisions().size() / PAGE_LENGTH);
	}
}
