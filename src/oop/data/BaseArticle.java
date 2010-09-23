package oop.data;

import java.util.HashSet;
import java.util.Set;

public abstract class BaseArticle extends CategorizableArticle {

	private Set<Resource<File>> attachments = new HashSet<Resource<File>>();
	private Set<Resource<File>> embeds = new HashSet<Resource<File>>();

	BaseArticle() {
	}
	
	public BaseArticle(Namespace namespace, Text content) {
		super(namespace, content);
	}

	public void setAttachments(Set<Resource<File>> attachments) {
		this.attachments = attachments;
	}

	public Set<Resource<File>> getAttachments() {
		return attachments;
	}
	
	protected <T> T copyTo(T obj) {
		super.copyTo(obj);
		BaseArticle article = (BaseArticle) obj;
		article.setTopics(new HashSet<Resource<Topic>>(getTopics()));
		return obj;
	}

	public void setEmbeds(Set<Resource<File>> embeds) {
		this.embeds = embeds;
	}

	public Set<Resource<File>> getEmbeds() {
		return embeds;
	}

}
