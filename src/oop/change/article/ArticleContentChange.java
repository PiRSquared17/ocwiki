package oop.change.article;

import oop.change.Change;
import oop.change.ChangeDelegate;
import oop.data.Article;
import oop.data.Text;

public class ArticleContentChange extends ChangeDelegate {
	
	private Text oc; // old content
	private Text nc; // new content

	/**
	 * For GSON only
	 */
	public ArticleContentChange() {
	}
	
	public ArticleContentChange(Text newContent) {
		this();
		this.nc = newContent;
	}

	@Override
	public void perform(Change change) throws Exception {
		Article question = change.getArticle();
		oc = question.getContent();
		question.setContent(nc);
	}

	@Override
	public ChangeDelegate createReverse() {
		return new ArticleContentChange(oc);
	}

	@Override
	public String getSummary() throws Exception {
		return "đã sửa đổi nội dung câu hỏi";
	}

}
