package org.ocwiki.data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.ocwiki.data.log.ResourceLog;
import org.ocwiki.persistence.search.ArticleTypeBridge;
import org.ocwiki.persistence.search.MultichoiceQuestionBridge;
import org.ocwiki.persistence.search.ResourceCustomizationBridge;
import org.ocwiki.persistence.search.TestBridge;
import org.ocwiki.util.Utils;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

@Indexed
@XmlRootElement
public class Resource<T extends Article> implements ArticleContainer<T>,
		HasVersion, Serializable {

	private static final long serialVersionUID = 1L;
	
	@DocumentId
	private long id;
	private Date createDate;
	@IndexedEmbedded
	private User author;
	@Field(index=Index.UN_TOKENIZED, store=Store.NO)
	private Status status = Status.NEW;
	private int version = 0;
	@Field(name="articleType")
	@FieldBridge(impl=ArticleTypeBridge.class)
	private Class<T> type;
	@IndexedEmbedded
	private T article;
	private ResourceAccessibility accessibility = ResourceAccessibility.EVERYONE;
	@IndexedEmbedded
	private Set<Revision<T>> revisions = new HashSet<Revision<T>>();
	private Set<ResourceLog> logs = new HashSet<ResourceLog>();
	private Set<Resource<? extends Article>> linkedResources = new HashSet<Resource<? extends Article>>();
	private Resource<? extends Article> link = null;
	private String urlFriendlyName;
	private long viewCount;

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
	 * @see org.ocwiki.data.ArticleContainer#getArticle()
	 */
	@XmlElement
	public T getArticle() {
		return article;
	}
	
	public void setArticle(T article) {
		this.article = article;
		computeUrlFriendlyName();
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
			return "#" + getId();
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

	/**
	 * Trả về tên đầy đủ của bài.
	 * 
	 * @return
	 * @see <a href="http://code.google.com/p/ocwiki/wiki/ArticleName">Article name</a>
	 */
	public String getQualifiedName() {
		if (getNamespace().getId() == Namespace.MAIN) {
			return getName();
		}
		return getNamespace().getName() + ":" + getName();
	}

	@XmlTransient
	public Set<Revision<T>> getRevisions() {
		return revisions;
	}
	
	public String getUrlFriendlyName() {
		if (urlFriendlyName == null) {
			computeUrlFriendlyName();
		}
		return urlFriendlyName;
	}

	private void computeUrlFriendlyName() {
		urlFriendlyName = Utils.toUrlFriendly(getQualifiedName());
	}
	
	public long getViewCount() {
		return viewCount;
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

	@Override
	public String toString() {
		return "Resource #" + id + " (" + type + ")";
	}
	
	/**
	 * Dùng làm placeholder cho Hibernate Search bridge.
	 */
	@Field(index=Index.UN_TOKENIZED, store=Store.NO)
	@FieldBridge(impl=ResourceCustomizationBridge.class)
	long getCustomization() {
		return id;
	}
	
	@Field(index=Index.TOKENIZED, store=Store.NO)
	@FieldBridge(impl=MultichoiceQuestionBridge.class)
	Object getQuestion() {
		return this;
	}
	
	@Field(index=Index.TOKENIZED, store=Store.NO)
	@FieldBridge(impl=TestBridge.class)
	Object getTest() {
		return this;
	}
	
}
