package oop.controller.action;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import oop.conf.ActionDescriptor;
import oop.conf.Config;
import oop.controller.ActionController;
import oop.controller.rest.bean.ResourceBean;
import oop.controller.rest.bean.ResourceMapper;
import oop.data.Article;
import oop.data.Resource;
import oop.data.Revision;
import oop.data.User;
import oop.db.dao.ResourceDAO;
import oop.util.SessionUtils;

import org.apache.commons.lang.ObjectUtils;

import com.oreilly.servlet.ParameterList;
import com.oreilly.servlet.ParameterNotFoundException;
import com.oreilly.servlet.ParameterParser;

public abstract class AbstractAction implements Action {

	private ActionController controller;

	/**
	 * <p>
	 * Chứa yêu cầu được gửi đến server, hạn chế sử dụng trực tiếp đối tượng này
	 * để tăng tính rõ ràng, dễ đọc và tăng khả năng kiểm thử.
	 * </p>
	 * <p>
	 * Khi nhận tham số đầu vào, nên sử dụng các hàm tiện ích như getParams(),
	 * getUser(), getSession(). Khi trả về giá trị, nên định nghĩa thuộc tính
	 * mới của lớp (thuộc tính sẽ được truy cập bởi các trang JSP.
	 * </p>
	 */
	@Deprecated
	protected HttpServletRequest request;
	private String nextAction = null;
	private String redirect = null;
	private ParameterList params;
	private ActionDescriptor descriptor;
	private Map<String, String> errors = null;

	private String title;

	public AbstractAction() {
		// default constructor
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see oop.controller.action.Action#perform()
	 */
	public void perform() throws IOException, ServletException {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see oop.controller.action.Action#getController()
	 */
	public ActionController getController() {
		return controller;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * oop.controller.action.Action#setController(oop.controller.ControllerServlet
	 * )
	 */
	public void setController(ActionController controller) {
		this.controller = controller;
	}

	/*
	 * (non-Javadoc)getPreviousURL
	 * 
	 * @see
	 * oop.controller.action.Action#setRequest(javax.servlet.http.HttpServletRequest
	 * )
	 */
	public void setRequest(HttpServletRequest request) {
		this.request = request;
		params = new ParameterParser(request);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see oop.controller.action.Action#getRequest()
	 */
	public HttpServletRequest getRequest() {
		return request;
	}

	protected ParameterList getParams() {
		return params;
	}

	protected abstract void performImpl() throws IOException, ServletException;

	protected void title(String pageTitle) {
		request.setAttribute("pageTitle", pageTitle + " - "
				+ Config.get().getSiteName());
	}

	protected void setNextAction(String nextAction) {
		this.nextAction = nextAction;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see oop.controller.action.Action#getNextAction()
	 */
	public String getNextAction() {
		return nextAction;
	}

	protected void addMessage(String msg) {
		request.setAttribute("message", msg);
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

	protected <T extends Article> Revision<T> saveNewRevision(
			Resource<T> resource, T article) throws ParameterNotFoundException {
		if (resource.getVersion() != getBaseVersion()) {
			throw new RuntimeException("old version");
		}

		User user = SessionUtils.getUser(getSession());
		String editToken = SessionUtils.getEditToken(getSession());
		if (!editToken.equals(getParams().getString("editToken"))) {
			throw new RuntimeException("wrong edit token");
		}
		String summary = getParams().getString("summary",
				"Sửa bài #" + resource.getId() + ": " + resource.getName());
		boolean minor = getParams().getBoolean("minor", false);

		return ResourceDAO.update(resource, article, user, summary, minor);
	}
	
	@SuppressWarnings("unchecked")
	protected <T extends Article> Resource<T> saveNewResource(T article) {
		User user = SessionUtils.getUser(getSession());
		String editToken = SessionUtils.getEditToken(getSession());
		if (!editToken.equals(getParams().getString("editToken"))) {
			throw new RuntimeException("wrong edit token");
		}
		return ResourceDAO.create(user, (Class<T>)article.getClass(), article);
	}

	protected int getBaseVersion() throws ParameterNotFoundException {
		return getParams().getInt("basever");
	}

	public Map<String, String> getErrors() {
		if (errors == null) {
			return Collections.emptyMap();
		}
		return errors;
	}

	protected void addError(String code, String message) {
		if (errors == null) {
			errors = new HashMap<String, String>();
		}
		errors.put(code, message);
	}
	
	public boolean hasErrors() {
		return errors != null && !errors.isEmpty();
	}
	
	public boolean hasNoErrors() {
		return errors == null || errors.isEmpty();
	}
	
	@Override
	public String getTitle() {
		return title;
	}
	
	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public Resource<? extends Article> getResource() {
		return null;
	}

	@SuppressWarnings("unchecked")
	public ResourceBean getResourceBean() {
		try {
			return ResourceMapper.get().toBean((Resource<Article>) getResource());
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Article getArticle() {
		return null;
	}
	
}