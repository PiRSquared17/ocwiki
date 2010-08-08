package oop.controller.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import oop.change.Change;
import oop.change.ChangeDelegate;
import oop.conf.ActionDescriptor;
import oop.conf.Config;
import oop.controller.ActionController;
import oop.data.Article;
import oop.data.User;
import oop.db.dao.ChangeDAO;
import oop.util.SessionUtils;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

import com.oreilly.servlet.ParameterList;
import com.oreilly.servlet.ParameterParser;

public abstract class AbstractAction implements Action {

	private ActionController controller;
	
	/**
	 * <p>Chứa yêu cầu được gửi đến server, hạn chế sử dụng trực tiếp đối tượng 
	 * này để tăng tính rõ ràng, dễ đọc và tăng khả năng kiểm thử.</p> 
	 * <p>Khi nhận tham số đầu vào, nên sử dụng các hàm tiện ích như 
	 * getParams(), getUser(), getSession(). Khi trả về giá trị, nên định
	 * nghĩa thuộc tính mới của lớp (thuộc tính sẽ được truy cập bởi các
	 * trang JSP.</p>
	 */
	@Deprecated
	protected HttpServletRequest request;
	private String nextAction = null;
	private String redirect = null;
	private ParameterList params;
	private ActionDescriptor descriptor;

	public AbstractAction() {
		// default constructor
	}
	
	/* (non-Javadoc)
	 * @see oop.controller.action.Action#perform()
	 */
	public void perform() throws Exception {
		performImpl(); 
	}
	
	protected boolean isUserLoggedIn() {
		return (Boolean) (ObjectUtils.defaultIfNull(request.getSession()
				.getAttribute("login"), Boolean.FALSE));
	}

	protected User getUser() {
		return SessionUtils.getUser(getSession());
	}

	protected HttpSession getSession() {
		return request.getSession();
	}

	/* (non-Javadoc)
	 * @see oop.controller.action.Action#getController()
	 */
	public ActionController getController() {
		return controller;
	}

	/* (non-Javadoc)
	 * @see oop.controller.action.Action#setController(oop.controller.ControllerServlet)
	 */
	public void setController(ActionController controller) {
		this.controller = controller;
	}

	/* (non-Javadoc)
	 * @see oop.controller.action.Action#setRequest(javax.servlet.http.HttpServletRequest)
	 */
	public void setRequest(HttpServletRequest request) {
		this.request = request;
		params = new ParameterParser(request);
	}

	/* (non-Javadoc)
	 * @see oop.controller.action.Action#getRequest()
	 */
	public HttpServletRequest getRequest() {
		return request;
	}

	protected ParameterList getParams() {
		return params;
	}

	protected abstract void performImpl() throws Exception;

	protected void title(String pageTitle) {
		request.setAttribute("pageTitle", pageTitle + " - "
				+ Config.get().getSiteName());
	}

	protected void setNextAction(String nextAction) {
		this.nextAction = nextAction;
	}

	/* (non-Javadoc)
	 * @see oop.controller.action.Action#getNextAction()
	 */
	public String getNextAction() {
		return nextAction;
	}

	public void error(String msg) {
		setNextAction("error?message=" + msg);
	}

	protected void message(String msg) {
		request.setAttribute("message", msg);
	}

	public String getPreviousQuery() {
		return StringUtils.defaultIfEmpty((String) request.getSession()
				.getAttribute("previousQuery"), "homepage");
	}
	
	public String getPreviousURL() {
		return controller.getScriptPath() + "?" + getPreviousQuery();
	}

	protected Change change(Article article, ChangeDelegate delegate,
			String comment, boolean minor) throws Exception {
		Change change = new Change(getUser(), new Date(), comment, article,
				minor, delegate);
		change.perform();
		ChangeDAO.save(change);
		return change;
	}
	
	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}

	@Override
	public String getRedirect() {
		return redirect;
	}

	@Override
	public ActionDescriptor getDescriptor() {
		return descriptor;
	}
	
	@Override
	public void setDescriptor(ActionDescriptor desc) {
		this.descriptor = desc;
	}
	
}