package oop.controller.action.TextArticle;

import oop.controller.action.AbstractAction;
import oop.controller.action.ActionException;
import oop.data.Article;
import oop.data.Resource;
import oop.data.Text;
import oop.data.TextArticle;
import oop.db.dao.TextArticalDAO;

public class EditAction extends AbstractAction {

	private Resource<TextArticle> resource;
	
	@Override
	protected void performImpl() throws Exception {
		// TODO Auto-generated method stub
		resource = TextArticalDAO.fetchById(getParams().getLong("id"));
		if(resource == null){
			throw new ActionException("Khong tim thay Text" + getParams().getLong("id"));
		}
		if (getParams().hasParameter("save")){
			doEdit();
		}
	}
	private void doEdit()throws Exception{
		TextArticle text = resource.getArticle().copy();
		String context = getParams().get("content");
		if (!text.getContent().getText().equals(context)){
			text.setContent(new Text(context));
		}
		saveNewRevision(resource, text);
		setNextAction("TextArticle.view&id="+ resource.getId());
	}
	public Resource<TextArticle> get(){
		return resource;
	}
	public Article getArticle(){
		return resource.getArticle();
	}
}
