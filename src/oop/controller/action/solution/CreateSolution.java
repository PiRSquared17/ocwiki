package oop.controller.action.solution;

import oop.controller.action.AbstractAction;
import oop.controller.action.ActionException;
import oop.data.BaseQuestion;
import oop.data.Resource;
import oop.db.dao.BaseQuestionDAO;

public class CreateSolution extends AbstractAction {

	Resource<BaseQuestion> resource;
	BaseQuestion basequestion;
	
	@Override
	protected void performImpl() throws Exception {
		// TODO Auto-generated method stub
		resource = BaseQuestionDAO.fetchById(getParams().getLong("id"));
		if(resource==null){
			throw new ActionException("Không tìm thấy câu hỏi!");
		}
		basequestion = resource.getArticle();		
	}
	
	public Resource<BaseQuestion> getResource(){
		return resource;
	}
	
	public BaseQuestion getQuestion(){
		return basequestion;
	}

}
