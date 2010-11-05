package oop.controller.action.solution;

import java.io.IOException;

import javax.servlet.ServletException;

import oop.controller.action.AbstractAction;
import oop.controller.action.ActionException;
import oop.data.Resource;
import oop.data.Solution;
import oop.db.dao.ResourceDAO;

public class ViewAction extends AbstractAction {

	Resource<Solution> resource;
	Solution solution;
	
	@Override
	protected void performImpl() throws IOException, ServletException {
		// TODO Auto-generated method stub
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
