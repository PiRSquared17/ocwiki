package org.ocwiki.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import org.ocwiki.util.Copiable;

public class TestQuestion implements Entity, Copiable<TestQuestion> {

	private long id;
	@XmlElement
	private Resource<? extends MultichoiceQuestion> baseResource;
	@XmlElement
	private Revision<? extends MultichoiceQuestion> baseRevision;
	private double mark;

	public TestQuestion() {
	}

	public TestQuestion(ArticleContainer<? extends MultichoiceQuestion> baseContainer, double d) {
		setBaseContainer(baseContainer);
		this.mark = d;
	}

	/**
	 * Dành cho JSP, tránh sử dụng hàm này.
	 * 
	 * @return
	 */
	@Deprecated
	public Map<Long, Choice> getChoiceById() {
		Map<Long, Choice> usedAnswerMap = new HashMap<Long, Choice>();
		for (Choice choice : getAnswers()) {
			usedAnswerMap.put(choice.getId(), choice);
		}
		return usedAnswerMap;
	}

	@XmlElement
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@XmlTransient
	public List<Choice> getAnswers() {
		return getBase().getChoices();
	}

	@XmlElement
	public double getMark() {
		return mark;
	}

	public void setMark(double mark) {
		this.mark = mark;
	}

	@XmlTransient
	public Text getContent() {
		return getBase().getContent();
	}

	@XmlTransient
	public int getLevel() {
		return getBase().getLevel();
	}

	@XmlElement
	public MultichoiceQuestion getBase() {
		return baseResource.getArticle();
	}

	@XmlTransient
	public ArticleContainer<? extends MultichoiceQuestion> getBaseContainer() {
		return baseRevision == null ? baseResource : baseRevision;
	}
	
	public Resource<? extends MultichoiceQuestion> getBaseResource() {
		return baseResource;
	}
	
	public Revision<? extends MultichoiceQuestion> getBaseRevision() {
		return baseRevision;
	}

	public void setBaseContainer(ArticleContainer<? extends MultichoiceQuestion> baseContainer) {
		if (baseContainer instanceof Resource<?>) {
			baseResource = (Resource<? extends MultichoiceQuestion>) baseContainer;
			baseRevision = null;
		} else if (baseContainer instanceof Revision<?>) {
			baseResource = ((Revision<? extends MultichoiceQuestion>) baseContainer).getResource();
			baseRevision = (Revision<? extends MultichoiceQuestion>) baseContainer;
		}
	}

	@Override
	public TestQuestion copy() {
		return new TestQuestion(getBaseContainer(), getMark());
	}

	public void setBaseResource(Resource<MultichoiceQuestion> resource) {
		this.baseResource = resource;
	}

	public void setBaseRevision(Revision<MultichoiceQuestion> revision) {
		this.baseRevision = revision;
	}

}
