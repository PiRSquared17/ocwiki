package oop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import oop.persistence.HibernateUtil;

public class RestController extends
		com.sun.jersey.spi.container.servlet.ServletContainer {

	private static final long serialVersionUID = 1L;

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		try {
			super.service(request, response);
		} finally {
			HibernateUtil.closeSession();
		}
	}

}
