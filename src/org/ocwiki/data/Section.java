package org.ocwiki.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ocwiki.util.Copiable;

public class Section implements Entity, Copiable<Section> {

	private long id;
	private Text content;
	private List<TestQuestion> questions = new ArrayList<TestQuestion>();

	public Section() {
	}

	public Section(Text content) {
		this.content = content;
	}

	public Map<Long, TestQuestion> getQuestionById() {
		HashMap<Long, TestQuestion> questionMap = new HashMap<Long, TestQuestion>();
		for (TestQuestion q : getQuestions()) {
			questionMap.put(q.getId(), q);
		}
		return questionMap;
	}

	public List<TestQuestion> getQuestions() {
		return questions;
	}

	public void setQuestions(List<TestQuestion> questions) {
		this.questions = questions;
	}

	public long getId() {
		return id;
	}

	public Text getContent() {
		return content;
	}

	public void setContent(Text text) {
		this.content = text;
	}

	public Section copy() {
		Section section = new Section();
		section.setContent(getContent());
		section.setQuestions(new ArrayList<TestQuestion>(getQuestions()));
		return section;
	}

	public void setId(long id) {
		this.id = id;
	}

}
