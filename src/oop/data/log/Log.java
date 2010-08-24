package oop.data.log;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import oop.data.User;

public class Log {

	private long id;
	protected User user;
	protected Date timestamp;

	public Log(User user, Date timestamp) {
		super();
		this.user = user;
		this.timestamp = timestamp;
	}

	public Log() {
		super();
	}

	@XmlElement
	public User getUser() {
		return user;
	}

	@XmlAttribute
	public Date getTimestamp() {
		return timestamp;
	}

	@XmlAttribute
	public long getId() {
		return id;
	}

}