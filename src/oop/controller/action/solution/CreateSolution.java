package oop.controller.action.solution;

import oop.controller.action.AbstractAction;
import oop.controller.action.ActionException;
import oop.data.BaseQuestion;
import oop.db.dao.BaseQuestionDAO;

public class CreateSolution extends AbstractAction<BaseQuestion> {

	private BaseQuestion basequestion;
	
	@Override
	protected void performImpl() throws Exception {
		// TODO Auto-generated method stub
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
