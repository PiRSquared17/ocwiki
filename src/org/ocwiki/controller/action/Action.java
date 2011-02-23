package org.ocwiki.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.ocwiki.conf.ActionDescriptor;
import org.ocwiki.controller.ActionController;
import org.ocwiki.controller.rest.bean.ResourceBean;
import org.ocwiki.data.Article;
import org.ocwiki.data.Resource;

public interface Action {
	
	public static Action NULL_ACTION = new AbstractAction() {
		
		@Override
		protected void performImpl() throws IOException, ServletException {
			// DO NOTHING
		}
	};
	
	public ActionDescriptor getDescriptor();
	
	public void setDescriptor(ActionDescriptor desc);

	public void perform() throws IOException, ServletException;

	public ActionController getController();

	public void setController(ActionController controller);

	public void setRequest(HttpServletRequest request);

	public HttpServletRequest getRequest();

	public String getNextAction();
	
	public String getRedirect();
	
	public Resource<? extends Article> getResource();
	
	public Article getArticle();

	public String getTitle();
	
	public void setTitle(String title);

	public abstract ResourceBean getResourceBean();

}