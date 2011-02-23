package org.ocwiki.controller.api;

public class APIResult {

	public static final String STATUS_SUCCESSFUL = "successful";
	public static final String STATUS_FAILED = "failed";
	public static final String STATUS_EXCEPTION = "exception";
	
	public String status;

	public APIResult() {
		this(STATUS_SUCCESSFUL);
	}
	
	public APIResult(String status) {
		super();
		this.status = status;
	}
	
	public static final APIResult SUCCESSFUL = new APIResult(STATUS_SUCCESSFUL);
	
}
