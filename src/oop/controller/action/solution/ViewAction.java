package oop.controller.action.solution;

import oop.controller.action.AbstractAction;
import oop.controller.action.ActionException;
import oop.data.Resource;
import oop.data.TextArticle;
import oop.db.dao.TextArticleDAO;

public class ViewAction extends AbstractAction {

	Resource<TextArticle> resource;
	TextArticle textarticle;
	
	@Override
	protected void performImpl() throws Exception {
		// TODO Auto-generated method stub
		resource = TextArticleDAO.fetchById(getParams().getLong("id"));
		if(resource==null){
			throw new ActionException("Không tìm thấy câu hỏi!");
		}
		textarticle = resource.getArticle();
	}
	
	public Resource<TextArticle> getResource(){
		return resource;
	}
	
	public TextArticle getQuestion(){
		return textarticle;
	}


}
