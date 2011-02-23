package org.ocwiki.controller.action.solution;

import java.io.IOException;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractAction;
import org.ocwiki.controller.action.ActionException;
import org.ocwiki.data.Resource;
import org.ocwiki.data.Solution;
import org.ocwiki.db.dao.ResourceDAO;

public class ViewAction extends AbstractAction {

	Resource<Solution> resource;
	Solution solution;
	
	@Override
	protected void performImpl() throws IOException, ServletException {
		resource = ResourceDAO.fetchById(getParams().getLong("id"));
		if(resource==null){
			throw new ActionException("Không tìm thấy câu hỏi!");
		}
		solution = resource.getArticle();
	}
	
	public Resource<Solution> getResource(){
		return resource;
	}
	
	public Solution getSolution(){
		return solution;
	}


}
