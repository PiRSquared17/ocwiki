package oop.controller.api;

import javax.servlet.http.HttpServletRequest;

public interface API {

	public void perform() throws Exception;

	public HttpServletRequest getRequest();

	public void setRequest(HttpServletRequest request);

	public Object getResult();

}
