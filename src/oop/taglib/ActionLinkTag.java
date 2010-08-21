package oop.taglib;

import java.io.IOException;
import java.util.Map.Entry;

import javax.servlet.jsp.JspException;

import oop.controller.action.ActionUtil;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

public class ActionLinkTag extends AbstractLinkTag {

	@Override
	public void doTag() throws JspException, IOException {
		StringBuilder sb = new StringBuilder();
		out().print("<a");
		appendHref();
		appendClass();
		out().print("\">");
		getJspContext().getOut().print(sb);
		jspBody.invoke(getJspContext().getOut());
		getJspContext().getOut().print("</a>");
	}

	@Override
	protected void appendHref() throws IOException {
		if (!StringUtils.isEmpty(getConfirm())) {
			out().print(" href=\"#\" onclick=\"location.href='");
			out().print("if(");
			out().print(confirm);
			out().print(")");
			appendActionURL();
		} else {
			out().print(" href=\"");
			appendActionURL();
			out().print("\"");
		}
		out().print("'\"");
	}

	private void appendActionURL() throws IOException {
		out().print(ActionUtil.getActionURL(getName()));
		if (!getParams().isEmpty()) {
			out().print("?");
			for (Entry<String, String> entry : getParams().entrySet()) {
				out().print(entry.getKey());
				out().print("=");
				out().print(StringEscapeUtils.escapeXml(entry.getValue()));
			}
		}
	}
	
}
