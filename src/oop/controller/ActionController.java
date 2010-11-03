package oop.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oop.conf.ActionDescriptor;
import oop.conf.Config;
import oop.conf.ModuleDescriptor;
import oop.controller.action.Action;
import oop.controller.action.ActionException;
import oop.data.User;
import oop.module.Module;
import oop.persistence.HibernateUtil;
import oop.util.SessionUtils;
import oop.util.Utils;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.map.LazyMap;
import org.apache.commons.lang.StringUtils;

/**
 * Servlet implementation class Test
 */
public class ActionController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public ActionController() {
	}

	public void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		// set variables
		String template = StringUtils.defaultIfEmpty((String) request
				.getSession().getAttribute("template"), "default");

		request.getSession().setAttribute("login",
				SessionUtils.isLoggedIn(request.getSession()));
		request.setAttribute("config", Config.get());
		request.setAttribute("homeDir", Config.get().getHomeDir());
		request.setAttribute("scriptPath", getScriptPath());
		request.setAttribute("templatePath", Config.get().getTemplatePath()
				.replaceAll("\\$\\{template\\}", template));
		request.setAttribute("pageTitle", Config.get().getSiteName());

		// perform action
		try {
			// get action descriptor
			String actionStr;
			if (request.getServletPath().endsWith("index.jsp")) {
				actionStr = StringUtils.defaultIfEmpty(request
						.getParameter("action"), "homepage");
			} else {
				actionStr = request.getPathInfo().substring(1);
			}
			ActionDescriptor actionDesc = Config.get().getActionDescriptor(
					actionStr);
			if (actionDesc == null) {
				error(request, response, "Không tìm thấy hành động: "
						+ actionStr);
				return;
			}
			if (!actionDesc.isEnabled()) {
				String mess = "Hành động này tạm thời bị khoá. <a href=\""
						+ Config.get().getHomeDir() + "\">Trở về trang chủ</a>";
				error(request, response, mess);
				return;
			}
			
			// check permission
			User user = SessionUtils.getUser(request.getSession());
			boolean loggedIn = (user != null);
			if (actionDesc.isLoginRequired() && !loggedIn) {
				error(request, response,
						"Bạn cần đăng nhập để thực hiện chức năng này.");
				return;
			}
			Set<String> requiredGroups = actionDesc.getRequiredGroups();
			if (Utils.isNotEmpty(requiredGroups)
					&& (!loggedIn || !requiredGroups.contains(user.getGroup()))) {
				error(request, response, "Bạn cần phải thuộc nhóm "
						+ requiredGroups + " để thực hiện chức năng này.");
				return;
			}

			// perform
			Action action = actionDesc.createAction();
			action.setController(this);
			action.setRequest(request);
			action.perform();
			
			// flush current session to avoid late thrown exception
			HibernateUtil.getSession().flush();

			// forward next action or jsp
			request.getSession().setAttribute("previousQuery",
					request.getSession().getAttribute("currentQuery"));
			if (!(StringUtils.isEmpty(request.getQueryString())
					|| "error".equals(actionStr)
					|| "user.login".equals(actionStr) || "user.logout"
					.equals(actionStr))) {
				request.getSession().setAttribute("currentQuery",
						request.getQueryString());
			}
			if (action.getNextAction() != null) {
				String url = action.getNextAction();
				if (url.contains("&") && !url.contains("?")) {
					int i = url.indexOf('&');
					url = url.substring(0, i) + "?" + url.substring(i+1);
				}
				url = Config.get().getActionPath() + "/" + url;
				response.sendRedirect(response.encodeRedirectURL(url));
			} else if (action.getRedirect() != null) {
				response.sendRedirect(response.encodeRedirectURL(action
						.getRedirect()));
			} else {
				request.setAttribute("modules", getModules(request, action));
				request.setAttribute("action", action);
				String entry = "/templates/" + template + "/"
						+ actionDesc.getContainer();
				request.getRequestDispatcher(entry).forward(request, response);
			}
		} catch (ActionException e) {
			error(request, response, e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	private void error(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
		request.setAttribute("message", message);
		String uri = getMainEntry() + "?action=error";
		request.getRequestDispatcher(uri).forward(request, response);
	}

	@SuppressWarnings({ "unchecked" })
	private Map<String, List<Module>> getModules(HttpServletRequest request,
			final Action action) {
		final User user = SessionUtils.getUser(request.getSession());
		final boolean loggedIn = (user != null);
		return LazyMap.decorate(new HashMap<String, List<Module>>(), 
				new Transformer() {

			@Override
			public Object transform(Object position) {
				List<ModuleDescriptor> moduleDescriptors = 
					Config.get().getModuleDescriptors((String)position);
				if (Utils.isEmpty(moduleDescriptors)) {
					return Collections.EMPTY_LIST;
				}
				List<Module> modules = new ArrayList<Module>();
				for (ModuleDescriptor descriptor : moduleDescriptors) {
					if (!descriptor.isEnabled()) {
						continue;
					}
					if (descriptor.isLoginRequired() && !loggedIn) {
						continue;
					}
					if (Utils.isNotEmpty(descriptor.getInActions())
							&& !descriptor.getInActions().contains(
									action.getDescriptor().getName())) {
						continue;
					}
					if (Utils.isNotEmpty(descriptor.getRequiredGroups())
							&& !(loggedIn && descriptor.getRequiredGroups().contains(
									user.getGroup()))) {
						continue;
					}
					if (descriptor.getArticleType() != null) {
						boolean found = false;
						for (Class<?> type : descriptor.getArticleType()) {
							if (type.isAssignableFrom(
									action.getResource().getType())) {
								found = true;
								break;
							}
						}
						if (!found) {
							continue;
						}
					}
					try {
						Module module = descriptor.createModule();
						module.setResource(action.getResource());
						module.init();
						modules.add(module);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return modules;
			}
		});
	}
	
	public String getMainEntry() {
		return Config.get().getMainEntry();
	}
	
	public String getScriptPath() {
		return Config.get().getHomeDir() + getMainEntry();
	}

}
