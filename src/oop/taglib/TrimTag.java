package oop.taglib;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class TrimTag extends SimpleTagSupport{
	
	@Override
	public void doTag() throws JspException, IOException {
		StringWriter sw = new StringWriter();
		getJspBody().invoke(sw);
		String body = sw.toString().trim();
		getJspContext().getOut().print(body);
	}

}
