package oop.taglib;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;

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
		out().print(sw.toString().trim());
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
		appendActionURL();
		out().print("'\"");
	}

}
