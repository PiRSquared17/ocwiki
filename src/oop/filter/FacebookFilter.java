package oop.filter;

import java.io.IOException;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import oop.conf.Config;
import oop.util.Utils;

/**
 * Servlet Filter implementation class FacebookFilter
 */
public class FacebookFilter implements Filter {

    public FacebookFilter() {
    }

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			for (Cookie cookie : httpRequest.getCookies()) {
				if (cookie.getName().equals(
						"fbs_" + Config.get().getFacebookAppId())) {
					SortedMap<String, String> facebook = readFacebookCookie(cookie);
					if (facebook != null) {
						facebook.get("uid");
						//TODO log user in or create account
					}
					break;
				}
			}
		}
		chain.doFilter(request, response);
	}

	private SortedMap<String,String> readFacebookCookie(Cookie cookie) {
		SortedMap<String,String> map = getQueryMap(cookie.getValue());
		StringBuilder payload = new StringBuilder(); 
		for (Entry<String, String> entry : map.entrySet()) {
			if (!entry.getKey().equals("sig")) {
				payload.append(entry.getKey()).append("=").append(
						entry.getValue());
			}
		}
		payload.append(Config.get().getFacebookSecret());
		if (Utils.md5(payload.toString()).equals(map.get("sig"))) {
			return null;
		}
		return map;
	}

	public static SortedMap<String, String> getQueryMap(String query) {
		String[] params = query.split("&");
		SortedMap<String, String> map = new TreeMap<String, String>();
		for (String param : params) {
			String[] pair = param.split("=");
			String name = Utils.urlDecode(pair[0]);
			String value = Utils.urlDecode(pair[1]);
			map.put(name, value);
		}
		return map;
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

}
