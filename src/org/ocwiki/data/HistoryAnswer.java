package org.ocwiki.data;

public abstract class HistoryAnswer {

	private long id;
	private MultichoiceQuestion question;

	public HistoryAnswer() {
	}

	public void setQuestion(MultichoiceQuestion question) {
		this.question = question;
	}

	public MultichoiceQuestion getQuestion() {
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
