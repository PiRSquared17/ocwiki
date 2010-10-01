package oop.controller.rest.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RevisionReferenceBean {

	private long id;

	public RevisionReferenceBean() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
