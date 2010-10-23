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

/**
 * Servlet Filter implementation class UserPrettyUrl
 */
public class UrlRewritingFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public UrlRewritingFilter() {
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
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
		if (request instanceof HttpServletRequest
				&& response instanceof HttpServletResponse) {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			String path = httpRequest.getRequestURL().toString();
			String url = null;
			String articlePath = Config.get().getArticlePath();
			String userPath = Config.get().getUserPath();
			if (path.startsWith(articlePath)) {
				String pathInfo = path.substring(articlePath.length());
				if (pathInfo.startsWith("/revision")) {
					url = "/article.viewold?id=" + pathInfo.substring(10);
				} else {
					url = "/article.view?id=" + pathInfo.substring(1);
				}
			} else if (path.startsWith(userPath)) {
				String name = path.substring(userPath.length() + 1);
				url = "/user.profile?user=" + name;
			}

			if (url != null) {
				dispatch(Config.get().getActionPath() + url, httpRequest,
						(HttpServletResponse) response);
				return;
			}
		}
		chain.doFilter(request, response);
	}

	private void dispatch(String path, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String home = Config.get().getHomeDir();
		if (path.startsWith(home)) {
			path = path.substring(home.length());
			request.getRequestDispatcher(path).forward(request, response);
		} else {
			response.sendRedirect(path);
		}
	}

}
