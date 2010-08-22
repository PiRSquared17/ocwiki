package oop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oop.data.Article;
import oop.data.BaseQuestion;
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
		String url = "/action";
		try {
			Long id = Long.parseLong(request.getPathInfo().substring(1));
			Article article = ArticleDAO.fetchById(id);
			if (article instanceof BaseQuestion) {
				url += "/question.view?id=" + article.getId();
			} else if (article instanceof Test) {
				url += "/test.view?id=" + article.getId();
			} else if (article instanceof TestStructure) {
				url += "/teststruct.view?id=" + article.getId();
			} else {
				url += "/error?message=" + Utils.urlEncode("Lỗi nội bộ.");
			}
		} catch (NumberFormatException e) {
			url += "/error?message=" + Utils.urlEncode("Id không hợp lệ");
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}
