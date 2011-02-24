package org.ocwiki.data;

public class Solution extends TextArticle {

	private Resource<MultichoiceQuestion> question;
	
	public Solution(){
		
	}
	
	public Solution(String name, Namespace namespace, Text text, Resource<MultichoiceQuestion> resource){
		super(name, namespace, text);
		question = resource;
	}

	public void setQuestion(Resource<MultichoiceQuestion> question) {
		this.question = question;
	}

	public Resource<MultichoiceQuestion> getQuestion() {
		return question;
	}
	
}
