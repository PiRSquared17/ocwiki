package oop.data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import oop.data.log.ResourceLog;

@XmlRootElement
public class Resource<T extends Article> implements ArticleContainer<T> {

	private long id;
	private Date createDate;
	private User author;
	private Status status = Status.NORMAL;
	private int version = 0;
	private Class<T> type;
	private T article;
	private ResourceAccessibility accessibility = ResourceAccessibility.EVERYONE;
	private Set<Revision<T>> revisions = new HashSet<Revision<T>>();
	private Set<ResourceLog> logs = new HashSet<ResourceLog>();
	private Set<Resource<? extends Article>> linkedResources = new HashSet<Resource<? extends Article>>();

	Resource() {
	}

	public Resource(long id, Date createDate, User author, Status status,
			int revision, Class<T> type, T article, Set<Revision<T>> revisions,
			Set<ResourceLog> logs) {
		super();
		this.id = id;
		this.createDate = createDate;
		this.author = author;
		this.status = status;
		this.type = type;
		this.article = article;
		this.revisions = revisions;
		this.logs = logs;
	}

	public Resource(Date createDate, User author, Status status,
			Class<T> type, T article) {
		this.createDate = createDate;
		this.author = author;
		this.status = status;
		this.type = type;
		this.article = article;
	}

	@XmlElement
	public long getId() {
		return id;
	}

	@XmlElement
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@XmlElement
	public Date getCreateDate() {
		return createDate;
	}

	@XmlElement
	public User getAuthor() {
		return author;
	}

	@XmlTransient
	public Set<Revision<T>> getRevisions() {
		return revisions;
	}

	public int getVersion() {
		return version;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see oop.data.ArticleContainer#getArticle()
	 */
	@XmlElement
	public T getArticle() {
		return article;
	}

	public String getName() {
		return getArticle().getName();
	}

	public Namespace getNamespace() {
		return getArticle().getNamespace();
	}

	@Deprecated
	public void setArticle(T article) {
		this.article = article;
	}

	@XmlElement
	public Class<T> getType() {
		return type;
	}

	@XmlTransient
	public Set<ResourceLog> getLogs() {
		return logs;
	}

	public Revision<T> getLatestRevision() {
		return getRevisions().iterator().next();
	}

	public void setLinkedResources(Set<Resource<? extends Article>> linkedResources) {
		this.linkedResources = linkedResources;
	}

	public Set<Resource<? extends Article>> getLinkedResources() {
		return linkedResources;
	}

	public String getQualifiedName() {
		return article.getQualifiedName();
	}

	public void setAccessibility(ResourceAccessibility accessibility) {
		this.accessibility = accessibility;
	}

	public ResourceAccessibility getAccessibility() {
		return accessibility;
	}

}
