package org.ocwiki.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ocwiki.conf.APIDescriptor;
import org.ocwiki.conf.Config;
import org.ocwiki.controller.api.API;
import org.ocwiki.data.User;
import org.ocwiki.persistence.HibernateUtil;
import org.ocwiki.util.JsonUtils;
import org.ocwiki.util.SessionUtils;
import org.ocwiki.util.Utils;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

/**
 * Servlet implementation class APIController
 */
public class APIController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public APIController() {
		super();
	}

	private void process(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			response.setContentType("application/json;charset=UTF-8");

			String actionStr = request.getPathInfo().substring(1);
			APIDescriptor descriptor = Config.get().getAPIDescriptor(actionStr);

			if (descriptor == null) {
				response.getWriter().write("{\"status\":\"failed\", \"code\":\"no such API\"}");
				return;
			}
			
			Object result;

			// check permission
			User user = SessionUtils.getUser(request.getSession());
			boolean loggedIn = SessionUtils.isLoggedIn(request.getSession());
			if ((descriptor.isLoginRequired() && !loggedIn)
					|| (Utils.isNotEmpty(descriptor.getRequiredGroups()) && (!loggedIn || !descriptor
							.getRequiredGroups().contains(user.getGroup())))) {
				// TODO real result
				result = "{\"status\":\"failed\", \"code\":no permission\"\"}";
			} else {
				API api = descriptor.createAPI();
				api.setRequest(request);
				api.perform();
				result = api.getResult();
			}
			if (result instanceof String) {
				response.getWriter().write((String) result);
			} else if (result instanceof JsonNode
					|| result instanceof JSONObject
					|| result instanceof JSONArray) {
				response.getWriter().write(result.toString());
			} else {
				JsonUtils.toJson(result, response.getWriter());
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write(e.getMessage());
		} finally {
			HibernateUtil.closeSession();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

}
