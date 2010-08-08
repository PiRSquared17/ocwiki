package oop.data;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import oop.change.Change;

@XmlRootElement
public abstract class Article implements Entity {

	protected long id;
	protected Date createDate;
	protected Change lastChange;
	protected User author;
	protected boolean deleted;
	protected Text content;
	protected int version;
	
	@XmlAttribute
	public long getId() {
		return id;
	}
	
	@XmlAttribute
	public Date getCreateDate() {
		return createDate;
	}
	
	public void setLastChange(Change lastChange) {
		this.lastChange = lastChange;
	}
	
	@XmlTransient
	public Change getLastChange() {
		return lastChange;
	}

	@XmlElement
	public User getAuthor() {
		return author;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@XmlAttribute
	public boolean isDeleted() {
		return deleted;
	}

	public Text getContent() {
		return content;
	}

	public void setContent(Text content) {
		this.content = content;
	}
	
	@XmlAttribute
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@XmlAttribute
	public abstract String getName();
	
	public String getQualifiedName() {
		return getNamespace().getDisplayName() + ":" + getName();
	}
	
	public abstract Namespace getNamespace();
	
}
