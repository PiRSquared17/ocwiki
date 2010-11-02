package oop.controller.action;

import javax.servlet.http.HttpServletRequest;

import oop.conf.ActionDescriptor;
import oop.controller.ActionController;
import oop.data.Article;
import oop.data.Resource;

public interface Action {
	
	public static Action NULL_ACTION = new AbstractAction() {
		
		@Override
		protected void performImpl() throws Exception {
			// DO NOTHING
		}
	};
	
	public ActionDescriptor getDescriptor();
	
	public void setDescriptor(ActionDescriptor desc);

	public void perform() throws Exception;

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

}