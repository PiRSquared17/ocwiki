package oop.controller.action.TextArticle;

import oop.controller.action.AbstractAction;
import oop.controller.action.ActionException;
import oop.data.Resource;
import oop.data.TextArticle;
import oop.db.dao.TextArticalDAO;

public class ViewAction extends AbstractAction {
	private Resource<TextArticle> resource;
	private TextArticle textcontext;
	
	@Override
	protected void performImpl() throws Exception {
		try{
			resource = TextArticalDAO.fetchById(getParams().getLong("id"));
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
	
	public Resource<TextArticle> getResource(){
		return resource;
	}
}
