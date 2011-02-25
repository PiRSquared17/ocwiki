package org.ocwiki.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.search.annotations.IndexedEmbedded;

@XmlRootElement
public class MultichoiceQuestion extends Question {

	@IndexedEmbedded
	private List<Choice> choices = new ArrayList<Choice>();

	public MultichoiceQuestion() {
	}
	
	public MultichoiceQuestion(Namespace namespace, Text content, int level) {
		super(namespace, content);
		this.level = level;
	}

	/** 
	 * Hàm này tồn tại để tương thích với code cũ.
	 */
	@Deprecated
	public int getMark() {
		return 1;
	}

	public List<Choice> getChoices() {
		return choices;
	}
	
	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}

	/**
	 * Mỗi khi gọi hàm này thì đối tượng Map lại được tạo mới nên cần hạn chế 
	 * gọi. Chỉ dành để sử dụng trong trang JSP.
	 * @return
	 */
	@Deprecated
	public Map<Long, Choice> getAnswerById() {
		Map<Long, Choice> answerMap = new HashMap<Long, Choice>();
		for (Choice choice : getChoices()) {
			answerMap.put(choice.getId(), choice);
		}
		return answerMap;
	}

	protected <T> T copyTo(T obj) {
		super.copyTo(obj);
		MultichoiceQuestion question = (MultichoiceQuestion) obj;
		question.setChoices(new ArrayList<Choice>(getChoices()));
		question.setLevel(getLevel());
		return obj;
	}
	
	public MultichoiceQuestion copy() {
		return copyTo(new MultichoiceQuestion());
	}
	
	@Override
	public String toString() {
		return "MultichoiceQuestion #" + getId();
	}

}
