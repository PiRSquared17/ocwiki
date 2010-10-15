package com.oreilly.servlet;

public class ParameterNotFoundException extends Exception {
	private static final long serialVersionUID = 4422875541335393521L;
	private String name;
	
	public ParameterNotFoundException(String name) {
		super();
		this.name = name;
	}

	public ParameterNotFoundException(String name, String s) {
		super(s);
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
