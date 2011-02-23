package org.ocwiki.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ocwiki.util.Copiable;

public class Section implements Entity, Copiable<Section> {

	private long id;
	private Text content;
	private List<Question> questions = new ArrayList<Question>();

	public Section() {
	}

	public Section(Text content) {
		this.content = content;
	}

	public Map<Long, Question> getQuestionById() {
		HashMap<Long, Question> questionMap = new HashMap<Long, Question>();
		for (Question q : getQuestions()) {
			questionMap.put(q.getId(), q);
		}
		return questionMap;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
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
		section.setQuestions(new ArrayList<Question>(getQuestions()));
		return section;
	}

	public void setId(long id) {
		this.id = id;
	}

}
