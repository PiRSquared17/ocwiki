package oop.data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import oop.data.log.ResourceLog;
import oop.util.Utils;

@XmlRootElement
public class Resource<T extends Article> implements ArticleContainer<T>, HasVersion {

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
	private Resource<? extends Article> link = null;

	public Resource() {
	}

	public Resource(long id, Date createDate, User author, Status status,
			int revision, Class<T> type, T article, Set<Revision<T>> revisions,
			Set<ResourceLog> logs) {
		super();
		if (!type.isInstance(article)) {
			throw new ClassCastException();
		}
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
	
	public void setId(long id) {
		this.id = id;
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
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@XmlElement
	public User getAuthor() {
		return author;
	}
	
	public void setAuthor(User author) {
		this.author = author;
	}

	public int getVersion() {
		return version;
	}
	
	public void setVersion(int version) {
		this.version = version;
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
	
	public void setArticle(T article) {
		this.article = article;
	}
	
	@XmlElement(name="articleType")
	public Class<T> getType() {
		return type;
	}

	public Resource<? extends Article> getLink() {
		return link;
	}

	public void setLink(Resource<? extends Article> link) {
		this.link = link;
	}

	public String getName() {
		if (getArticle().getName() == null) {
			return "#" + getArticle().getId();
		}
		return getArticle().getName();
	}
	
	public void setType(Class<T> type) {
		this.type = type;
	}

	public Namespace getNamespace() {
		return getArticle().getNamespace();
	}

	@XmlTransient
	public Set<ResourceLog> getLogs() {
		return logs;
	}

	public Set<Resource<? extends Article>> getLinkedResources() {
		return linkedResources;
	}

	public void setLinkedResources(Set<Resource<? extends Article>> linkedResources) {
		this.linkedResources = linkedResources;
	}

	public ResourceAccessibility getAccessibility() {
		return accessibility;
	}

	public void setAccessibility(ResourceAccessibility accessibility) {
		this.accessibility = accessibility;
	}

	public String getQualifiedName() {
		return article.getQualifiedName();
	}

	@XmlTransient
	public Set<Revision<T>> getRevisions() {
		return revisions;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Resource<?>) {
			return id == ((Resource<?>)obj).id;
		}
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return Utils.hashCode(id);
	}
	
}
