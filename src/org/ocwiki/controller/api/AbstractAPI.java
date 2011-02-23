package org.ocwiki.controller.api;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ocwiki.controller.ActionController;
import org.ocwiki.data.User;
import org.ocwiki.util.SessionUtils;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.oreilly.servlet.ParameterList;
import com.oreilly.servlet.ParameterParser;

public abstract class AbstractAPI implements API {

	protected ActionController controller;
	protected HttpServletRequest request;
	private ParameterList params;
	Object result;
	
	@Override
	public HttpServletRequest getRequest() {
		return request;
	}

	protected ParameterList getParams() {
		return params;
	}

	public void perform() throws IOException {
		try {
			result = performImpl();
		} catch (Exception e) {
			e.printStackTrace();
			result = new APIExceptionResult(e);
		}
	}
	
	protected abstract Object performImpl() throws Exception;

	public Object getResult() {
		return result;
	}

	@Override
	public void setRequest(HttpServletRequest request) {
		this.request = request;
		this.params = new ParameterParser(request);
	}

	/**
	 * Create a result object when the action is successful.
	 * @return JSON result object
	 * @throws JSONException 
	 */
	protected JSONObject success() throws JSONException {
		return new JSONObject().put("status", APIResult.STATUS_SUCCESSFUL);
	}
	
	/**
	 * Create a result object when the action is failed.
	 * @return JSON result object
	 * @throws JSONException 
	 */
	protected JSONObject fail() throws JSONException {
		return new JSONObject().put("status", APIResult.STATUS_FAILED);
	}
	
	protected HttpSession getSession() {
		return request.getSession();
	}

	protected User getUser() {
		return SessionUtils.getUser(getSession());
	}

}
