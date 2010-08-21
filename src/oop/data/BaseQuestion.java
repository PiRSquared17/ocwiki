package oop.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BaseQuestion extends BaseArticle {

	private int level;
	private List<Answer> answers = new ArrayList<Answer>();

	BaseQuestion() {
	}
	
	public BaseQuestion(Namespace namespace, Text content, int level) {
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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public static String getLevelName(int level) {
		switch (level) {
		case 1:
			return "Rất khó";
		case 2:
			return "Khó";
		case 3:
			return "Trung bình";
		case 4:
			return "Dễ";
		case 5:
			return "Rất dễ";
		}
		return "<Không hợp lệ>";
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
		BaseQuestion question = (BaseQuestion) obj;
		question.setAnswers(new ArrayList<Answer>(getAnswers()));
		question.setLevel(getLevel());
		return obj;
	}
	
	public BaseQuestion copy() {
		return copyTo(new BaseQuestion());
	}

}
