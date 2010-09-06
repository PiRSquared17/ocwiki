package oop.taglib;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map.Entry;

import javax.servlet.jsp.JspException;

import oop.controller.action.ActionUtil;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

public class ActionLinkTag extends AbstractLinkTag {

	private String title;
	
	@Override
	public void doTag() throws JspException, IOException {
		// cause param tags to be read
		StringWriter sw = new StringWriter();
		jspBody.invoke(sw);
		// render
		StringBuilder sb = new StringBuilder();
		out().print("<a");
		appendHref();
		appendClass();
		appendOnclick();
		out().print("\">");
		out().append(sb);
		out().append(sw.toString());
		out().append("</a>");
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
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

}
