package oop.data;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Lớp đóng gói dữ liệu văn bản (không thay đổi được). 
 * @author cumeo89
 */
@XmlRootElement
public class Text implements Entity, Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement
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

	public long getId() {
		return id;
	}
	
	@XmlElement
	public String getText() {
		return text;
	}
	
	@Override
	public String toString() {
		return text;
	}

}
