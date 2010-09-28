package oop.controller.rest.bean;

import java.security.InvalidParameterException;

import oop.data.Article;
import oop.data.BaseQuestion;
import oop.data.File;
import oop.data.Test;
import oop.data.TestStructure;
import oop.data.TextArticle;
import oop.data.Topic;

public class ArticleMapper implements Mapper<ArticleBean, Article> {

	@Override
	public ArticleBean apply(Article value) {
		if (value instanceof BaseQuestion) {
			return BaseQuestionMapper.get().apply((BaseQuestion) value);
		}
		if (value instanceof Test) {
			return TestMapper.get().apply((Test) value);
		}
		if (value instanceof TestStructure) {
			return TestStructureMapper.get().apply((TestStructure) value);
		}
		if (value instanceof File) {
			return FileMapper.get().apply((File) value);
		}
		if (value instanceof TextArticle) {
			return TextArticleMapper.get().apply((TextArticle) value);
		}
		if (value instanceof Topic) {
			return TopicMapper.get().apply((Topic) value);
		}
		throw new InvalidParameterException("unsupported type");
	}

	@Override
	public Article get(ArticleBean bean) {
		if (bean instanceof BaseQuestionBean) {
			return BaseQuestionMapper.get().get((BaseQuestionBean) bean);
		}
		if (bean instanceof TestBean) {
			return TestMapper.get().get((TestBean) bean);
		}
		if (bean instanceof TestStructureBean) {
			return TestStructureMapper.get().get((TestStructureBean) bean);
		}
		if (bean instanceof FileBean) {
			return FileMapper.get().get((FileBean) bean);
		}
		if (bean instanceof TextArticleBean) {
			return TextArticleMapper.get().get((TextArticleBean) bean);
		}
		if (bean instanceof TopicBean) {
			return TopicMapper.get().get((TopicBean) bean);
		}
		throw new InvalidParameterException("unsupported type");
	}

	private static ArticleMapper DEFAULT_INSTANCE = new ArticleMapper();

	public static ArticleMapper get() {
		return DEFAULT_INSTANCE;
	}
	

	
}
