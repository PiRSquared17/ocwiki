package org.ocwiki.controller.api;

public class APIFailedResult extends APIResult {

	public String code;
	public String message;
	
	public APIFailedResult(String code, String message) {
		super(STATUS_FAILED);
		this.code = code;
		this.message = message;
	}

}
