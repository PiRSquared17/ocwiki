package oop.controller.rest.bean;

import java.security.InvalidParameterException;

import oop.data.Article;
import oop.data.BaseQuestion;
import oop.data.Test;

public class ArticleMapper implements Mapper<ArticleBean, Article> {

	@Override
	public ArticleBean apply(Article value) {
		if (value instanceof BaseQuestion) {
			return BaseQuestionMapper.get().apply((BaseQuestion) value);
		}
		if (value instanceof Test) {
//			TestMapper.get().apply(value);
		}
		throw new InvalidParameterException("unsupported type");
	}

	@Override
	public Article get(ArticleBean bean) {
		if (bean instanceof BaseQuestionBean) {
			return BaseQuestionMapper.get().get((BaseQuestionBean) bean);
		}
		throw new InvalidParameterException("unsupported type");
	}

	private static ArticleMapper DEFAULT_INSTANCE = new ArticleMapper();

	public static ArticleMapper get() {
		return DEFAULT_INSTANCE;
	}
	

	
}
