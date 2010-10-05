package oop.controller.api;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import oop.controller.ActionController;
import oop.util.JsonUtils;

import org.codehaus.jackson.node.ObjectNode;

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
	 */
	protected ObjectNode success() {
		ObjectNode result = JsonUtils.getFactory().objectNode();
		result.put("status", APIResult.STATUS_SUCCESSFUL);
		return result;
	}
	
	/**
	 * Create a result object when the action is failed.
	 * @return JSON result object
	 */
	protected ObjectNode fail() {
		ObjectNode result = JsonUtils.getFactory().objectNode();
		result.put("status", APIResult.STATUS_FAILED);
		return result;
	}

}
