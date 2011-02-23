package org.ocwiki.controller.rest.bean;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BaseArticleBean extends CategorizableArticleBean {

	private Set<ResourceReferenceBean> attachments = new HashSet<ResourceReferenceBean>();
	private Set<ResourceReferenceBean> embeds = new HashSet<ResourceReferenceBean>();

	public BaseArticleBean() {
	}
	
	public Set<ResourceReferenceBean> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<ResourceReferenceBean> attachments) {
		this.attachments = attachments;
	}

	public Set<ResourceReferenceBean> getEmbeds() {
		return embeds;
	}

	public void setEmbeds(Set<ResourceReferenceBean> embeds) {
		this.embeds = embeds;
	}

}
