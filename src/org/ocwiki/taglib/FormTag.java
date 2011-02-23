package org.ocwiki.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.ocwiki.controller.action.ActionUtil;

import org.apache.commons.lang.StringUtils;

public class FormTag extends SimpleTagSupport {

	private String id;
	private String className;
	private String action;
	private String method;
	private String enctype;

	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		String actionURL = ActionUtil.getActionURL(getAction());
		// open form
		out.append("<form action=\"").append(actionURL).append("\"");
		if (StringUtils.isEmpty(enctype)) {
			enctype = "application/x-www-form-urlencoded";
		}
		out.append(" enctype=\"" + enctype + "\"");
		if (!StringUtils.isEmpty(id)) {
			out.append(" id=\"").append(id).append("\"");
		}
		if (!StringUtils.isEmpty(className)) {
			out.append(" id=\"").append(className).append("\"");
		}
		if (!StringUtils.isEmpty(method)) {
			out.append(" method=\"").append(method).append("\"");
		}
		out.append(">");
		// append edit token
		String editToken = (String) getJspContext().getAttribute("editToken",
				PageContext.SESSION_SCOPE);
		if (StringUtils.isNotEmpty(editToken)) {
			out.append("<input type=\"hidden\" name=\"editToken\" value=\"");
			out.append(editToken);
			out.append("\">");
		}
		// append form body
		getJspBody().invoke(null);
		// close form
		out.append("</form>");
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setClass(String className) {
		this.className = className;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getAction() {
		return action;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getMethod() {
		return method;
	}

	public void setEnctype(String enctype) {
		this.enctype = enctype;
	}

	public String getEnctype() {
		return enctype;
	}

}
