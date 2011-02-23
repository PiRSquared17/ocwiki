package org.ocwiki.data;

public abstract class HistoryAnswer {

	private long id;
	private BaseQuestion question;

	public HistoryAnswer() {
	}

	public void setQuestion(BaseQuestion question) {
		this.question = question;
	}

	public BaseQuestion getQuestion() {
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
