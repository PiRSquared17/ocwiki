package oop.controller.rest.util;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class InvalidParamResult extends ErrorResult {

	public String name;
	public Object value;

	InvalidParamResult() {
	}
	
	public InvalidParamResult(String code, String name, Object value) {
		super(code);
		this.name = name;
		this.value = value;
	}

}
