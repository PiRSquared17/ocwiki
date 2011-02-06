package oop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oop.conf.Config;
import oop.util.SessionUtils;
import oop.util.TemplateUtils;

/**
 * Servlet implementation class ErrorController
 */
public class ErrorController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ErrorController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("inController", true);
		String template = TemplateUtils.getTemplate(request);
		
		// !!!IMPORTANT!!! Encoding error if delete the next line
		request.setCharacterEncoding("UTF-8");
		
		request.getSession().setAttribute("login",
				SessionUtils.isLoggedIn(request.getSession()));
		request.setAttribute("templatePath", Config.get().getTemplatePath()
				.replaceAll("\\$\\{template\\}", template));
		request.setAttribute("pageTitle", Config.get().getSiteName());

		String errorCode = request.getPathInfo();
		String entry = "/templates/" + template + "/errors" + errorCode
				+ ".jsp";
		request.getRequestDispatcher(entry).forward(request, response);
	}

}
