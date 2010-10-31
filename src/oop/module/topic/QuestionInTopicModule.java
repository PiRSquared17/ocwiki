package oop.module.topic;

import java.util.List;

import oop.data.BaseQuestion;
import oop.data.Resource;
import oop.db.dao.BaseQuestionDAO;
import oop.module.DefaultModule;

public class QuestionInTopicModule extends DefaultModule {

	private List<Resource<BaseQuestion>> questions;

	@Override
	public void init() throws Exception {
		questions = BaseQuestionDAO.fetchByTopic(getResource().getId(), 0, 20);
	}
	
	public List<Resource<BaseQuestion>> getQuestions() {
		return questions;
	}
	
}
