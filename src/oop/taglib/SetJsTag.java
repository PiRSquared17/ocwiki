package oop.taglib;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

public class SetJsTag extends SimpleTagSupport {

	private String var;
	private String value;
	
	@Override
	public void doTag() throws JspException, IOException {
		if (StringUtils.isEmpty(value)) {
			StringWriter sw = new StringWriter();
			getJspBody().invoke(sw);
			value = sw.toString();
		}
		value = StringEscapeUtils.escapeJavaScript(value);
		getJspContext().setAttribute(var, value);
	}

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
