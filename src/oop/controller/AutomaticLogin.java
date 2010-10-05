package oop.controller;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * Application Lifecycle Listener implementation class Test
 * 
 */
public class AutomaticLogin implements ServletRequestListener {

	/**
	 * Default constructor.
	 */
	public AutomaticLogin() {
	}

	/**
	 * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
	 */
	public void requestDestroyed(ServletRequestEvent arg0) {
	}

	/**
	 * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
	 */
	public void requestInitialized(ServletRequestEvent event) {
		if (event.getServletRequest() instanceof HttpServletRequest) {
			HttpServletRequest httpRequest = (HttpServletRequest) event
					.getServletRequest();
			if (httpRequest.getSession(false) == null) {
			}
		}
	}

}
