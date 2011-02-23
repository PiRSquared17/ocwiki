package org.ocwiki.taglib;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

public class SetJsTag extends SimpleTagSupport {

	private String var;
	private String value;
	private String jsVar;
	private String templateVar;
	
	@Override
	public void doTag() throws JspException, IOException {
		if (StringUtils.isEmpty(value)) {
			StringWriter sw = new StringWriter();
			getJspBody().invoke(sw);
			value = sw.toString();
		}
		value = StringEscapeUtils.escapeJavaScript(value);
		if (StringUtils.isNotEmpty(var)) {
			getJspContext().setAttribute(var, value);
		}
		if (StringUtils.isNotEmpty(jsVar)) {
			JspWriter out = getJspContext().getOut();
			out.print("<script>");
			out.print(jsVar);
			out.print("='");
			out.print(value);
			out.print("';</script>");
		}
		if (StringUtils.isNotEmpty(templateVar)) {
			JspWriter out = getJspContext().getOut();
			out.print("<script>");
			out.print(templateVar);
			out.print("=new Template('");
			out.print(value);
			out.print("');</script>");
		}
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

	public void setJsVar(String jsVar) {
		this.jsVar = jsVar;
	}

	public String getJsVar() {
		return jsVar;
	}

	public void setTemplateVar(String templateVar) {
		this.templateVar = templateVar;
	}

	public String getTemplateVar() {
		return templateVar;
	}

}
