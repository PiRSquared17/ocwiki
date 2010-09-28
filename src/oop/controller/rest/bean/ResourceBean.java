package oop.controller.rest.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import oop.data.HasVersion;
import oop.data.ResourceAccessibility;
import oop.data.Status;

@SuppressWarnings("unchecked")
@XmlRootElement
public class ResourceBean implements HasVersion {

	private long id;
	private Date createDate;
	private UserReferenceBean author;
	private Status status = Status.NORMAL;
	private int version = 0;
	private ArticleBean article;
	private ResourceAccessibility accessibility = ResourceAccessibility.EVERYONE;
	private ResourceReferenceBean link = null;
	private Class type;

	public ResourceBean() {
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public UserReferenceBean getAuthor() {
		return author;
	}

	public void setAuthor(UserReferenceBean author) {
		this.author = author;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public ArticleBean getArticle() {
		return article;
	}

	public void setArticle(ArticleBean article) {
		this.article = article;
	}

	public ResourceAccessibility getAccessibility() {
		return accessibility;
	}

	public void setAccessibility(ResourceAccessibility accessibility) {
		this.accessibility = accessibility;
	}

	public ResourceReferenceBean getLink() {
		return link;
	}

	public void setLink(ResourceReferenceBean link) {
		this.link = link;
	}

	public void setType(Class type) {
		this.type = type;
	}

	@XmlElement(name="articleType")
	public Class getType() {
		return type;
	}
	
}
