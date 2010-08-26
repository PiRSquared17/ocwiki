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
		int page = getParams().getInt("page", 1);
		String resourceID = getParams().get("resID");
		if( !resourceID.equals("")){
			Resource<Article> res = ResourceDAO.fetchById(Long.parseLong(resourceID));
			List<Revision<Article>> revList = RevisionDAO.fetchByResource(Long.parseLong(resourceID),(page-1)*PAGE_LENGTH, PAGE_LENGTH);
			title("Danh sách phiên bản thuộc tài nguyên: " + res.getName());
			
			// tạm thời để ntn đã, chuyển sang properties sau
			request.setAttribute("revisions", revList);
			request.setAttribute("page", page);
			
		}
	}
	
}
