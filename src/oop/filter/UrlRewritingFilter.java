package oop.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oop.conf.Config;
import oop.data.User;
import oop.db.dao.UserDAO;
import oop.util.Utils;

/**
 * Servlet Filter implementation class UserPrettyUrl
 */
public class UrlRewritingFilter implements Filter {

	private Config config;

	/**
	 * Default constructor.
	 */
	public UrlRewritingFilter() {
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		config = Config.get();
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			String path = httpRequest.getRequestURL().toString();
			String url = null;
			if (path.startsWith(config.getArticlePath())) {
				String pathInfo = path.substring(config.getArticlePath().length());
				try {
					if (pathInfo.startsWith("/revision")) {
						long id = Long.parseLong(pathInfo.substring(10));
						url = "/article.viewold?id=" + id;
					} else {
						long id = Long.parseLong(pathInfo.substring(1));
						url = "/article.view?id=" + id;
					}
				} catch (NumberFormatException ex) {
					url += "/error?message=" + Utils.urlEncode("Id không hợp lệ");
				}
			} else if (path.startsWith(config.getUserPath())) {
				String name = path.substring(config.getUserPath().length()+1);
				User user = UserDAO.fetchByUsername(name);
				if (user == null) {
					url = "/error?message=" + Utils.urlEncode("Người sử dụng không tồn tại");
				} else {
					url = "/user.profile?user=" + user.getId();
				}
			}
			if (url != null) {
				dispatch(config.getActionPath() + url, request, response);
				return;
			}
		}
		chain.doFilter(request, response);
	}
	
	private void dispatch(String path, ServletRequest request, ServletResponse response) throws ServletException, IOException {
		String home = Config.get().getHomeDir();
		if (path.startsWith(home)) {
			path = path.substring(home.length());
			request.getRequestDispatcher(path).forward(request, response);
		} else {
			if (response instanceof HttpServletResponse) {
				((HttpServletResponse) response).sendRedirect(path);
			} else {
				throw new UnsupportedOperationException();
			}
		}
	}

}
