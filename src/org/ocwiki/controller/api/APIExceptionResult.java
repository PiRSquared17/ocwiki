package org.ocwiki.controller.api;

public class APIExceptionResult extends APIFailedResult {

	public String type;
	
	public APIExceptionResult(Exception ex) {
		super("exception", ex.getMessage());
		this.type = ex.getClass().getName();
	}
	
}
