package oop.data;

public class Solution extends TextArticle {

	private Resource<BaseQuestion> question;

	public void setQuestion(Resource<BaseQuestion> question) {
		this.question = question;
	}

	public Resource<BaseQuestion> getQuestion() {
		return question;
	}
	
}
