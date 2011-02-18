package oop.filter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oop.conf.Config;
import oop.controller.OcwikiApp;

/**
 * Servlet Filter implementation class UserPrettyUrl
 */
public class UrlRewritingFilter implements Filter {

	private Pattern revisionPattern;
	private Pattern articlePattern;
	private Pattern userPattern;
	
	/**
	 * Default constructor.
	 */
	public UrlRewritingFilter() {
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		Config config = OcwikiApp.get().getConfig();
		String idPattern = "/(\\d+)(?:-.+)?(?:.\\w+)?";
		articlePattern = Pattern.compile(Pattern.quote(config
				.getArticlePath()) + idPattern);
		revisionPattern = Pattern.compile(Pattern.quote(config
				.getRevisionPath()) + idPattern);
		userPattern = Pattern.compile(Pattern.quote(config
				.getUserPath()) + idPattern);
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
			long id;
			if ((id = getId(path, revisionPattern)) > 0) {
				url = "/article.viewold?id=" + id;
			} else if ((id = getId(path, articlePattern)) > 0) {
				url = "/article.view?id=" + id;
			} else if ((id = getId(path, userPattern)) > 0) {
				url = "/user.profile?user=" + id;
			}

			if (url != null) {
				dispatch(OcwikiApp.get().getConfig().getActionPath() + url,
						httpRequest, (HttpServletResponse) response);
				return;
			}
		}
		chain.doFilter(request, response);
	}

	private long getId(String path, Pattern pattern) {
		Matcher matcher = pattern.matcher(path);
		if (matcher.matches()) {
			return Long.parseLong(matcher.group(1));
		}
		return -1;
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
