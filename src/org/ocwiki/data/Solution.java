package org.ocwiki.data;

public class Solution extends TextArticle {

	private Resource<BaseQuestion> question;
	
	public Solution(){
		
	}
	
	public Solution(String name, Namespace namespace, Text text, Resource<BaseQuestion> resource){
		super(name, namespace, text);
		question = resource;
	}

	public void setQuestion(Resource<BaseQuestion> question) {
		this.question = question;
	}

	public Resource<BaseQuestion> getQuestion() {
		return question;
	}
	
}
