package oop.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import oop.conf.ActionDescriptor;
import oop.controller.ActionController;
import oop.controller.rest.bean.ResourceBean;
import oop.data.Article;
import oop.data.Resource;

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