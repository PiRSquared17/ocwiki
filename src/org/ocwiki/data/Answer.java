package org.ocwiki.data;

public abstract class Answer {

	private long id;
	private Question question;

	public Answer() {
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Question getQuestion() {
		return question;
	}
	
	public abstract boolean isCorrect();

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

}
