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
	private List<Answer> answers = new ArrayList<Answer>();

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

	public List<Answer> getAnswers() {
		return answers;
	}
	
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	/**
	 * Mỗi khi gọi hàm này thì đối tượng Map lại được tạo mới nên cần hạn chế 
	 * gọi. Chỉ dành để sử dụng trong trang JSP.
	 * @return
	 */
	@Deprecated
	public Map<Long, Answer> getAnswerById() {
		Map<Long, Answer> answerMap = new HashMap<Long, Answer>();
		for (Answer answer : getAnswers()) {
			answerMap.put(answer.getId(), answer);
		}
		return answerMap;
	}

	protected <T> T copyTo(T obj) {
		super.copyTo(obj);
		MultichoiceQuestion question = (MultichoiceQuestion) obj;
		question.setAnswers(new ArrayList<Answer>(getAnswers()));
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
