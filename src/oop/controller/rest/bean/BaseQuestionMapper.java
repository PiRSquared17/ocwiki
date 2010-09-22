package oop.controller.rest.bean;

import oop.data.BaseQuestion;

public class BaseQuestionMapper implements Mapper<BaseQuestionBean, BaseQuestion> {

	@Override
	public BaseQuestionBean apply(BaseQuestion value) {
		BaseQuestionBean bean = new BaseQuestionBean();
		bean.setId(value.getId());
		bean.setName(value.getName());
		bean.setNamespace(value.getNamespace());
		bean.setLevel(value.getLevel());
		bean.setAnswers(value.getAnswers());
		MapperUtils.applyAll(value.getTopics(), bean.getTopics(),
				ResourceReferenceMapper.get());
		return bean;
	}

	@Override
	public BaseQuestion get(BaseQuestionBean value) {
		// TODO Auto-generated method stub
		return null;
	}

	private static BaseQuestionMapper DEFAULT_INSTANCE = new BaseQuestionMapper();

	public static BaseQuestionMapper get() {
		return DEFAULT_INSTANCE;
	}
	
}
