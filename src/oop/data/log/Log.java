package oop.data.log;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

import oop.data.User;

public class Log {

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

	@XmlElement
	public Date getTimestamp() {
		return timestamp;
	}

}