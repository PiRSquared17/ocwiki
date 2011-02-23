package org.ocwiki.taglib;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;


import org.apache.commons.lang.StringUtils;

public class ActionLinkTag extends AbstractActionTag {

	private String title;
	private String target;
	
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
		appendTarget();
		appendTitle();
		out().print("\">");
		out().append(sb);
		out().append(sw.toString().trim());
		out().append("</a>");
	}

	private void appendTarget() throws IOException {
		if (StringUtils.isNotEmpty(target)) {
			out().append(" target=\"").append(target).append("\"");
		}
	}
	
	private void appendTitle() throws IOException {
		if (StringUtils.isNotEmpty(title)) {
			out().append(" title=\"").append(title).append("\"");
		}
	}

	@Override
	protected void appendHref() throws IOException {
		out().append(" href=\"");
		appendActionURL();
		out().append("\"");
		if (!StringUtils.isEmpty(getConfirm())) {
			out().append(" onclick=\"if(!").append(confirm)
					.append(") return false;\"");
		}
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getTarget() {
		return target;
	}

}
