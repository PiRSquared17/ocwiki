package oop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oop.conf.APIDescriptor;
import oop.conf.Config;
import oop.controller.api.API;
import oop.data.User;
import oop.persistence.HibernateUtil;
import oop.util.SessionUtils;
import oop.util.Utils;

import org.codehaus.jackson.map.ObjectMapper;

import com.google.gson.JsonElement;

/**
 * Servlet implementation class APIController
 */
public class APIController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final ObjectMapper mapper = new ObjectMapper();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public APIController() {
		super();
		// TODO Auto-generated constructor stub
	}

	private void process(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			response.setContentType("application/json;charset=UTF-8");

			String actionStr = request.getPathInfo().substring(1);
			APIDescriptor descriptor = Config.get().getAPIDescriptor(actionStr);

			if (descriptor == null) {
				response.getWriter().write("{\"status\":\"failed\", \"errorCode\":\"no such API\"}");
				return;
			}
			
			Object result;

			// check permission
			User user = SessionUtils.getUser(request.getSession());
			boolean loggedIn = SessionUtils.isLoggedIn(request.getSession());
			if ((descriptor.isLoginRequired() && !loggedIn)
					|| (!Utils.isEmpty(descriptor.getRequiredGroups()) && (!loggedIn || !descriptor
							.getRequiredGroups().contains(user.getGroup())))) {
				// TODO real result
				result = "You don't have permission to perform this task.";
			} else {
				API api = descriptor.createAPI();
				api.setRequest(request);
				api.perform();
				result = api.getResult();
			}
			if (result instanceof String) {
				response.getWriter().write((String) result);
			} else if (result instanceof JsonElement) {
				response.getWriter().write(result.toString());
			} else {
				mapper.writeValue(response.getWriter(), result);
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
