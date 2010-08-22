package oop.taglib;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

public abstract class AbstractLinkTag extends SimpleTagSupport {

	private Map<String, String> params = new HashMap<String, String>();
	private String name;
	protected String cssClass;
	protected String confirm;
	protected JspFragment jspBody;

	public AbstractLinkTag() {
		super();
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public String getConfirm() {
		return confirm;
	}

	@Override
	protected JspFragment getJspBody() {
		return jspBody;
	}

	@Override
	public void setJspBody(JspFragment jspBody) {
		this.jspBody = jspBody;
	}

	protected void appendClass() throws IOException {
		if (!StringUtils.isEmpty(cssClass)) {
			out().print(" class=\"");
			out().print(StringEscapeUtils.escapeXml(cssClass));
			out().print("\"");
		}
	}

	protected abstract void appendHref() throws IOException, JspException;

	protected JspWriter out() {
		return getJspContext().getOut();
	}

}