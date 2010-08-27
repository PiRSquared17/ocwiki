package oop.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
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
//<<<<<<< local
		// set encoding
		response.setContentType("text/html;charset=UTF-8");
//=======
//>>>>>>> other

		// set variables
		String template = StringUtils.defaultIfEmpty((String) request
				.getSession().getAttribute("template"), "default");
		String templateEntry = "/templates/" + template + "/index.jsp";

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
			
			// check permission
			User user = SessionUtils.getUser(request.getSession());
			boolean loggedIn = (user != null);
			if (actionDesc.isLoginRequired() && !loggedIn) {
				error(request, response,
						"Bạn cần đăng nhập để thực hiện chức năng này.");
				return;
			}
			Set<String> requiredGroups = actionDesc.getRequiredGroups();
			if (!Utils.isEmpty(requiredGroups)
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
				response.sendRedirect(url);
			} else if (action.getRedirect() != null) {
				response.sendRedirect(action.getRedirect());
			} else {
				request.setAttribute("modules", getModules(request));
				request.setAttribute("action", action);
				request.getRequestDispatcher(templateEntry).forward(request,
						response);
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

	private Map<String, List<Module>> getModules(HttpServletRequest request) {
		Map<String, List<Module>> moduleMap = new HashMap<String, List<Module>>();
		User user = SessionUtils.getUser(request.getSession());
		boolean loggedIn = (user != null);
		for (ModuleDescriptor descriptor : Config.get().getModuleDescriptors()) {
			if (descriptor.isLoginRequired() && !loggedIn) {
				continue;
			}
			if (!Utils.isEmpty(descriptor.getRequiredGroups())
					&& !(loggedIn && descriptor.getRequiredGroups().contains(
							user.getGroup()))) {
				continue;
			}
			try {
				Module module = descriptor.createModule();
				module.init();
				if (moduleMap.get(descriptor.getPosition()) == null) {
					moduleMap.put(descriptor.getPosition(),
							new LinkedList<Module>());
				}
				moduleMap.get(descriptor.getPosition()).add(module);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// sắp xếp theo thứ tự
		for (List<Module> modules : moduleMap.values()) {
			Collections.sort(modules, moduleComparator);
		}
		return moduleMap;
	}
	
	public String getMainEntry() {
		return Config.get().getMainEntry();
	}
	
	public String getScriptPath() {
		return Config.get().getHomeDir() + getMainEntry();
	}

	private static Comparator<Module> moduleComparator = new Comparator<Module>() {
		
		@Override
		public int compare(Module o1, Module o2) {
			return o1.getOrder() - o2.getOrder();
		}
	};
	
}
