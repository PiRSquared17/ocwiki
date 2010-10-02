package oop.controller.action.solution;

import oop.controller.action.AbstractResourceAction;
import oop.controller.action.ActionException;
import oop.data.BaseQuestion;
import oop.db.dao.BaseQuestionDAO;

public class CreateSolution extends AbstractResourceAction<BaseQuestion> {

	private BaseQuestion basequestion;
	
	@Override
	protected void performImpl() throws Exception {
		resource = BaseQuestionDAO.fetchById(getParams().getLong("id"));
		if(resource==null){
			throw new ActionException("Không tìm thấy câu hỏi!");
		}
		basequestion = resource.getArticle();		
	}
	
	public BaseQuestion getQuestion(){
		return basequestion;
	}

}
