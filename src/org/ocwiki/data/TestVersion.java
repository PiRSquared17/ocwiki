package org.ocwiki.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestVersion {

	private Article test;
	private String code;
	private Map<Section, List<TestQuestion>> questions = new HashMap<Section, List<TestQuestion>>();
	private Map<TestQuestion, List<Answer>> answers = new HashMap<TestQuestion, List<Answer>>();

	TestVersion() {
	}
	
	public TestVersion(Article test, String code,
			Map<Section, List<TestQuestion>> questions,
			Map<TestQuestion, List<Answer>> answers) {
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

	public void setQuestions(Map<Section, List<TestQuestion>> questions) {
		this.questions = questions;
	}
	
	public Map<Section, List<TestQuestion>> getQuestions() {
		return questions;
	}

	public void setAnswers(Map<TestQuestion, List<Answer>> answers) {
		this.answers = answers;
	}

	public Map<TestQuestion, List<Answer>> getAnswers() {
		return answers;
	}

}
