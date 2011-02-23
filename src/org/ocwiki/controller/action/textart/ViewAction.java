package org.ocwiki.controller.action.textart;

import java.io.IOException;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractResourceAction;
import org.ocwiki.controller.action.ActionException;
import org.ocwiki.data.TextArticle;
import org.ocwiki.db.dao.TextArticleDAO;

public class ViewAction extends AbstractResourceAction<TextArticle> {

	private TextArticle textcontext;
	
	@Override
	protected void performImpl() throws IOException, ServletException {
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
