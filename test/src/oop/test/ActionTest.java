package oop.test;

import oop.controller.ActionController;

import org.junit.Test;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebRequest;
import com.meterware.servletunit.InvocationContext;
import com.meterware.servletunit.ServletRunner;
import com.meterware.servletunit.ServletUnitClient;

public class ActionTest {

//	@Test
	public void questionList() throws Exception {
		ServletRunner sr = new ServletRunner();
		sr.registerServlet("controller", ActionController.class.getName());
		
		ServletUnitClient sc = sr.newClient();
		WebRequest request = new GetMethodWebRequest(
				"http://localhost:8080/ocwiki/index.jsp?action=question.list");
		
		TestConfig.getConfig(); // make it initialized
		InvocationContext ic = sc.newInvocation(request);
		ActionController serv = (ActionController) ic.getServlet();
		serv.doGet(ic.getRequest(), ic.getResponse());
		
		Object action = ic.getRequest().getAttribute("action");
	}
	
}
