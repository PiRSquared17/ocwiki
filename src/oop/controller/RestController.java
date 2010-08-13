package oop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import oop.persistence.HibernateUtil;

public class RestController extends com.sun.jersey.spi.container.servlet.ServletContainer {

	private static final long serialVersionUID = 1L;

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		try {
			super.service(arg0, arg1);
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
}
