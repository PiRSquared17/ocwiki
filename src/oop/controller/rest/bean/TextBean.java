package oop.controller.rest.bean;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.StringUtils;

@XmlRootElement
public class TextBean {

	private long id;
	private String text;

	public TextBean() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public static boolean isBlank(TextBean t) {
		return t == null
				|| (t.getId() <= 0 && StringUtils.isBlank(t.getText()));
	}
	
	public static boolean isNotBlank(TextBean t) {
		return t != null
				&& (t.getId() > 0 || StringUtils.isNotBlank(t.getText()));
	}
	
}
