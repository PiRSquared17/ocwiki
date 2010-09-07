package oop.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ParamTag extends SimpleTagSupport {

	private String name;
	private Object value;
	private Object defaultValue;

	@Override
	public void doTag() throws JspException, IOException {
		AbstractLinkTag link = (AbstractLinkTag) getParent();
		if (getValue() == null
				|| (getValue() instanceof String && ((String) getValue())
						.isEmpty())) {
			setValue(getDefault());
		}
		link.getParams().put(getName(), getValue());
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

	public void setValue(Object value) {
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	public Object getDefault() {
		return defaultValue;
	}

	public void setDefault(Object defaultValue) {
		this.defaultValue = defaultValue;
	}

}
