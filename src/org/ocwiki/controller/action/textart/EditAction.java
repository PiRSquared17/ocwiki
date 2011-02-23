package org.ocwiki.controller.action.textart;

import java.io.IOException;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractResourceAction;
import org.ocwiki.controller.action.ActionException;
import org.ocwiki.data.Article;
import org.ocwiki.data.Resource;
import org.ocwiki.data.Text;
import org.ocwiki.data.TextArticle;
import org.ocwiki.db.dao.TextArticleDAO;

public class EditAction extends AbstractResourceAction<TextArticle> {

	@Override
	protected void performImpl() throws IOException, ServletException {
		resource = TextArticleDAO.fetchById(getParams().getLong("id"));
		if(resource == null){
			throw new ActionException("Khong tim thay Text" + getParams().getLong("id"));
		}
		if (getParams().hasParameter("save")){
			doEdit();
		}
	}
	private void doEdit() {
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
