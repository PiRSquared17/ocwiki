package oop.data;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BaseQuestion extends BaseArticle {

	private int level;
	private Set<Answer> answers = new HashSet<Answer>();
	
	/**
	 * For Hibernate only
	 */
	BaseQuestion() {
	}
	
	public BaseQuestion(Text content, int level, User author,
			Date createDate) {
		this.content = content;
		this.level = level;
		this.author = author;
		this.createDate = createDate;
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

	public Set<Answer> getAnswers() {
		return answers;
	}
	
	public void setAnswers(Set<Answer> answers) {
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

	@Override
	public Namespace getNamespace() {
		return Namespace.QUESTION;
	}

	@Override
	public String getName() {
		return String.valueOf(id);
	}

	@Override
	public String getQualifiedName() {
		return getNamespace().getName() + ":" + getName();
	}

}
