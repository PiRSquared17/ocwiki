package org.ocwiki.controller.rest.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class QuestionBean {

	private long id;
	private double mark;
	private MultichoiceQuestionBean base;
	private ResourceReferenceBean baseResource;
	private RevisionReferenceBean baseRevision;

	public QuestionBean() {
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getMark() {
		return mark;
	}

	public void setMark(double mark) {
		this.mark = mark;
	}

	public ResourceReferenceBean getBaseResource() {
		return baseResource;
	}

	public void setBaseResource(ResourceReferenceBean baseResource) {
		this.baseResource = baseResource;
	}

	public RevisionReferenceBean getBaseRevision() {
		return baseRevision;
	}

	public void setBaseRevision(RevisionReferenceBean baseRevision) {
		this.baseRevision = baseRevision;
	}

	public void setBase(MultichoiceQuestionBean base) {
		this.base = base;
	}

	public MultichoiceQuestionBean getBase() {
		return base;
	}

}
