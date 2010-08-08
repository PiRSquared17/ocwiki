package oop.change.article;

import java.util.Set;

import oop.change.Change;
import oop.change.ChangeDelegate;
import oop.data.BaseArticle;
import oop.data.Topic;

public class ArticleTopicsChange extends ChangeDelegate {

	private Set<Topic> nt; // new topics
	private Set<Topic> ot; // old topics
	
	public ArticleTopicsChange(Set<Topic> newTopics) {
		super();
		this.nt = newTopics;
	}

	@Override
	public String getSummary() throws Exception {
		return null; //TODO summary
	}

	@Override
	public void perform(Change change) throws Exception {
		BaseArticle article = (BaseArticle) change.getArticle();
		ot = article.getTopics();
		article.setTopics(nt);
	}
	
	@Override
	public ChangeDelegate createReverse() {
		return new ArticleTopicsChange(ot);
	}

}
