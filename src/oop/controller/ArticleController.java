package oop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oop.conf.Config;
import oop.util.Utils;

/**
 * Servlet implementation class ArticleController
 */
public class ArticleController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ArticleController() {
		super();
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

	private void process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String url = Config.get().getActionPath();
		try {
			long id;
			String pathInfo = request.getPathInfo();
			if (pathInfo.startsWith("/revision")) {
				id = Long.parseLong(pathInfo.substring(10));
				url += "/article.viewold?id=" + id;
			} else {
				id = Long.parseLong(pathInfo.substring(1));
				url += "/article.view?id=" + id;
			}
		} catch (NumberFormatException e) {
			url += "/error?message=" + Utils.urlEncode("Id không hợp lệ");
		}
		if (url.startsWith(Config.get().getHomeDir())) {
			url = url.substring(Config.get().getHomeDir().length());
			request.getRequestDispatcher(url).forward(request, response);
		} else {
			response.sendRedirect(url);
		}
	}

}
