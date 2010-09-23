package oop.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestVersion {

	private Article test;
	private String code;
	private Map<Section, List<Question>> questions = new HashMap<Section, List<Question>>();
	private Map<Question, List<Answer>> answers = new HashMap<Question, List<Answer>>();

	TestVersion() {
	}
	
	public TestVersion(Article test, String code,
			Map<Section, List<Question>> questions,
			Map<Question, List<Answer>> answers) {
		super();
		this.test = test;
		this.code = code;
		this.questions = questions;
		this.answers = answers;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Article getTest() {
		return test;
	}
	
	public void setTest(Article test) {
		this.test = test;
	}

	public void setQuestions(Map<Section, List<Question>> questions) {
		this.questions = questions;
	}
	
	public Map<Section, List<Question>> getQuestions() {
		return questions;
	}

	public void setAnswers(Map<Question, List<Answer>> answers) {
		this.answers = answers;
	}

	public Map<Question, List<Answer>> getAnswers() {
		return answers;
	}

}
