package oop.controller.rest.util;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorResult {

	public String code;

	ErrorResult() {
	}
	
	public ErrorResult(String code) {
		super();
		this.code = code;
	}
	
}
