package oop.taglib;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map.Entry;

import javax.servlet.jsp.JspException;

import oop.controller.action.ActionUtil;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

public class ActionButtonTag extends AbstractActionTag {

	@Override
	public void doTag() throws JspException, IOException {
		// cause param tags to be read
		StringWriter sw = new StringWriter();
		jspBody.invoke(sw);
		// render
		out().print("<button type=\"button\"");
		appendHref();
		appendClass();
		appendOnclick();
		out().print("\">");
		out().print(sw);
		out().print("</button>");
	}

	@Override
	protected void appendHref() throws IOException {
		out().print(" onclick=\"location.href='");
		if (!StringUtils.isEmpty(getConfirm())) {
			out().print("if(");
			out().print(confirm);
			out().print(")");
		}
		out().print(ActionUtil.getActionURL(getName()));
		if (!getParams().isEmpty()) {
			boolean first = true;
			for (Entry<String, Object> entry : getParams().entrySet()) {
				out().print(first ? "?" : "&");
				first = false;
				out().print(entry.getKey());
				out().print("=");
				out().print(
						StringEscapeUtils
								.escapeXml(entry.getValue().toString()));
			}
		}
		out().print("'\"");
	}

}
