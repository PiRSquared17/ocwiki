package oop.controller.action.textart;

import oop.controller.action.AbstractResourceAction;
import oop.controller.action.ActionException;
import oop.data.TextArticle;
import oop.db.dao.TextArticleDAO;

public class ViewAction extends AbstractResourceAction<TextArticle> {

	private TextArticle textcontext;
	
	@Override
	protected void performImpl() throws Exception {
		try{
			resource = TextArticleDAO.fetchById(getParams().getLong("id"));
			if (resource == null){
				throw new ActionException("Không tìm thấy văn bản nào hợp lệ!!");
			}
			textcontext = resource.getArticle();
		}
		catch(NumberFormatException ex){
			throw new ActionException("Id không hợp lệ: " + getParams().getString("id"));
		}
	}
	
	public TextArticle getTextArtical(){
		return textcontext;
	}
	
}
