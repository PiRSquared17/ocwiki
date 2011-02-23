package org.ocwiki.module.topic;

import java.util.List;

import org.ocwiki.data.BaseQuestion;
import org.ocwiki.data.Resource;
import org.ocwiki.db.dao.BaseQuestionDAO;
import org.ocwiki.module.DefaultModule;

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
