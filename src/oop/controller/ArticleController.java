package oop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oop.conf.Config;
import oop.data.Article;
import oop.data.BaseQuestion;
import oop.data.Resource;
import oop.data.Test;
import oop.data.TestStructure;
import oop.db.dao.ArticleDAO;
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
			Long id = Long.parseLong(request.getPathInfo().substring(1));
			Resource<? extends Article> resource = ArticleDAO.fetchById(id);
			Article article = resource.getArticle();
			if (article == null) {
				url += "/error?message=" + Utils.urlEncode("Không tìm thấy bài viết.");
			} else if (article instanceof BaseQuestion) {
				url += "/question.view?id=" + resource.getId();
			} else if (article instanceof Test) {
				url += "/test.view?id=" + resource.getId();
			} else if (article instanceof TestStructure) {
				url += "/teststruct.view?id=" + resource.getId();
			} else {
				url += "/error?message=" + Utils.urlEncode("Lỗi nội bộ.");
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
