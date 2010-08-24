package oop.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.lang.StringUtils;

public class ParamTag extends SimpleTagSupport {

	private String name;
	private String value;
	private String defaultValue;
	
	@Override
	public void doTag() throws JspException, IOException {
		AbstractLinkTag link = (AbstractLinkTag) getParent();
		link.getParams().put(getName(),
				StringUtils.defaultIfEmpty(getValue(), getDefault()));
	}
	
	@Override
	public void setParent(JspTag parent) {
		super.setParent(parent);
	}
	
	@Override
	public JspTag getParent() {
		return super.getParent();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public String getDefault() {
		return defaultValue;
	}
	
	public void setDefault(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	
}
