package oop.controller.rest.bean;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BaseArticleBean extends CategorizableArticleBean {

	private Set<ResourceBean> attachments = new HashSet<ResourceBean>();
	private Set<ResourceBean> embeds = new HashSet<ResourceBean>();

	public BaseArticleBean() {
	}
	
	public Set<ResourceBean> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<ResourceBean> attachments) {
		this.attachments = attachments;
	}

	public Set<ResourceBean> getEmbeds() {
		return embeds;
	}

	public void setEmbeds(Set<ResourceBean> embeds) {
		this.embeds = embeds;
	}

}
