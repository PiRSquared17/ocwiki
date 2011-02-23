package org.ocwiki.taglib;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.ocwiki.data.Article;
import org.ocwiki.data.Resource;
import org.ocwiki.data.Text;

public class ParseTag extends SimpleTagSupport {

	private Resource<? extends Article> resource;
	private Object content;
	
	@Override
	public void doTag() throws JspException, IOException {
		if (content == null) {
			content = new StringWriter();
			getJspBody().invoke((Writer) content);
		} else if (content instanceof Text) {
			content = ((Text) content).getText();
		}
		getJspContext().getOut().print(content);
	}

	public void setContent(Object content) {
		this.content = content;
	}

	public Object getContent() {
		return content;
	}

	public void setResource(Resource<? extends Article> resource) {
		this.resource = resource;
	}

	public Resource<? extends Article> getResource() {
		return resource;
	}
	
}
