package oop.data;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.StringUtils;

/**
 * Lớp đóng gói dữ liệu văn bản (không thay đổi được). 
 * @author cumeo89
 */
@XmlRootElement
public class Text implements Entity, Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String text;

	/**
	 * For Hibernate only
	 */
	Text() {
		// DO NOTHING
	}
	
	public Text(String content) {
		super();
		this.text = content;
	}

	@XmlElement
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@XmlElement
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return text;
	}
	
	public static boolean isBlank(Text t) {
		return t == null
				|| (t.getId() <= 0 && StringUtils.isBlank(t.getText()));
	}
	
	public static boolean isNotBlank(Text t) {
		return t != null
				&& (t.getId() > 0 || StringUtils.isNotBlank(t.getText()));
	}

}
